public class Lab41 
{
    public static void main(String[] args)
    {
        Employee Frank = new Employee("Frank", "H3LL0-51R", 50000);
        
        Policy coffee = new Policy("Frank Jr.", "1337-H4X", 5000 );
        
        salesPerson tea = new salesPerson("Francis", 55000, "8008135", coffee);
        
        System.out.println(Frank);
        System.out.println(tea);
    }
}

class Employee
{
    protected String name;
    protected String id;
    
    protected int salary;
    
    
    public Employee(String name, String id, int salary)
    {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
    
    public String toString()
    {
        String s = name + " " + id + " " + salary + " ";
        
        return s;
    }
}

class Policy
{
    private String beneficiary;
    private String pid;
    
    private int amount;
    
    public Policy(String beneficiary, String pid, int amount)
    {
        this.beneficiary = beneficiary;
        this.pid = pid;
        this.amount = amount;
    }
    
    public String toString()
    {
        String s = beneficiary + " " + pid + " "+ amount;
        return s;
    }
}


class salesPerson extends Employee
{
    private Policy pol;
    
    public salesPerson(String name, int salary, String id, Policy pol)
    {
        super(name, id, salary);
        this.pol = pol;
    }
    
    public String toString()
    {
        String s = super.toString() + " \n" + pol;
        return s;
    }
}
