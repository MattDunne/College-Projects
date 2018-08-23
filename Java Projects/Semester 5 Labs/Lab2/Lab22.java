/*********************************************
*        Student Name: Matthew Dunne         *
*      Student Number: 20087584              *
*              Group : 5N/50                 *
*          Instructor: Kashif Amjad          *
*              Class : CP 2530               *
*********************************************/

public class Lab32
{
    public static void main(String[] args)
    {
		
    QueueB q = new QueueB();
	
	System.out.println(" ");
	q.enqueueB(9); 
    q.enqueueB(3);
    q.enqueueB(6);
    q.enqueueB(74);
    q.enqueueB(15);
	System.out.println(q);
	q.dequeueB();
	System.out.println(q);
    q.enqueueB(5);
    q.enqueueB(100);
	System.out.println(q);
	q.dequeueB();
	System.out.println(q);
	System.out.println("Proceeding to remove everything from the Queue:");
	q.dequeueB();
    q.dequeueB();
    q.dequeueB();
    q.dequeueB();
    q.dequeueB();
	q.dequeueB();
	System.out.println(q);
	
    }
}

class QueueB
{
    private DNodeB head;
    private DNodeB tail;
    private int size;

    public QueueB()
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

    public void enqueueB(int data)
    {
        DNodeB n = new DNodeB();
        n.setData(data);
        
        // Runs when there are no numbers present in the Queue
        if (isEmpty())
        {
            head = n;
            tail = n;
        } 
        
        // Runs in every other case
        else
        {
            tail.setNext(n);
            n.setPrevious(tail);
        }
        
        
        System.out.println("Inserting [" + n.getData() + "] into the Queue.");
        tail = n;
        size++;
    }
    
    public void dequeueB()
    {
        if (isEmpty())
        {
            System.out.println("Queue is empty, nothing to deQueue.");
        } 
        
        else if (size == 1)
        {
            head = tail = null;
            size = 0;
			System.out.println("Removing only element present in the Queue.");
        } 
        
        else
        {
            DNodeB temp = head;
            DNodeB temp2 = head;
            
            while (temp != null)
            {
                if (temp2.getData() >= temp.getData())
                {
                    temp2 = temp;
                }
                temp = temp.getNext();
            }

            if (temp2 == head)
            {
                head = temp2.getNext();
                head.setPrevious(null);
                size--;
                System.out.println("Removing lowest element [" + temp2.getData() + "] from the Queue.");
            } 
            
            else if (temp2 == tail)
            {
                tail = temp2.getPrevious();
                tail.setNext(null);
                size--;
                System.out.println("Removing lowest element [" + temp2.getData() + "] from the Queue.");
            } 
            
            else
            {
                temp2.getPrevious().setNext(temp2.getNext());
                temp2.getNext().setPrevious(temp2.getPrevious());
                size--;
                System.out.println("Removing lowest element [" + temp2.getData() + "] from the Queue.");
            }
        }
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("\nPrinting the Queue:\n");
        sb.append("[");
        DNodeB temp = head;
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
    
    class DNodeB
{
    private int data;
    public DNodeB previous;
    public DNodeB next;

    public DNodeB()
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

    public void setPrevious(DNodeB previous)
    {
        this.previous = previous;
    }

    public DNodeB getPrevious()
    {
          return previous; 
    }

    public void setNext(DNodeB next)
    {
        this.next = next;
    }

    public DNodeB getNext()
    {
        return next;
    }
}
}