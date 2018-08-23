/*********************************************
*        Student Name: Matthew Dunne         *
*      Student Number: 20087584              *
*              Group : 5N/50                 *
*          Instructor: Kashif Amjad          *
*              Class : CT 2530               *
*********************************************/

public class LRU
{
    public int size;
    public int[][] table;
    public int[] priority;
    public int[] page;

    public LRU(int x)
    {
        this.size = x;
        table = new int[size][size];
        priority = new int[size];
        page = new int[size];

        // sets every value of the table to a default value of -1
        for (int col = 0; col < size; col++)
        {
            for (int row = 0; row < size; row++)
            {
                table[col][row] = -1;
            }
        }

        for (int i = 0; i < size; i++)
        {
            priority[i] = 0;
        }

        for (int i = 0; i < size; i++)
        {
            page[i] = i;
        }

    }

    // Used for settings values of the table
    public void reference(int x)
    {
        if (x > size)
        {
            System.out.println("Invalid input; reference cannot be larger than the table.");
        }
        
        else
        {
            // sets all values of the row with the value of int x to 1
            for (int col = 0; col < size; col++)
            {
                table[col][x - 1] = 1;
            }

            // sets all values of the row with the value of in x to 0
            for (int row = 0; row < size; row++)
            {
                table[x - 1][row] = 0;
            }

            for (int row = 0; row < size; row++)
            {
                int sum = 0;
                for (int col = size - 1; col >= 0; col--)
                {
                    sum += table[col][row] * (int) Math.pow(2, size - col - 1);
                }
                priority[row] = sum;
            }
            
            System.out.println("Referencing value of: [" + x + "]");
            
        }

    }

    // Used to find the LRU
    public int findPage()
    {
        int temp = priority[0];
        
        for (int i = 0; i < size; i++)
        {
            if (temp > priority[i] && priority[i] >= 0)
            {
                temp = priority[i];
            }
        }

        for (int j = 0; j < priority.length; j++)
        {
            if (temp == priority[j])
            {
                temp = j + 1;
            }
        }
        return temp;
    }

    public void print()
    {
        System.out.println("\nA table value of 'X' represents a page that has not been referenced yet.");
        
        System.out.println("\nRow Number");
        System.out.println("|");
        System.out.print("V   ");

        for (int i = 0; i < size; i++)
        {
            System.out.print("| " + (i + 1) + " ");
        }
    
        System.out.print("|");
        
        System.out.print(" <-- Column Number\n");
        
        for (int i = 0; i < size; i++)
        {
            System.out.print("_____");
        }

        System.out.println();
        for (int row = 0; row < size; row++)
        {
            System.out.print((row + 1) + ":  ");
            for (int col = 0; col < size; col++)
            {
                if (table[col][row] == -1)
                {
                    System.out.print("| X ");
                }
                
                else
                {
                    System.out.print("| " + table[col][row] + " ");
                }
            }

            System.out.print("|\n");
            
            for (int x = 0; x < size; x++)
            {
                System.out.print("-----");
            }
            
            System.out.println();
        }

        System.out.println();
        System.out.println("Priorities: ");
        System.out.println("----------");
        for (int i = 0; i < priority.length; i++)
        {
            System.out.println((i + 1) + ":  " + priority[i]);
        }

        System.out.println("\nLowest priority value = Least recently used.");
        System.out.println("\nLeast Recently Used Page / Reference: " + findPage());

    }

}