public class Lab32 
{
  public static void main(String[] args)
  {
      CA ca;
      int size;
      int rule;
      
      int maxSize = 3;
      int maxRule = 255;
      
      if(args.length != 0)
      {
          try
          {
              size = Integer.parseInt(args[0]);
              if(size < maxSize) throw new NumberFormatException();
              rule = Integer.parseInt(args[1]);
              if(rule > maxRule) throw new NumberFormatException();
              ca = new CA(size,rule);
          }
          catch(NumberFormatException e)
          {
              System.out.println("Improper format.");
              return;
          }
      }
      
      else
      {
          System.out.println("You must enter integers for size and rule.");
          return;
      }
      
      
      String output = "";
      boolean[] cell = new boolean[size];
      
      int counter = 0;

      while(true)
      {
          cell = ca.getState();
          output = "";
          
          for(int i = 0; i < size; i++)
          {
             if(cell[i])
             {
                 output += 'X';
             }
             else
             {
                 output += ' ';
             }
          
          }
          
       System.out.println(output);

          counter++;
          
          if(counter >= rule)
          {
              break;
          }
      }
     
  }
}

class CA
{
    private boolean[] tTable;
    private boolean[] cell;
    
    private int[] tempTable;
    
    private int size;
    private int rule;
    private int tTableLength = 8;// 8 because 8 bites
    
    
    public CA(int x, int y)
    {
        this.size = x;
        this.rule = y;
        
        cell = new boolean[size];
        tTable = new boolean[tTableLength];
        
        
        
       /* for(int i = 0; i < cell.length; i++)
        {
            cell[i] = false;
        }*/
        
        cell[size/2] = true;
        
        printBinaryForm(rule);
        
        for(int i = 0; i < tTableLength; i++)
        {
            if(tempTable[i] == 0)
            {
                tTable[i] = false;
                //System.out.println("false ");
            }
            if(tempTable[i] == 1)
            {
                tTable[i] = true;
                //System.out.println("true ");
            }
        }
        
        
        
    }
    
    private void printBinaryForm(int rule)
    {
        int remainder;
        
        tempTable = new int[tTableLength]; 
        
        for(int i = 0; i < tTableLength ;i++)
        {
            
           if (rule <= 0) {
               System.out.print(rule);
               return;   // KICK OUT OF THE RECURSION
           }

           remainder = rule %2; 
           rule = (rule >> 1);
           //System.out.print(remainder)
           tempTable[i] = remainder;
        }
        
    }
    
    public boolean[] getState()
    {
       boolean[] temp = new boolean[size];
		for(int l = 0; l < size; l++)
                {
                    temp[l] = cell[l];
                }
			
		
		int left, right, center;
			
		//Leftmost
		left = toInt(cell[size - 1]);
		center = toInt(cell[0]);
		right = toInt(cell[1]);
		
		int ind = (4 * left) + (2 * center) + right;
		temp[0]=tTable[ind];
			
		//Center Indexes
		for(int i = 1; i < size-1; i++) 
                {
		
			left = toInt(cell[i - 1]);
			center = toInt(cell[i]);
			right = toInt(cell[i + 1]);
		
			ind = (4 * left) + (2 * center) + right;
			temp[i]=tTable[ind];
		
		}
			
		//Rightmost
		left = toInt(cell[size - 2]);
		center = toInt(cell[size -1]);
		right = toInt(cell[0]);
		
		ind = (4 * left) + (2 * center) + right;
		temp[size-1]=tTable[ind];
			
		//Cell becomes Temp	
	
		for(int gg = 0; gg < size; gg++) 
                {
			cell[gg] = temp[gg]; 
			
			
			}
		
		//And the cycle repeats...	
		
		return temp;
    }
    
    public int toInt(boolean n)
	
	{
		int ans = 0;
		
		if (n)
                {
                   ans = 1; 
                }
                else
                {
                  ans = 0;  
                }
			
		return ans;	
	}
    
   /* public String toString()
    {
        String s = "cell size is: " + size;
        s+= "\nRule: " + rule;
        return s;
    }*/
}