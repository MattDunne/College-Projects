class IntegerSet
{
    int[] a = new int[100];
    
    
     public IntegerSet()
    {
        for(int i = 0; i < a.length;i++ )
        {
            a[i] = 0;
        }
    }
    
    public void insert(int i)
    {
        // sets i'th positon of array to equal 1
        a[i] = 1;
    }
    
    public void delete(int i)
    {
       a[i] = 0;
    }
    
    public String toString()
    {
        String s = new String("{");
        
        // counts ups to length of array and checks if position of the array is equal to
        // one and then prints that position number if it IS equal to 1
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] == 1)
            {
                s = s + i;
                break;
            }
        }
        
        for(int j = 0; j < a.length; j++)
        {
            if(a[j] == 1)
            {
                s = s + ", " + j;
                
            }
        }
        
        s = s + "}";
        return s;
    }
    
    public IntegerSet union(IntegerSet s)
    {
        IntegerSet IntUnion = new IntegerSet();
        
        for(int i = 0; i < a.length; i++)
        {
            if(this.a[i] == 1 || s.a[i] == 1)
            {
                IntUnion.insert(i);
            }
        }
        return IntUnion;
    }
    
    public IntegerSet intersection(IntegerSet s)
    {
        IntegerSet IntSec = new IntegerSet();
        
        for(int i = 0; i < a.length; i++)
        {
            if(this.a[i] == 1 && s.a[i] == 1)
            {
                IntSec.insert(i);
            }
        }
        return IntSec;
    }
    
    public IntegerSet difference(IntegerSet s)
    {
        IntegerSet IntDiff = new IntegerSet();
        
        for(int i = 0; i < a.length; i++)
        {
            if(this.a[i] != s.a[i])
            {
                IntDiff.insert(i);
            }
        }
        return IntDiff;
    }
    
    public boolean equals(IntegerSet s)
    {
        IntegerSet equals = new IntegerSet();
        boolean bool = true;
        
        for(int i = 0; i < a.length && bool; i++)
        {
            if(this.a[i] != s.a[i])
            {
                bool = false;
            }
        }
        
        return bool;
    }
    
    
}

public class Lab31 
{
    public static void main(String[] args)
    {
        IntegerSet x = new IntegerSet();
        x.insert(12);
        System.out.println("12 inserts to x: " + x);
	x.insert(22);
	System.out.println("22 inserts to x: " + x);
	x.delete(22);
	System.out.println("22 is deleted from x: " + x);
		
	IntegerSet y = new IntegerSet();
	y.insert(11); y.insert(12); y.insert(13);
	x.union(y);
	System.out.println("y is placed in union with x: " + x.union(y));
	x.intersection(y);
	System.out.println("y intersects x: " + x.intersection(y));
	x.difference(y);
	System.out.println("The Difference is: " + x.difference(y));
	x.equals(y);
	System.out.println("Checks if x and y are equal: " + x.equals(y));
    }
}