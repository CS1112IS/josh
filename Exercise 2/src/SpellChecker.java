import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SpellChecker extends JFrame {

    JTextArea textArea;
    JTextField stringField;
    JScrollPane scrollPane;

    String[] words;

    public SpellChecker ()
    {
        // Set some parameters of the frame (window) that's brought up.
        this.setSize (600, 600);
        this.setTitle ("Spell checker");
        this.setResizable (true);

        // This is how stuff is put into a frame.
	Container cPane = this.getContentPane();
        textArea = new JTextArea ();
	scrollPane = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        cPane.add (scrollPane, BorderLayout.CENTER);
        
        // Make the controls.
        JPanel panel = new JPanel ();
        JLabel label = new JLabel ("Enter string: ");
        panel.add (label);
        stringField = new JTextField (30);
        panel.add (stringField);
        JButton button = new JButton ("Go");
	button.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
	      {
		  handleButtonClick();
	      }
	  }
        );
        panel.add (button);
        cPane.add (panel, BorderLayout.SOUTH);

	// Read in dictionary.
	words = WordTool.getDictionary ();
        
        this.setVisible (true);
    }
    

    String inputStr;


    // When the user clicks the button, this method gets called.
    // It's where we need to respond.

    void handleButtonClick ()
    {
        // Extract the string from the textfield where the user typed the strings.
        inputStr = stringField.getText ();

	String[] matchedWords = findClosestWords (inputStr, words);

	String outputStr = "";

	if (matchedWords ==  null) {
	    outputStr = "No matches found";
	}
	else {
	    for (int i=0; i<matchedWords.length; i++) {
		outputStr += matchedWords[i] + "\n";
	    }
	}

        // Put the output string in the text box.
	String text = textArea.getText ();
	text += outputStr + "\n";
	textArea.setText (text);
    }


    static String[] findClosestWords (String inputWord, String[] words)
    {
    	//create string array of arbitrary length to store close words in
    	String[] result = new String[20];
    	//create integer to fill in result array
    	int count = 0;
    	//run through every word in dictionary
    	for(int i=0; i<words.length;i++){
    		switch (checkLength(inputWord,words[i])){
    		case 0:
    			//words are equal length
    			if(checkEqual (inputWord, words[i])){
    				result[count]=words[i];
    				count++;
    			}
    			break;
    		case 1:
    			//dictionary word longer
    			if(checkDifEqual1 (inputWord, words[i])){
    				result[count]=words[i];
    				count++;
    			}
    			break;
    		case 2:
    			//input word longer
    			if(checkDifEqual2 (inputWord, words[i])){
    				result[count]=words[i];
    				count++;
    			}
    			break;
    		case 3:
    			result[count]="Doesn't match";
    			count++;
    		default:
				result[count]="default";
				count++;
				continue;
    		}
    	}
    	return result;
    }
    
    /*Run this if CHECKLENGTH returns 2 (letters(dictionary words) 1 < than input Word)
     * Code checks to see if both strings vary by more than one letter.
     * This is done by first making both strings equal length by assigning "@" at the end of the short word
     * Then check to see if characters are equal 
     */
    static boolean checkDifEqual2 (String inputWord, String words){
    	//initialize variables and char arrays
    	boolean result = true;
    	int count = 0;
    	int A = 0; //used in for loop for input
    	int B = 0; //used in for loop for letters
    	words+='@';
    	char[] input = inputWord.toCharArray ();
    	char[] letters = words.toCharArray ();
    	for(int i=0;i<letters.length;i++){
    		if((A<input.length) && (B<input.length)){
    			if(input[A]==letters[B]){
    				count+=0;
    				A++;
    				B++;
    			}else if(input[A]!=letters[B]){
    				count++;
    				A++;
    			}
    		}else{
    			continue;
    		}
    	}
    	if(count<2){
    		result=true;
    	}else{
    		result=false;
    	}
    	return result;
    	
    	
    }
    
    
    /*Run this if CHECKLENGTH returns 1 (letters(dictionary words) 1 > than input Word)
     * Code checks to see if both strings vary by more than one letter.
     * This is done by first making both strings equal length by assigning "@" at the end of the short word
     * Then check to see if characters are equal 
     */
    static boolean checkDifEqual1 (String inputWord, String words){
    	//initialize variables and char arrays
    	boolean result = true;
    	int count = 0;
    	int A = 0; //used in for loop for input
    	int B = 0; //used in for loop for letters
    	inputWord+='@';
    	char[] input = inputWord.toCharArray ();
    	char[] letters = words.toCharArray ();
    	for(int i=0;i<letters.length;i++){
    		if((A<letters.length) && (B<letters.length)){
    			if(input[A]==letters[B]){
    				count+=0;
    				A++;
    				B++;
    			}else if(input[A]!=letters[B]){
    				count++;
    				B++;
    			}
    		}else{
    			continue;
    		}
    	}
    	if(count<2){
    		result=true;
    	}else{
    		result=false;
    	}
    	return result;
    	
    	
    }
    /*
     * Check to see if EQUAL SIZE WORDS match or are off by 1 letter
     * if return 0, words match exactly
     * if return 1, words off by 1 letter
     * if return 2 or more, words off by 2 or more letters
     * 
     * 0 and 1 turned into boolean true
     * 2 turned into boolean false
     */
    static boolean checkEqual (String inputWord, String words){
    	//initialize variables and char arrays
    	boolean result = true;
    	int count = 0;
    	char[] input = inputWord.toCharArray ();
    	char[] letters = words.toCharArray ();
    	//for loop to check if each letter matches
    	for (int i = 0;i<letters.length;i++){
    		if(input[i]!=letters[i]){
    			//if letter doesn't match, add 1 to count
    			count+=1;
    		}else{
    			count+=0;
    		}
    	}
    	//convert count to boolean: 1&0 true, everything else false
    	if(count==0||count==1){
    		result=true;
    	}else{
    		result=false;
    	}
    	return result;
    }
    
    /*
     * Check to see if length of two words is equal or close
     * if return 0, length =
     * if return 1, letters(dictionary words) 1 > than input Word 
     * if return 2, input word 1 > than letters(dictionary words)
     * if return 3, length does not match
     */
    static int checkLength (String inputWord, String words){
    	//initialize variables for return value and char array for input word
    	int result = 0;
    	char[] input = inputWord.toCharArray ();
    	char[] letters = words.toCharArray ();
    	//if else statements to check length
    	if(input.length==letters.length){
    		result=0;
    	}else if((input.length-1)==letters.length){
    		result=2;
    	}else if(input.length==(letters.length-1)){
    		result=1;
    	}else{
    		result=3;
    	}
    	return result;
    }

    static void test ()
    {
	String inputStr = "asdf";
	String[] words = {"asdf", "aadf", "bsdf", "asdd", "aadd", "asdff", "aasdf", "asd", "assdf","sdf"};
	String[] matched = findClosestWords (inputStr, words);
	print (inputStr, matched);
    }

    static void print (String word, String[] matchedWords)
    {
	System.out.println ("Words that matched: " + word);
	for (int i=0; i<matchedWords.length; i++) {
	    System.out.println ("  " + matchedWords[i]);
	}
    }

    public static void main (String[] argv)
    {
	test ();
	//SpellChecker checker = new SpellChecker ();

    }

}