import java.util.*;

public class StackImpl2 implements OurStackInterface{

	LinkedList<Character> letters = new LinkedList<Character>();
	int top=-1;

	public void push(char x){
		top++;
		letters.add(x);

	}

	public boolean isEmpty(){
		if(top==-1){
			return true;
		}
		return false;
	}
	
	public char pop(){
		if (top==0){
			System.out.println("There is nothing to pop");
			return '@';
		}
		char temp = letters.get(top);
		top--;
		return temp;
	}

}
