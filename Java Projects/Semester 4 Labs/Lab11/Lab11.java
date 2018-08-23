public class Lab11 
{
    public static void main(String[] args)
    {
        int[] a = new int[1000];
        init(a);
        findPrimes(a);
        printPrimes(a);
        
    }
    
    public static void init(int[] a)
    {
        for(int i = 2; i < a.length;i++ )
        {
            a[i] = 1;
        }
        
    }
    
    
    
    public static void findPrimes(int[] a)
    {
       for(int i = 2; i < a.length;i++)
       {
           if(a[i] == 1)
           {
               for(int j = i*2; j < a.length; j+=i)
               {
                   a[j] = 0;
               }
           }
           
       }
    }
    
    public static void printPrimes(int[] a)
    {
        for( int x = 0; x < a.length; x++)
        {
            if(a[x]!= 0)
            System.out.println(x);
        }
    }
}