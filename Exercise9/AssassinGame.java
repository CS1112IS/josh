
class ListItem {

    String data;
    ListItem next;

    public ListItem()
    {
        String info = "";
        String nextNode = "";
    }

}


class CircularList {

    // The usual list variables.
    ListItem front = null;
    ListItem rear = null;

    // To keep track of the size.
    int numItems = 0;


    public void add (String s)
    {
        ListItem temp = new ListItem();
        if(numItems == 0)
        {
            front = new ListItem();
            front.data = s;
            numItems++;
        }else{
            temp = front;
            while(temp.next!=null && temp!=rear){
                temp=temp.next;
            }
            temp.next = new ListItem();
            temp.next.data = s;
            rear = temp.next;
            rear.next = front;
            numItems++;
        }
    }


    public int size ()
    {
        return numItems;
    }

    /*
    public String toString ()
    {
    }
    */

    public void printList ()
    {
        ListItem temp = front;
        for(int i=0;i<numItems;i++)
        {
            System.out.println(temp.data);
            temp = temp.next;
        }
        //System.out.println(temp.data);
        //System.out.println(temp.next.data);
        //System.out.println(temp.next.next.data);
    }

    public String fire (String assassin)
    {
        //System.out.println("in");
        ListItem temp = front;
        boolean stringEqual = temp.data.equals(assassin);
        int count = 0;
        while(!stringEqual && count < numItems)
        {
            temp = temp.next;
            stringEqual = temp.data.equals(assassin);
            //System.out.println(count);
            count++;
        }
        if(count==numItems)
        {
           return "Error. No such Assasin";
        }else{
            String returnValue = temp.next.data;
            //System.out.println(temp.data + "=current assassin");
            //System.out.println(temp.next.data + "=victim");
           if(temp.next == front)
            {
                rear = temp;
                front = rear.next.next;
                rear.next = front;
                
                //temp.next.data = front.data;
                //System.out.println(temp.next.data);
                //System.out.println(temp.next.next.data);
                //temp.next.data = temp.next.next.data;
                //System.out.println(temp.next.data + "=new victim");
            }else{
            temp.next = temp.next.next;
            }
            numItems--;
            //System.out.println("out");
            return returnValue;
        }
    }

}


public class AssassinGame {

    public static void main (String[] argv)
    {
        CircularList assassins = new CircularList ();
        assassins.add ("Jackal");
        assassins.add ("Mata Hari");
        assassins.add ("John Wilkes Booth");
        assassins.add ("Lee Harvey Oswald");
        assassins.add ("Gavrilo Princip");
        assassins.add ("James Earl Ray");
        assassins.add ("Jack Ruby");

        //System.out.println (assassins);
        assassins.printList();

        String victim = assassins.fire ("Gavrilo Princip");
        System.out.println ("\nGavrilo's victim: " + victim + "\n  Remaining: " );
        assassins.printList();
        // Gavrilo's victim: James Earl Ray

        victim = assassins.fire ("Jack Ruby");
        System.out.println ("\nJack's victim: " + victim + "\n  Remaining: " );
        assassins.printList();
        // Jack's victim: Jackal

        victim = assassins.fire ("Mata Hari");
        System.out.println ("\nMata's victim: " + victim + "\n  Remaining: " );
        assassins.printList();
        // Mata's victim: John Wilkes Booth

        victim = assassins.fire ("Jackal");
        System.out.println ("\nJackal's victim: " + victim + "\n  Remaining: " );
        assassins.printList();
        // Victim: Error: no such assassin.

        victim = assassins.fire ("Jack Ruby");
        System.out.println ("\nJack's victim: " + victim + "\n  Remaining: " );
        assassins.printList();
        // Jack's second victim: Mata Hari
    }
    

}
