
import java.util.*;

public class ParenBalancingExercise {

    public static void main (String[] argv)
    {
	String s = "((()))";
	checkParens (s);

	s = "((())";
	checkParens (s);

	s = "())))";
	checkParens (s);

	s = "(()()(";
	checkParens (s);
    }


    static void checkParens (String inputStr)
    {
    	// Extract letters from String.
		char[] letters = inputStr.toCharArray();

		Stack<Node> stack = new Stack<Node> ();
		Node remaining = new Node();
		boolean unbalanced = false;


		for (int i=0; i<letters.length; i++) {

	    if (letters[i] == '(') {
		// Push left paren.
	    Node temp = new Node();
	    temp.data = letters[i];
	    temp.index = i;
	    remaining = temp;
		stack.push (temp);
		//System.out.println(stack.peek().index);
	    }
	    else if (letters[i] == ')') {
		// Right paren: we should have a match on the stack.
	    Node temp = new Node();
	    temp.data = letters[i];
	    temp.index = i;


	    Node ch = new Node();
	    if(stack.empty())
	    {
	    	ch.data = '@';
	    	ch.index = 15;
	    }else{

			ch = stack.pop ();
	    }

		//System.out.println(stack.peek().index);
		if (ch.data != '(' ) {
		    // Not a match.
		    remaining = temp;
		    unbalanced = true;
		    break;
		}
	    }
		}	

	    if (unbalanced) {
	    //print out unbalanced
	    	//System.out.println("Unbalanced break");
	    	Node temp = new Node();
	    	temp = remaining;

	    	char[] carrat = new char[letters.length];

	    	for(int i=0;i<letters.length;i++)
	    	{
	    		if(i==temp.index)
	    		{
	    			carrat[i] = '^';
	    		}else{
	    			carrat[i] = ' ';
	    		}
	    	}

	    
	    	String strCar = new String(carrat);
	    	System.out.println("Unbalanced: " + inputStr);
	    	System.out.println("            " + strCar);
		}else if (stack.empty()==false){
	    	//print first element
	    	//System.out.println("Stack isn't empty");
	    	//System.out.println(stack.peek().index);
	    	//System.out.println(stack.isEmpty());
	    	Node temp = new Node();
	    	temp = stack.pop ();

	    	char[] carrat = new char[letters.length];
	   
	    	for(int i=0;i<letters.length;i++)
	    	{
	    		if(i==temp.index)
	    		{
	    			carrat[i] = '^';
	    		}else{
	    			carrat[i] = ' ';
	    		}
	    	}

	    	String strCar = new String(carrat);
	    	//System.out.println(stack.peek().data);
	    	System.out.println("Unbalanced: " + inputStr);
	    	System.out.println("            " + strCar);
		}else{
			//print balanced
			System.out.println("Balanced: " + inputStr);
		}



    }

}
