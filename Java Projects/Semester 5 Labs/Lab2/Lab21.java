/*********************************************
*        Student Name: Matthew Dunne         *
*      Student Number: 20087584              *
*              Group : 5N/50                 *
*          Instructor: Kashif Amjad          *
*              Class : CP 2530               *
*********************************************/

public class Lab31
{
    public static void main(String[] args)
    {
    
	QueueA q = new QueueA();
		
	System.out.println(" ");
    q.enqueueA(345);
    q.enqueueA(43);
    q.enqueueA(4);
	q.enqueueA(45);
	q.enqueueA(3);
    q.enqueueA(1098);
    q.enqueueA(645);
    System.out.println(q);
	q.dequeueA();
	System.out.println(q);
    q.enqueueA(65);
    q.enqueueA(2);
    System.out.println(q);
    q.dequeueA();
    System.out.println(q);
	System.out.println("Proceeding to remove everything from the Queue:");
	q.dequeueA();
	q.dequeueA();
	q.dequeueA();
	q.dequeueA();
	q.dequeueA();
	q.dequeueA();
	q.dequeueA();
	q.dequeueA();
	System.out.println(q);
	
    }
}

class QueueA
{
    private DNodeA head;
    private DNodeA tail;
    private int size;

    public QueueA()
    {
        head = tail = null;
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

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("\nPrinting Queue in ascending order:\n");
        sb.append("[");
        DNodeA temp = head;
        
        while (temp != null)
        {
            if (temp.getNext() == null)
            {
                sb.append(temp.getData());
            } 
            else
            {
                sb.append(temp.getData() + ", ");
            }
            
            temp = temp.getNext();
        }
        
        sb.append("]\n");
        String s = sb.toString();
        return s;
    }

    public void enqueueA(int data)
    {
        DNodeA n = new DNodeA();
        n.setData(data);
        
        System.out.println("Inserting ["+ n.getData()+ "] into the Queue.");
        
        // Runs when there are no numbers present in the Queue.
        if (isEmpty())
        {
            head = n;
            size = 1;
        } 
        
        // Runs when there is only one number present in the Queue.
        else if (size == 1)
        {
            // Runs if it is less than the first(only) number present in the Queue.
            if (n.getData() < head.getData())
            {
                DNodeA temp = head;
                n.setNext(temp);
                head = n;
                tail = temp;
                tail.setPrevious(head);
                size++;
            } 
            
            // Runs this if it is greater than the first(only) number preent in the Queue.
            else
            {
                head.setNext(n);
                tail = n;
                tail.setPrevious(head);
                size++;
            }
        }
        
        // Runs if there is aleady two numbers present in the Queue.
        else
        {
            DNodeA temp = head.getNext();
            
            while (temp != null)
            {
                // Runs if number inserted is less than tail (last number in Queue).
                if (n.getData() > tail.getData())
                {
                    DNodeA swap = tail;
                    swap.setNext(n);
                    n.setPrevious(swap);
                    tail = n;
                    size++;
                    
                    break;
                } 
                
                // Runs if number inserted is less than tail (first number in Queue).
                else if (n.getData() < head.getData())
                {
                    DNodeA swap2 = head;
                    n.setNext(swap2);
                    swap2.setPrevious(n);
                    swap2.setNext(head.getNext());
                    head = n;
                    size++;
                    
                    break;
                } 
                
                // Runs if the number inserted is less than or equal to temp.getData()
                // (which is head.getNext()).
                else if (n.getData() <= temp.getData())
                {
                    temp.getPrevious().setNext(n);
                    n.setPrevious(temp.getPrevious());
                    temp.setPrevious(n);
                    n.setNext(temp);
                    size++;
                    
                    break;
                } 
                
                // If none of the above statements are met, it will set temp to be the 
                // next number in line and loop again until one of the conditions is true.
                else
                {
                    temp = temp.getNext();
                }
            }
        }
    }

    public void dequeueA()
    {
        if (isEmpty())
        {
            System.out.println("Queue is empty, nothing to deQueue.");
        }
        
        else if (size == 1)
        {
            System.out.println("Removing head element [" + head.getData() + "] from the Queue.");
            head = tail = null;
            size = 0;
        } 
        
        else
        {
            System.out.println("Removing head element [" + head.getData() + "] from the Queue.");
            head = head.getNext();
            size--;
        }
    }
    
    class DNodeA
{
    private int data;
    public DNodeA previous;
    public DNodeA next;

    public DNodeA()
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

    public void setPrevious(DNodeA previous)
    {
        this.previous = previous;
    }

    public DNodeA getPrevious()
    {
          return previous; 
    }

    public void setNext(DNodeA next)
    {
        this.next = next;
    }

    public DNodeA getNext()
    {
        return next;
    }
}
    
}