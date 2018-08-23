class Point 
{
    private int x, y;
    
    public Point()
    {
        x = y = 0;
    }
    
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public String toString()
    {
        String s = "x: " + x + " y: " +y;
        return s;
    }
}

class Circle
{
    private int radius = 1;
    Point center = new Point(0,0);
    
    public Circle()
    {
        
    }
    
    public Circle(int radius)
    {
        this.radius = radius;
    }
    
    public Circle(Point center)
    {
        this.center = center;
    }
    
    public Circle(int radius,Point center)
    {
        this.radius = radius;
        this.center = center;
    }
    
    public double area()
    {
        return (Math.PI*Math.pow(radius, 2));
    }
    
    double circumference()
    {
        return(2*Math.PI*radius);
        
    }
    
    public String toString()
    {
        String s = "radius: " 
                   + radius + 
                   " center: " 
                   + center +
                   " area: "
                   + area() +
                   " circumference: "
                   + circumference()
                   ;
        return s;
    }
}



class Lab21
{
    public static void main(String[]args)
    {
        Point A = new Point();
        Point B = new Point(2,5);
        
        Circle test = new Circle();
        Circle a = new Circle(A);
        Circle b = new Circle(B);
        Circle c = new Circle(7,B);
        Circle d = new Circle(5);

        System.out.println("Point A:  "+ A);
        System.out.println("Point B:  "+B+"\n");
        
        System.out.println("test circle(circle with nothing within perimeters):  "+test+"\n");
        System.out.println("circle a(uses Point A):                  "+a);
        System.out.println("circle b(uses Point B):                  "+b);
        System.out.println("circle c(uses radius of 7 and Point B):  "+c);
        System.out.println("circle d(just using radius value of 5): "+d);

        A.move(2,4);
        B.move(3,6);
        
        System.out.println("\nPoint A has been moved to (2,4)");
        System.out.println("Point B has been moved to (3,6)\n");
        
        System.out.println("circle a(uses Point A):                  "+a);
        System.out.println("circle b(uses Point B):                  "+b);
        System.out.println("circle c(uses radius of 7 and Point B):  "+c);
    }
}