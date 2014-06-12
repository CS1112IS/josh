import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


class WordInfo {

    String originalWord;               // Store the full word here. You must do this.
    int subWordLength;                 // What is the length of each subword here. Again, you need to set this correctly.
    LinkedList<String> subWords;       // The list of subwords of that length.

    LinkedList<String> foundSubWords;  // This is all the words currently found by the user.


    // This will print out all the subwords into a string.
    public String toString ()
    {
	String s = "Subwords of string \"" + originalWord + "\" of length " + subWordLength + ": \n";
	if ( (subWords == null) || (subWords.size() == 0) ) {
	    s += "  NONE";
	    return s;
	}
	for (int i=0; i<subWords.size(); i++) {
	    s += "  " + subWords.get(i) + "\n";
	}
	return s;
    }

} // end-WordInfo



class TextTwistGUI extends JFrame {

    /////////////////////////////////////////////////////////////////////////////////
    //
    // Variables

    JTextArea textArea;             // We'll write the results into this textbox.
    JTextField stringField;         // Input a word from the user.
    JScrollPane scrollPane;         // A GUI element.
    JLabel wordLabel;               // We'll write out the word here in scrambled form.
    JLabel topLabel;                // This is where messages are written to the user.

    static String[] dictionary;
   // String[] dictionary;            // The dictionary.
    LinkedList<String> wordList;    // This is a list of words or puzzles, one per game.
    int wordListCount;              // Where we are in our list.

    String currentWord;             // The current word, unscrambled.
    WordInfo[] subWordInfo;         // For the current word, all the subword info.



    /////////////////////////////////////////////////////////////////////////////////
    //
    // Constructor. This has all the GUI building stuff.

