/*********************************************
*        Student Name: Matthew Dunne         *
*      Student Number: 20087584              *
*              Group : 5N/50                 *
*          Instructor: Kashif Amjad          *
*              Class : CT 2530               *
*********************************************/

import java.util.*;

class LinkedList
{
    // head points to the first node in the list.
    private Node head;
    // tail points to the last node in the list.
    private Node tail;

    // size is used to determine if the LinkedList is empty or to help assign values to an array.
    int size;

     /* this will run where there are no parameters, since there is no value input it will
       default size to 0 and since there is not first or last node it will default head and
       tail to 'null'.*/
    public LinkedList()
    {
        head = tail = null;
        size = 0;
    }

    /* insert works by creating a temporary Node 'n' and then uses setData to assign the value
       input in the parameters to the node. If its empty then it must be the first node in the 
       list so it assigns it to head, if its not then it is either the last node or will continue
       on more nodes so setLink is used to assing n for the next link*/
    public void insert(int data)
    {
        Node n = new Node();
        n.setData(data);
        if (isEmpty())
        {
            head = n;
        } 
        else
        {
            tail.setLink(n);
        }
        tail = n;
        System.out.println("Inserting: " + tail.getData());
        size++;
    }

    /* remove works by frist checking if its empty, if it is then it cannot remove anyhting so it
       will print a message to let the user know. */
    public void remove()
    {
        if (isEmpty())
        {
            System.out.println("List is empty, nothing can be removed.");
        } 
        else if (size == 1)
        {
            head = tail = null;
            size = 0;
            System.out.println("List is empty, nothing can be removed.");
        }
        else
        {
            Node temp = head;
            while (temp.getLink() != tail)
            {
                temp = temp.getLink();
            }
            System.out.print("Removing: " + tail.getData()+ "\n");
            tail = temp;
            tail.setLink(null);
            size--;
        }
    }

     
    // this function determines if the the LinkedList is empty by checking if its size is 0
    public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /* this function prints the LinkedList, it references 'isEmpty' to check if it has
       anything to print and lets the user know if its empty. */
    public void print()
    {
        if (isEmpty())
        {
            System.out.println("List is empty.");
        } else
        {
            Node temp = head;
 
            System.out.println("\nPrinting The List:");
            while (temp != null)
            {
                System.out.print(temp.getData() + ",");
                temp = temp.getLink();
            }
            System.out.println();
        }

    }

    public int size()
    {
        return size;
    }

    
    /* this function runs through all of the nodes and returning the values as an array. If
       there is nothing in teh array it will let the user know. */
    public int[] toArray()
    {
        if (isEmpty())
        {
            int[] a = new int[0];
            System.out.println("That array is empty.");
            return a;
        } 
        else
        {
            Node temp = head;
            int counter = 0;
            int[] array = new int[this.size];

            while (temp != null)
            {
                array[counter] = temp.getData();
                temp = temp.getLink();
                counter++;
            }

            return array;
        }
    }
}

class Lab21
{

    public static void main(String[] args)
    {
        LinkedList l = new LinkedList();
        int[] array;
     
        System.out.println("Inserting 1, 7, and 3:");
        
        l.insert(1);
        l.insert(7);
        l.insert(3);

        array = l.toArray();

        l.print();
        
        System.out.print("\nPrinting the [1] value of the array:\n[");
        System.out.print(array[1]);
        System.out.println("]");
        
        System.out.print("\nArray Values:\n[");
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]);

            if (i < array.length - 1)
            {
                System.out.print(",");
            }
        }

        System.out.println("]\n");
        
        
        
        l.remove();
        l.print();
        array = l.toArray();
        System.out.print("\nArray Values:\n[");

        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]);

            if (i < array.length - 1)
            {
                System.out.print(",");
            }
        }
        System.out.println("]\n");
        
        
        
        l.remove();
        l.print();
        array = l.toArray();
        System.out.print("\nArray Values:\n[");

        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]);

            if (i < array.length - 1)
            {
                System.out.print(",");
            }
        }
        System.out.println("]\n");
        
        
        
        l.remove();
        l.print();
        System.out.println("");
        array = l.toArray();
        System.out.print("\nArray Values:\n[");

        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]);

            if (i < array.length - 1)
            {
                System.out.print(",");
            }
        }
        System.out.println("]\n");
    }
}

class Node
{

    private int data;
    private Node link;

    /* this will run where there are no parameters, since there is no value input it will
       default to 0 and since there isn't a link set it will default to 'null'.*/
    public Node()
    {
        data = 0;
        link = null;
    }

    //'setData' is what is used to assigned a value to a node.
    public void setData(int data)
    {
        this.data = data;

    }

    //'getData' is what is used to retrieve the assigned data.
    public int getData()
    {
        return data;

    }

    //setLink is used to set the next link this node will link to.
    public void setLink(Node link)
    {
        this.link = link;

    }

    //getLink is used when you need to retrieve the next link this node is connected to.
    public Node getLink()
    {
        return link;

    }
}

class TestNode
{

    public static void main(String[] args)
    {
        Node n1, n2, n3, temp;

        n1 = new Node();
        n2 = new Node();
        n3 = new Node();

        n1.setData(1);
        n1.setLink(n2);
        n2.setData(2);
        n2.setLink(n3);
        n3.setData(3);

        temp = n1;
        while (temp != null)
        {
            System.out.print(temp.getData() + " ");
            temp = temp.getLink();
        }

        System.out.println();
    }
}