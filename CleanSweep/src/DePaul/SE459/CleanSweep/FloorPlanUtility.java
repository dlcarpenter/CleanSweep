import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class contains all of the logic related to loading and dumping floor plans to and from files.
 * TODO: move this to a better place once we have our packages set up. Probably belongs in the sensor program package.
 */
public class FloorPlanUtility
  {
  /**
   * Loads an XML file from disk and parses it into a floor plan.
   * 
   * @param filePath The file path of the XML file.
   * @return The FloorPlan, complete with Floors and Tiles.
   * @throws Exception If there is an IO error or file does not meet predefined floor plan layout.
   */
  public static FloorPlan loadFloorPlan(String filePath) throws Exception
    {
    File file = new File(filePath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
    Document doc = docBuilder.parse(file);

    doc.getDocumentElement().normalize();

    FloorPlan floorPlan = new FloorPlan();

    // for each floor
    NodeList floors = doc.getElementsByTagName("floor");
    for (int i = 0; i < floors.getLength(); i++)
      {
      Node floor = floors.item(i);

      if (floor.getNodeType() == Node.ELEMENT_NODE)
        {
        Element floorElements = (Element) floor;
        int level = Integer.parseInt(floorElements.getAttribute("level"));
        Floor f = new Floor(level);
        floorPlan.addFloor(f);

        // for each cell
        NodeList cells = floor.getChildNodes();
        for (int ii = 0; ii < cells.getLength(); ii++)
          {
          Node cell = cells.item(ii);

          if (cell.getNodeType() == Node.ELEMENT_NODE)
            {
            Element cellElements = (Element) cell;

            int xPos = Integer.parseInt(cellElements.getAttribute("xs"));
            int yPos = Integer.parseInt(cellElements.getAttribute("ys"));
            int surface = Integer.parseInt(cellElements.getAttribute("ss"));

            String paths = cellElements.getAttribute("ps");

            int right = Integer.parseInt(paths.substring(0, 1));
            int left = Integer.parseInt(paths.substring(1, 2));
            int down = Integer.parseInt(paths.substring(2, 3));
            int up = Integer.parseInt(paths.substring(3, 4));

            int dirt = Integer.parseInt(cellElements.getAttribute("ds"));
            int charge = Integer.parseInt(cellElements.getAttribute("cs"));

            Boolean charger = false;
            if (charge == 1)
              {
              charger = true;
              }

            Tile t = new Tile(xPos, yPos, surface, right, left, up, down, dirt, charger);
            f.addTile(t);
            }
          }
        }
      }

    return floorPlan;
    }
  }
