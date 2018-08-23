/*********************************************
*        Student Name: Matthew Dunne         *
*      Student Number: 20087584              *
*              Group : 5N/50                 *
*          Instructor: Kashif Amjad          *
*              Class : CT 2530               *
*********************************************/

public class TestLRU
{
    public static void main(String[] args)
    {
        LRU test = new LRU(5);
        
        test.reference(3);
        test.reference(5);
        test.reference(1);
        test.reference(1);
        test.reference(4);
        test.reference(5);
        test.reference(1);
        test.reference(3);
        test.reference(2);
        test.reference(4);
        
        test.print();
    }
}