    public TextTwistGUI ()
    {
        // Set some parameters of the frame (window) that's brought up.
        this.setSize (600, 600);
        this.setTitle ("TextTwist");
        this.setResizable (true);

        // This is how stuff is put into a frame.
	Container cPane = this.getContentPane();

	// Label at the top.
	topLabel = new JLabel("      ");
	cPane.add (topLabel, BorderLayout.NORTH);

        // Create an instance of the text area and put that in a scrollpane.
        textArea = new JTextArea ();
	scrollPane = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        cPane.add (scrollPane, BorderLayout.CENTER);
        
        // Make the controls. This has two parts.
	JPanel bottomPanel = new JPanel ();
	bottomPanel.setLayout (new GridLayout(2,1));

	JPanel topPart = new JPanel ();
	wordLabel = new JLabel ("       ");
        wordLabel.setFont (new Font ("Serif", Font.BOLD, 30));
	topPart.add (wordLabel);
	topPart.add (new JLabel ("       "));
	bottomPanel.add (topPart);

        JPanel bottomPart = new JPanel ();
        JLabel label = new JLabel ("Enter sub-word: ");
        bottomPart.add (label);
        stringField = new JTextField (10);
        bottomPart.add (stringField);
        JButton button = new JButton ("Add");
	button.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
	      {
		  handleAddButtonClick();
	      }
	  }
        );
        bottomPart.add (button);

	bottomPart.add (new JLabel ("    "));
        JButton shuffleButton = new JButton ("Shuffle");
	shuffleButton.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
	      {
		  handleShuffleButtonClick();
	      }
	  }
        );
        bottomPart.add (shuffleButton);

	bottomPart.add (new JLabel ("    "));
        JButton nextButton = new JButton ("Next word");
	nextButton.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
              {
		  handleNextButtonClick();
	      }
	  }
        );
        bottomPart.add (nextButton);
	bottomPanel.add (bottomPart);

	bottomPart.add (new JLabel ("    "));
        JButton quitButton = new JButton ("Quit");
	quitButton.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
              {
		  System.exit(0);
	      }
	  }
        );
        bottomPart.add (quitButton);

	bottomPanel.add (bottomPart);
        
        // The GUI is now built.
        cPane.add (bottomPanel, BorderLayout.SOUTH);

        // Get the list of puzzles.
	wordList = getWords();
	wordListCount = 0;
	
        // Bring up the GUI.
        this.setVisible (true);
    }
    

    LinkedList<String> getWords()
    {
	// First, the dictionary.
	dictionary = WordTool.getDictionary ("words.txt");
	LinkedList<String> list = new LinkedList<String>();
	for(int i=0;i<dictionary.length;i++)
	{
		if(dictionary[i].length()<9 && dictionary[i].length()>4)
		{
			list.add(dictionary[i]);
		}
	}

        // This is just a test for now, with just two puzzle words. 
        // What we should do is populate the list with "reasonable" 
        // words that don't have too many subwords. For example, 
        // "Conversation" is not a good word because it has too 
        // many subwords. One approach may be to fill this from
        // the dictionary with all words that are between 5 and 8
        // letters long.
	//LinkedList<String> list = new LinkedList<String>();
	//list.add ("house");
	//list.add ("theory");
	return list;
    }


    /////////////////////////////////////////////////////////////////////////////////
    //
    // Handle input.


    // When the user clicks the add button, this method gets called.

    void handleAddButtonClick ()
    {
        // Extract the string from the textfield where the user typed the strings.
        String inputStr = stringField.getText ();

        // Some sanity checks.
	if (inputStr == null) {
	    writeOutput ();
	    return;
	}
	inputStr = inputStr.trim ();
	if (inputStr.length() == 0) {
	    writeOutput ();
	    return;
	}

	// Check word against those already put up.
	int len = inputStr.length();
	if (subWordInfo[len].subWords == null) {
	    writeOutput ();
	    return;
	}

        // If this is not a subword, ignore it.
	if (! subWordInfo[len].subWords.contains (inputStr) ) {
	    writeOutput ();
	    return;
	}

        // We've got a new subword, so add that to the list of "found" subwords.
	if (subWordInfo[len].foundSubWords == null) {
	    subWordInfo[len].foundSubWords = new LinkedList<String>();
	}
	subWordInfo[len].foundSubWords.add (inputStr);

        // Put the output string in the text box.
	writeOutput ();

        // Clear the textfield.
	stringField.setText ("");
    }

    

    // Build the string that goes into the text box.

    void writeOutput ()
    {
        // Sanity check.
	if (currentWord == null) {
	    return;
	}

        // This is the string we will build.
	String s = "";

	boolean finished = true;

        // Repeat for each possible subword size.
	for (int n=3; n<= currentWord.length(); n++) {

	    int numWords = subWordInfo[n].subWords.size();
	    int numWordsFound = 0;
	    if (subWordInfo[n].foundSubWords != null) {
		numWordsFound = subWordInfo[n].foundSubWords.size();
	    }
	    if (numWordsFound != numWords) {
		finished = false;
	    }
	    s += "Found " + numWordsFound + " words out of " + numWords + " words of length " + n + ":\n";
	    if (subWordInfo[n].foundSubWords != null) {
		for (int i=0; i<subWordInfo[n].foundSubWords.size(); i++) {
		    String w = subWordInfo[n].foundSubWords.get(i);
		    s += "  " + w + "\n";
		}
	    }	    
	    s += "\n";

	} //end-for

	if (finished) {
	    topLabel.setText ("Congratulations! You found all the words!");
	}
	textArea.setText (s);
    }


    // This is simple: every time the user hits the "shuffle" button,
    // we randomly permute the letters and write it back.

    void handleShuffleButtonClick ()
    {
	setPermutedWordLabel ();
    }


    void setPermutedWordLabel ()
    {
	String perm = permute (currentWord);
        // We may have to repeat a couple of times.
	while ( perm.equalsIgnoreCase (currentWord) ) {
	    perm = permute (currentWord);
	}
	wordLabel.setText (perm);
    }


    String permute (String word)
    {
        // Random permutation.
    	LinkedList<String> scrambledWords = new LinkedList();

    	char[] wordArray = word.toCharArray();

    	char[] scramble = new char [wordArray.length];
    			for(int m = 0;m<wordArray.length;m++){
    				scramble[m]='@';
    			}

    	creatPermutations (scramble.length, scramble.length, scramble, wordArray, 0, wordArray, scrambledWords);

    	int random = (int )(Math.random() * scrambledWords.size());

    	String returnValue = new String(scrambledWords.get(random));

    	return returnValue;

        // INSERT YOUR CODE HERE.
    }



    void handleNextButtonClick ()
    {
	textArea.setText ("");
	topLabel.setText ("");

        // Get the next word.
        if (wordListCount >= wordList.size()) {
            // No more words left.
            topLabel.setText ("Game over. No more puzzles left");
            return;
        }
        
	currentWord = wordList.get (wordListCount);
        wordListCount ++;

        topLabel.setText ("Building subwords ... wait...");
        
	// Compute all sub-words.
	subWordInfo = findSubWords (currentWord);

	// Make a random perm and display it.
	setPermutedWordLabel ();
        topLabel.setText ("  ");
    }


    boolean isDictionaryWord (String word)
    {
	for (int i=0; i<dictionary.length; i++) {
	    if ( dictionary[i].equalsIgnoreCase (word) ) {
		return true;
	    }
	}
	return false;
    }

    // INSERT YOUR CODE HERE
    static WordInfo[] findSubWords (String thisWord)
    {
        char[]  myWordChar = thisWord.toCharArray();

        WordInfo[] subWordInfo = new WordInfo[myWordChar.length+1];

        for(int i=0;i<subWordInfo.length;i++)
        {
            if(i<3){
            subWordInfo[i]=null;   
            }else{
            subWordInfo[i] = new WordInfo();
            subWordInfo[i].originalWord = thisWord;
            subWordInfo[i].subWordLength = i;
            LinkedList subWords = new LinkedList();
            
            breakDown(myWordChar,i ,subWords);

            subWordInfo[i].subWords = subWords;
            }
        }

        return subWordInfo;
    }
    // Check if a given string is in the dictionary.
    // You will find this useful to check whether a given
    // arrangement of letters is in the dictionary.

    // INSERT YOUR CODE HERE

    static void breakDown (char[] word, int subWordSize, LinkedList<String> realWords)
    {
    	if(subWordSize==word.length-1)
    	{
    		for(int i=0;i<word.length;i++)
    		{
    		
    			char[] subWord = new char [subWordSize];
    			for(int j=0;j<subWordSize;j++)
    			{
    				int position = j+i;
    				if(position>=word.length){
    					position = (Math.abs(word.length-position));
    				}

    				subWord[j]=word[position];
    			}
    			//call printpermutations
    			char[] numSubWord = new char [subWordSize];
    			for(int m = 0;m<subWordSize;m++){
    				numSubWord[m]='@';
    			}
    			printPermutations (subWordSize, subWordSize, numSubWord, subWord, 0, word, realWords);

    		}
    	}else if(subWordSize==word.length){
    		char[] numSubWord = new char [subWordSize];
    			for(int m = 0;m<subWordSize;m++){
    				numSubWord[m]='@';
    			}
    		printPermutations (subWordSize, subWordSize, numSubWord, word, 0, word, realWords);
    	}else{
            for(int i=0;i<word.length;i++)
            {
            
                char[] subWord = new char [word.length-1];
                for(int j=0;j<word.length-1;j++)
                {
                    int position = j+i;
                    if(position>=word.length){
                        position = (Math.abs(word.length-position));
                    }

                    subWord[j]=word[position];
                }
                //call breakdown recursively
                breakDown(subWord,subWordSize,realWords);

                /*
                //call printpermutations
                char[] numSubWord = new char [word.length-1];
                for(int m = 0;m<word.length-1;m++){
                    numSubWord[m]='@';
                }
                printPermutations (subWordSize, subWordSize, numSubWord, subWord, 0, word, realWords);
                */
            }
        }
    }

    static void printPermutations (int numSpaces, int numRemaining, char[] seats, char[] person, int personNum, char[] word, LinkedList<String> realWords)
    {
        // Bottom-out case. Note that we are printing here, since each time 
        // we get here we complete one permutation.
	if (numRemaining == 0) 
    {

	    // Print.
    	dictionary = WordTool.getDictionary ("words.txt");
    	boolean inDictionary = false;



	    String permutation = new String(seats);

	    for (int i=0; i<dictionary.length; i++) {
	    	if ( dictionary[i].equalsIgnoreCase (permutation) ) {
			inDictionary = true;
	    	}
	    }

	    if(inDictionary && !realWords.contains(permutation))
	    {
	    	realWords.add(permutation);
            //change test to permutation
            //System.out.println(permutation);
	    }
	    //System.out.println(permutation);
	    //System.out.println ( Arrays.toString(seats) );
	    
            // Remember to increment the number of permutations found.
	    //count ++;

	    return;
	}


	// Otherwise, non-base case: look for an empty spot for "person"
	for (int i=0; i < seats.length; i++) {
	    if (seats[i] == '@') {

		// Empty spot.
		seats[i] = person[personNum];

                // Recursively assign remaining, starting with person+1
		printPermutations (numSpaces-1, numRemaining-1, seats, person, personNum+1, word, realWords);

                // Important: we need to un-do the seating for other trials.
		seats[i] = '@';
	    }
	} //end-for
    }


static void creatPermutations (int numSpaces, int numRemaining, char[] seats, char[] person, int personNum, char[] word, LinkedList<String> realWords)
    {
        // Bottom-out case. Note that we are printing here, since each time 
        // we get here we complete one permutation.
	if (numRemaining == 0) 
    {

	    // Print.

	    String permutation = new String(seats); 
	   	realWords.add(permutation);

	    
	    //System.out.println(permutation);
	    //System.out.println ( Arrays.toString(seats) );
	    
            // Remember to increment the number of permutations found.
	    //count ++;

	    return;
	}


	// Otherwise, non-base case: look for an empty spot for "person"
	for (int i=0; i < seats.length; i++) {
	    if (seats[i] == '@') {

		// Empty spot.
		seats[i] = person[personNum];

                // Recursively assign remaining, starting with person+1
		printPermutations (numSpaces-1, numRemaining-1, seats, person, personNum+1, word, realWords);

                // Important: we need to un-do the seating for other trials.
		seats[i] = '@';
	    }
	} //end-for
    }


}


public class TextTwist {

    public static void main (String[] argv)
    {
	TextTwistGUI t = new TextTwistGUI ();
    }

}
