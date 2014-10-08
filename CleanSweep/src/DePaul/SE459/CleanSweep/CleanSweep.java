package DePaul.SE459.CleanSweep;

import java.awt.Point;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The main entry point of the CleanSweep program.
 */
public class CleanSweep
  {
  /**
   * Main entry point.
   * @param args command line arguments
   */
  public static void main(String args[])
    {
    String test;
    if (args.length == 0)
      {
      test = "floorplans\\samplehome0.xml";
      }
    else
      {
      test = args[0];
      }

    try
      {
      loadFloorplan(test);
      }
    catch (Exception e)
      {
      System.err.println("Exception in main: " + e.getMessage());
      e.printStackTrace();
      }
    }

  /**
   * Loads an XML file from disk and parses it into a floor plan.
   * 
   * @param filePath The file path of the XML file.
   * @throws Exception If there is an IO error or file does not meet predefined floor plan layout.
   */
  public static void loadFloorplan(String filePath) throws Exception
    {
    File file = new File(filePath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
    Document doc = docBuilder.parse(file);

    // this will clean up the file and remove stuff we don't want, such as comments or line breaks between elements.
    doc.getDocumentElement().normalize();

    System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
    System.out.println("----------------------------");

    NodeList floors = doc.getElementsByTagName("floor");

    // for each floor
    for (int i = 0; i < floors.getLength(); i++)
      {
      Node floor = floors.item(i);

      System.out.println("Current element: " + floor.getNodeName());
      if (floor.getNodeType() == Node.ELEMENT_NODE)
        {
        Element floorElements = (Element) floor;

        System.out.println("Level: " + floorElements.getAttribute("level"));
        System.out.println("----------------------------");

        NodeList cells = doc.getElementsByTagName("cell");

        // for each cell
        for (int ii = 0; ii < cells.getLength(); ii++)
          {
          Node cell = cells.item(ii);

          if (cell.getNodeType() == Node.ELEMENT_NODE)
            {
            Element cellElements = (Element) cell;

            int xPos = Integer.parseInt(cellElements.getAttribute("xs"));
            int yPos = Integer.parseInt(cellElements.getAttribute("ys"));

            Point pos = new Point(xPos, yPos);
            System.out.println(String.format("\nCurrent element: %s (%d,%d) on floor %s",
                cell.getNodeName(), pos.x, pos.y, floorElements.getAttribute("level")));
            
            System.out.println("xs: " + cellElements.getAttribute("xs"));
            System.out.println("ys: " + cellElements.getAttribute("ys"));
            System.out.println("ss: " + cellElements.getAttribute("ss"));
            System.out.println("ps: " + cellElements.getAttribute("ps"));
            System.out.println("ds: " + cellElements.getAttribute("ds"));
            System.out.println("cs: " + cellElements.getAttribute("cs"));
            }
          }
        
        System.out.println("\n----------------------------");
        }
      }
    }
  }