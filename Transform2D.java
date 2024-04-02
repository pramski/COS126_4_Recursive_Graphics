public class Transform2D {

    // Returns a new array object that is an exact copy of the given array.
    // The given array is not mutated.
    /** @param array of doubles
     * @return array, without any change*/
    public static double[] copy(double[] array){
        return array;
    }

    /** Scales the polygon by the factor alpha. This is accomplished by mutliplying each x, y coordinqate iteratively by alpha and then drawing a
     *  polygon with these new coordinates..
     * @param x, the array of x-coordinates for each point in the polygon
     * @param y, the array of y-coordinates for each point on the polygon
     * @param alpha, the factor byb which we must scale each coordinate */
    public static void scale(double[] x, double[] y, double alpha){
        for(int i=0;i<x.length;i++){
            x[i]*=alpha;
            y[i]*=alpha;
        }

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(x,y);
    }

    /** Translates the polygon by (dx, dy). This is done by iteratively adding the required increments in both directions and then
     * drawing the new polygon.
     * @param x, the array of x coordinates.
     * @param y, the array of y-coordinates.
     * @param dx the amount we must translate by in the x-direction.
     * @param dy the amount we must translate by in the x-direction.
     */
    public static void translate(double[] x, double[] y, double dx, double dy){
        for(int i=0;i<x.length;i++){
            x[i]+=dx;
            y[i]+=dy;
        }
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(x,y);

    }

    /** Rotates the polygon "theta" degrees counterclockwise, about the origin. This is done by rotating each x and y coordinate by theta iteratively (we adjust for calculation
     * of rotated x when calculating rotated y) and then redraw our polygon.
     * @param x, the array of x coordinates.
     * @param y, the array of y coordinates.
     * @param theta, the angle we must rotate by
     */

    public static void rotate(double[] x, double[] y, double theta){
        theta=Math.toRadians(theta);
        for(int i=0;i<x.length;i++){
            x[i]=x[i]*Math.cos(theta)-y[i]*Math.sin(theta);
            y[i]=y[i]*Math.cos(theta)+(x[i]+y[i]*Math.sin(theta))*Math.tan(theta);
        }
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(x,y);
    }

    // Tests each of the API methods by directly calling them. I have used a tier system for this purpose. Use 1,2 or 3 to call scaling, translation and rotation respectively.
    public static void main(String[] args){
        int n=Integer.parseInt(args[0]);
        double [] x= new double [n];
        double [] y= new double [n];
        StdDraw.setScale(-5.0,5.0);
        for(int j=0;j<n;j++){
            x[j]=StdIn.readDouble();
        }
        for(int k=0;k<n;k++){
            y[k]=StdIn.readDouble();
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.polygon(x,y);
        System.out.print("Pick a number from 1 to 4");
        int m=StdIn.readInt();
        double[] cx=Transform2D.copy(x);
        double[] cy=Transform2D.copy(y);
        if(m==1){
            double alpha=StdIn.readDouble();
            Transform2D.scale(cx,cy,alpha);
        }
        else if(m==2){
            double dx=StdIn.readDouble();
            double dy=StdIn.readDouble();
            translate(cx,cy,dx,dy);
        }
        else if(m==3){
            double theta=StdIn.readDouble();
            rotate(cx,cy,theta);
        }
        else{
            System.out.println("I don't have that transform");
        }
    }
}

