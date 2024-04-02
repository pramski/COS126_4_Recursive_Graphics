public class Sierpinski {

    public static double height(double length) {
        return (Math.sqrt(3.0)/2.0)*length;
    }

    /** Draws a filled equilateral triangle whose bottom vertex is (x, y) of the specified side length.
     * This is done by drawing the desired triangle with its top left vertex at (0,0) and using Transform2D,translate to move its bottom vertex to the desired (x,y).
     * @param x, bottom vertex x-coordinate.
     * @param y, bottom vertex y-coordinate.
     * @param length, length of the triangle */
    public static void filledTriangle(double x, double y, double length) {
        double[] X={0, length/2.0,length};
        double[] Y={0,-height(length),0};
        Transform2D.translate(X,Y,(x-length),(y+height(length)));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledPolygon(X,Y);
    }

    /** Draws a filled Sierpinski triangle of order n and the specified side length, where the largest triangle's bottom vertex is (x, y).
     * For this, we call filledTriangle to the desired point, then calculate the bottom vertices for the next largest triangles, and recursively call
     * the function at those points if the need arises .
     * @param n, order of the Sierpinski triangle
     * @param x, bottom vertex x-coordinate.
     * @param y, bottom vertex y-coordinate.
     * @param length, length of the triangle */
    public static void sierpinski(int n, double x, double y, double length) {
        if(n==0) return;
        filledTriangle(x,y,length);
        double x0=x-(3*length/4.0);
        double x1=x+length/4.0;
        double y1=y+height(length);
        sierpinski(n-1,x0,y,length/2.0);
        sierpinski(n-1,x1,y,length/2.0);
        sierpinski(n-1,x-length/4.0,y1,length/2.0);

    }
    //  Takes an integer command-line argument n;
    //  draws the outline of an equilateral triangle (pointed upwards) of length 1;
    //  whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and
    //  draws a Sierpinski triangle of order n that fits snugly inside the outline.
    public static void main(String[] args){
        int n=Integer.parseInt(args[0]);
        StdDraw.line(0,0,1,0); StdDraw.line(1,0,0.5,height(1)); StdDraw.line(0,0,0.5,height(1));
        sierpinski(n,0.75,0,0.5);
    }
}
