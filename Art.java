public class Art {
    /*This is going to create a recursive pattern of tilted squares that spawn near the diagonals of each preceding square at random.*/
    /** First, we create a function that allows us to rotate a point with respect to an arbitrary point
     * @param x, initial x coordinate
     * @param y, initial y coordinate
     * @param theta, angle to rotate by
     * @param x1 reference point' x coordinate
     * @param y1 reference point's y-coordinate
     * @return an array {x,y} consisting of the rotated points
     *  */
    private static double[] pointTranslate(double x, double y,double theta,double x1,double y1){
        x=x-x1;
        y=y-y1;
        x=x*Math.cos(theta)-y*Math.sin(theta);
        y=y*Math.cos(theta)+(x+y*Math.sin(theta))*Math.tan(theta);
        x=x+x1;
        y=y+y1;
        return new double[] {x,y};
    }
    /* This is going to create a recursive pattern of tilted squares that spawn near the diagonals of each preceding square at random.*/
    /** Next we create a square that has bottom left vertex at a desired point and is tilted anti-clockwise by "theta" degrees
     * @param x, initial x coordinate
     * @param y, initial y coordinate
     * @param theta, angle to rotate by
     * @param length, the length of the square */
    private static void shiftedRotate(double x,double y, double theta,double length){
        double[]X={0,length,length,0};
        double[]Y={0,0,length,length};
        StdDraw.setScale(-2,2);
       Transform2D.rotate(X,Y,theta);
       Transform2D.translate(X,Y,x,y);
    }

    /** Below is the recursive function which will create our pattern. Our base case is an instance of shiftedRotate and for non-one orders,
     * we recursively call it  at random at either the bottom vertex, or the diagonally opposite top right one.
     * @param n, order of the squares
     * @param x, initial x coordinate
     * @param y, initial y coordinate
     * @param theta, angle to rotate by
     * @param length, the length of the square */
    private static void shiftedSquare(int n, double x, double y, double theta, double length){
        if (n==0) return;
        StdDraw.setScale(-2,2);
        shiftedRotate(x,y,theta,length);
        double r=Math.random();
        if(r<0.5){
            shiftedSquare(n-1,x,y,theta,length/2);
            shiftedSquare(n-2,x,y,theta,length/4);
        }
        else{
            shiftedSquare(n-1,pointTranslate(x+length,y+length/4,theta,x,y)[0],pointTranslate(x+length,y+length/4,theta,x,y)[1],theta,length/2);
        }

    }
    // Now we just accept a value for n and call our function at the origin
    public static void main(String[] args) {
        int n=Integer.parseInt(args[0]);
        shiftedSquare(n,0.0,0.0,20.0,1.0);
    }
}
