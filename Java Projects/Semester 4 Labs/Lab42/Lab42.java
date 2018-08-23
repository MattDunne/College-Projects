
import java.util.*;

public class Lab42 
{
    public static void main(String[] args)
    {
        double depositCash;
        double withdrawlCash;
        
        Account[] A = new Account[2];
        
        Saving save = new Saving();
        
        Checking check =  new Checking();
        
        A[0] = save;
        A[1] = check;
        
        Scanner input = new Scanner(System.in);
        
        
        while(true)
        {
            try
            {
                System.out.println("Enter amount to deposit into each account: ");
                depositCash = input.nextDouble(); 
                break;
            }
            catch(InputMismatchException e)
            {
                System.out.println("please input a double value.");
                input.next();
            }
        }
        
        while(true)
        {
            try
            {
                System.out.println("Enter amount to withdrawl into each account: ");
                withdrawlCash = input.nextDouble(); 
                break;
            }
            catch(InputMismatchException e)
            {
                System.out.println("please input a double value.");
                input.next();
            }
        }
        
        System.out.println("\n");
        
        for(int i = 0; i < A.length; i++)
        { 
	    if(i == 0)
            {
                System.out.println("Saving account:");
                System.out.println("---------------");
            }
            if(i == 1)
            {
                System.out.println("Checking account:");
                System.out.println("-----------------");
            }
            
            A[i].deposit(depositCash);
	    
            A[i].withdrawl(withdrawlCash);
	    
            A[i].dailyInterest();
	    
            A[i].show();
            
            System.out.println("\n");
	}
        
    }
}

abstract class Account
{
    protected double amount;
    
    public Account()
    {
        this.amount = 0.00;
    }
    
    public void deposit(double m)
    {
        this.amount = this.amount + m;
        System.out.println("Deposited: "+ this.amount);
    }
    
    public void withdrawl(double m)
    {
        System.out.println("Attempted withdrawl: " + m);
        
        if(this.amount < m)
        {
            System.out.println("Insufficient funds.");
        }
        
        else
        {
            System.out.println("Sufficient funds, withdrawl successful.");
            this.amount = this.amount - m;
        }
    }
    
    abstract public void show();
    abstract public double dailyInterest();
}

class Saving extends Account
{
    public Saving()
    {
        super();
    }
    
    public double dailyInterest()
    {
        this.amount = this.amount * 1.06;
        return amount;
    }
    
    public void show()
    {
        System.out.println("Remaining account balance: " + amount);
    }
}

class Checking extends Account
{
    public Checking()
    {
        super();
    }
    
    public double dailyInterest()
    {
        if(amount > 1000)
        {
            System.out.println("This individual DOES qualify for interest.");
            double number = amount - 1000;
            number = number * 1.03;
            number = amount + number;
        }
        
        else
        {
            System.out.println("This individual DOES NOT qualify for interest.");
        }
        
        return amount;
    }
    
    public void show()
    {
        System.out.println("Remaining account balance: " + amount);
    }
}