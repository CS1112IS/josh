import java.util.*;

public class StackImpl1 implements OurStackInterface{

	char[] letters= new char[30];
	int top=-1;

	public void push(char x){
		top++;
		letters[top]=x;
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
		char temp = letters[top];
		top--;
		return temp;
	}

}
