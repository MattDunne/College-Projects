
import java.io.*;
import java.lang.*;
/*********************************************
*        Student Name: Matthew Dunne         *
*      Student Number: 20087584              *
*              Group : 5N/50                 *
*          Instructor: Kashif Amjad          *
*              Class : CP 2530               *
*********************************************/
public class Lab22
{
      public static void main(String args[]) throws IOException
    {
        String message; 
       
        DLinkedList dList = new DLinkedList();
        
        System.out.println("Inserting numbers to the list:");
        
        dList.Insert(5);
        dList.Insert(5);
        dList.Insert(7);
        dList.Insert(9);
        dList.Insert(5);
        dList.Insert(5);
        dList.Insert(7);
        dList.Insert(9);
        dList.Insert(5);
        dList.Insert(9);
        dList.Insert(5);
        dList.Insert(5);
        dList.Insert(11);
        dList.Insert(2);
        
        message = dList.toString();
        System.out.print(message);
        
        message = dList.reverse();
        System.out.print(message);
        
        System.out.println("\nRemoving two numbers:");
        
        dList.remove();
        dList.remove();
        
        message = dList.toString();
        System.out.print(message);
        
        message = dList.reverse();
        System.out.print(message);
        
        System.out.println("\nRemoving all numbers until nothing left:");
        
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        
        System.out.println("\nInserting same numbers again:");
        
        dList.Insert(5);
        dList.Insert(5);
        dList.Insert(7);
        dList.Insert(9);
        dList.Insert(5);
        dList.Insert(5);
        dList.Insert(7);
        dList.Insert(9);
        dList.Insert(5);
        dList.Insert(9);
        dList.Insert(5);
        dList.Insert(5);
        
        message = dList.toString();
        System.out.print(message);
        
        message = dList.reverse();
        System.out.print(message);
        
        System.out.println("\nRemoving the number 5 with the remove(int d) method:");

        dList.remove(5);
        
       message = dList.toString();
       System.out.print(message);
       
        System.out.println("\nAttemping to remove 5 again:");
        
        dList.remove(5);
        
        message = dList.toString();
        System.out.print(message);
        
        message = dList.reverse();
        System.out.print(message);
        
        System.out.println("\nRemoving remaining variables:");
        
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        dList.remove();
        
        
        System.out.println("Attempting to print list in forward and reverse:\n");
        message = dList.toString();
        System.out.print(message);
        
        message = dList.reverse();
        System.out.print(message);
    }
}

class DNode
{
    private int data;
    private DNode previous;
    private DNode next;

    public DNode()
    {
        data = 0;
        previous = null;
        next = null;
    }

    public void setData(int data)
    {
        this.data = data;
    }

    public int getData()
    {
        return data;
    }

    public void setPrevious(DNode previous)
    {
        this.previous = previous;
    }

    public DNode getPrevious()
    {
        return previous;
    }

    public void setNext(DNode next)
    {
        this.next = next;
    }

    public DNode getNext()
    {
        return next;
    }
}

class DLinkedList
{

    private DNode head;
    private DNode tail;
    private int size;
    
    public DLinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        } 
        
        else
        {
            return false;
        }
    }

    // This function is used to insert a specific variable to the list.
    public void Insert(int d)
    {
        DNode n = new DNode();

        n.setData(d);
        
        if (isEmpty())
        {
            head = n;
            tail = n;
        }
        
        else
        {
            tail.setNext(n);
            n.setPrevious(tail);
        }
        
        tail = n;
        System.out.println("Inserting: " + tail.getData());
        size++;
    }

    public int getSize()
    {
        return this.size;
    }

    // This function is used to remove the tail of the list.
    public void remove()
    {
        if (isEmpty())
        {
            System.out.println("List is now empty, nothing left to remove.\n");
        }
        
        else if (size == 1)
        {
            head = tail = null;
            size = 0;
        } 
        
        else
        {
            DNode temp = head;
            
            System.out.print("Removing: " + tail.getData()+ "\n");
            
            
            while (temp.getNext() != tail)
            {
                temp = temp.getNext();
            }
            
            tail = temp;
            tail.setNext(null);
            size--;
        }
    }

    // This function is used to remove a specific variable from the list.
    public void remove(int d)
    {
        if (isEmpty())
        {
            System.out.println("List is empty; nothing left to remove.\n");
        }
        else if (size == 1)
        {
            head = tail = null;
            size = 0;
        }
        else
        {
            DNode temp = head;
            boolean inList = false;
            
            while (temp != null)
            {
                if (d == temp.getData() && temp == head)
                {
                    head = head.getNext();
                    head.setPrevious(null);
                    temp.getNext().setPrevious(temp.getPrevious());
                    size--;
                    System.out.println(d + " has been removed from the list.");
                    inList = true;
                } 
                else if (d == temp.getData() && temp == tail)
                {
                    tail = tail.getPrevious();
                    tail.setNext(null);
                    size--;
                    System.out.println(d + " has been removed from the list.");
                    inList = true;
                } 
                else if (d == temp.getData())
                {
                    temp.getPrevious().setNext(temp.getNext());
                    temp.getNext().setPrevious(temp.getPrevious());
                    size--;
                    System.out.println(d + " has been removed from the list.");
                    inList = true;
                }
                   temp = temp.getNext(); 
            }
            
            if(inList == false)
            {
                System.out.println(d + " is not present in the list.");
            }
            
            inList = false;
        }
    }
    
    // This function is used to return the list as a string.
    public String toString()
    {
        String message = "";
        
        if (isEmpty())
        {
            message = "No links present to be printed in order.\n";
        } 
        else
        {
            message += "\nData printed forward:\n";
            
            DNode temp = head;
            while (temp != null)
            {
                message += temp.getData() + ",";
                temp = temp.getNext();
            }
           message += "\n";
        }
        return message;
    }
    
    // This function is used to return the list in reversed order as a string.
    public String reverse()
    {
        String message = "";
        
        if (isEmpty())
        {
            message = "No links present to be printed in reverse.\n";
        } 
        else
        {
            message += "\nData printed reversed:\n";
            
            DNode temp = tail;
            while (temp != null)
            {
                message += temp.getData() + ",";
                temp = temp.getPrevious();
            }
            message += "\n";
        }
        return message;
    }
    
}

