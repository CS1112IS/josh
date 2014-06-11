
import java.util.*;


// Use an instance of this for all subwords of a certain length.
// Thus, one instance of this for subwords of length 3, one instance
// for subwords of length 4 etc.

class WordInfo {

    String originalWord;            // Store the full word here. You must do this.
    int subWordLength;              // What is the length of each subword here. Again, you need to set this correctly.
    LinkedList<String> subWords;    // The list of subwords of that length.

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



public class SubWordFinder {

    static String[] dictionary;

    public static void main (String[] argv)
    {
	// Read in the dictionary. Should be used later to 
	// check whether a given letter combination is a real word.
	dictionary = WordTool.getDictionary("words.txt");

	// The method findSubWords returns an array of WordInfo
	// instances. Thus, subWordInfo[3] is an instance of 
	// WordInfo that has all the info about subwords of length 3.
	//
	WordInfo[] subWordInfo = findSubWords ("house");
	for (int i=3; i<=5; i++) {
	    System.out.println (subWordInfo[i]);
	}


	//char[] testWord = "house".toCharArray();

	//breakDown(testWord,3);
	//breakDown(testWord,4);
	//breakDown(testWord,5);
    }

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

    static boolean isDictionaryWord (String word)
    {
	for (int i=0; i<dictionary.length; i++) {
	    if ( dictionary[i].equalsIgnoreCase (word) ) {
		return true;
	    }
	}
	return false;
    }


    // A variation of the above method that might be more useful.

    static boolean isDictionaryWord (char[] letters)
    {
	String s = new String (letters);
	return isDictionaryWord (s);
    }


    // INSERT YOUR CODE HERE

    static void breakDown (char[] word, int subWordSize, LinkedList<String> realWords)
    {
    	if(subWordSize<word.length)
    	{
    		for(int i=0;i<word.length+1;i++)
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
    	}else{
    		char[] numSubWord = new char [subWordSize];
    			for(int m = 0;m<subWordSize;m++){
    				numSubWord[m]='@';
    			}
    		printPermutations (subWordSize, subWordSize, numSubWord, word, 0, word, realWords);
    	}
    }

    static void printPermutations (int numSpaces, int numRemaining, char[] seats, char[] person, int personNum, char[] word, LinkedList<String> realWords)
    {
        // Bottom-out case. Note that we are printing here, since each time 
        // we get here we complete one permutation.
	if (numRemaining == 0) {

	    // Print.

	    String permutation = new String(seats);
	    if(isDictionaryWord(permutation))
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

} // end-SubWordFinder class



