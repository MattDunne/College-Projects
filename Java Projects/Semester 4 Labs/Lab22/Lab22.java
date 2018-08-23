import java.io.*;
import java.util.Scanner;

class MyInteger
{
    private int number = 0;
    
    public MyInteger()
    {
        
    }
    
    public MyInteger(int number)
    {
        this.number = number;
    }
    
    public String toString()
    {
        String s = "number: " + number + "\n" +
                   "Even: " + isEven() + "\n" +
                   "Odd: " + isOdd() + "\n" +
                   "Prime: " + isPrime() + "\n" +
                   "Perfect: " + isPerfect() + "\n" +
                   "Perfect Square: " + isPerfectSquare();
        
        return s;
    }
    
    public boolean isEven()
    {
        boolean answer = false;
        int result = number % 2;
        if(result == 0)
        {
            answer = true;
        }
        return answer;
    }
    
    public boolean isOdd()
    {
        boolean answer = false;
        int result = number % 2;
        if(result != 0)
        {
            answer = true;
        }
        return answer;
    }
    
    public boolean isPrime()
    {
        boolean answer = true;
        int temp;
        
        for(int i = 2;i <= number/2;i++)
        {
            temp = number % i;
            if(temp == 0)
            {
                answer = false;
                break;
            }
        }
        return answer;
    }
    
    public boolean isPerfect()
    {
        boolean answer = false;
        int temp = 0;
        
        for(int i = 1;i < number;i++)
        {
            if(number % i == 0)
            {
                temp = temp + i;
            }
        }
        
        if(temp == number)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }
        return answer;
    }
    
    public boolean isPerfectSquare()
    {
        boolean answer = false;
        int temp = (int)Math.sqrt(number);
        
        if(temp*temp == number)
        {
            answer = true;
        }
         return answer;
    }
    
    public void primeFactors()
    {
        int temp = 2;
        int temp2 = number;
        
        System.out.println("The prime factors of this number are: ");
        
        while(temp2 > 1)
        {
            if(temp2 % temp == 0)
            {
                System.out.print(temp + " ");
                temp2 = temp2 / temp;
            }
            else
            {
                temp++;
            }
        }
        
        System.out.println("\n");
    }
    
    
    
}



class Lab22 
{
    public static void main(String[]args)
    {
        int user;
        Scanner input = new Scanner(System.in);


        MyInteger a = new MyInteger();
        MyInteger b = new MyInteger(24);
        MyInteger c = new MyInteger(9);
        MyInteger d = new MyInteger(13);
        MyInteger e = new MyInteger(28);
        
        
        System.out.println(a);
        a.primeFactors();
        System.out.println(b);
        b.primeFactors();
        System.out.println(c);
        c.primeFactors();
        System.out.println(d);
        d.primeFactors();
        System.out.println(e);
        e.primeFactors();

        System.out.println("input your own number to test: ");
        user = input.nextInt();
        
        MyInteger f = new MyInteger(user);
        System.out.println(f);
        f.primeFactors();
    }
    
    
}