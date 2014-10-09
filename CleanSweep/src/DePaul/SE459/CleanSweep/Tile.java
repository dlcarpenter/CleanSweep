public class Tile 
{
    private int x;
    private int y;
    private int ss;
    private int r;
    private int l;
    private int u;
    private int d;
    private int ds;
    private boolean cs;
    
    public Tile(int xC, int yC, int sC, int rC, int lC, int uC, int dC, int dsC, boolean csC)
    {
        setX(xC);
        setY(yC);
        setSs(sC);
        setR(rC);
        setL(lC);
        setU(uC);
        setD(dC);
        setDs(dsC);
        setCs(csC);
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSs() {
        return ss;
    }

    public void setSs(int ss) {
        this.ss = ss;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getDs() {
        return ds;
    }

    public void setDs(int ds) {
        this.ds = ds;
    }

    public boolean isCs() {
        return cs;
    }

    public void setCs(boolean cs) {
        this.cs = cs;
    }
}
