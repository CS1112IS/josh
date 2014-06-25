
import java.util.*;

public class WordMorph {

    static String[] words;                 // English dictionary (lowercase).

    static LinkedList<LinkedList<String>> results;     // The result.


    static LinkedList<LinkedList<String>> findLinks (String startWord, String endWord, int numSteps)
    {
        // Read in the dictionary.
        words = WordTool.getDictionary("words.txt");
        
        results = new LinkedList<LinkedList<String>>();

        // INSERT PART OF YOUR CODE HERE to call the recursive method
        LinkedList<String> wordList = new LinkedList<String>();
        wordList.add(startWord);
        findLinksRecursive(startWord,endWord,numSteps,wordList);

        return results;
    }

    static void findLinksRecursive (String startWord, String endWord, int numSteps, LinkedList<String> wordList)
    {
        if(numSteps==0){
            if(wordList.peekLast().equals(endWord)){
                results.add(wordList);
                return;
            }
            return;
        }

        /////////////////////////////////////////////

        for(int i=0;i<words.length;i++)
        {
            if(offByOne(startWord,words[i]))
            {
                String tempWord = words[i];
                LinkedList<String> tempList = copy(wordList);
                tempList.add(tempWord);
                //System.out.println(tempList);
                findLinksRecursive(tempWord,endWord,numSteps-1,tempList);
            }
        }
    }
    // INSERT YOUR CODE HERE (for the recursive method)


    static boolean offByOne (String word1, String word2) 
    {
        // INSERT YOUR CODE HERE.

        // Return true if the two words are of the same length
        // and differ by exactly one letter.

        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();

        int count = 0;

        if(word1Array.length==word2Array.length){
            for(int i=0;i<word1Array.length;i++){
                if(word1Array[i]==word2Array[i]){
                    count++;
                }
            }

            if(count==word1Array.length-1){
                return true;
            }
            return false;
        }
        return false;
    }
    
    

    // You might or might not find this method useful.

    static LinkedList<String> copy (LinkedList<String> list)
    {
        LinkedList<String> copyList = new LinkedList<String>();
        for (String s: list) {
            copyList.add (s);
        }
        return copyList;
    }
    

    public static void main (String[] argv)
    {
        /*
        System.out.println(offByOne("car","cat"));
        System.out.println(offByOne("whale","white"));
        System.out.println(offByOne("caes","caad"));
        System.out.println(offByOne("care","cat"));
        System.out.println(offByOne("caad","caes"));
        */

               
        // Test 
        LinkedList<LinkedList<String>> results = findLinks ("east", "west", 4);
        print (results);

        // Test 2:
        results = findLinks ("lead", "gold", 4);
        print (results);

        // Test 3:
        results = findLinks ("cat", "dog", 4);
        print (results);

        
    }

    
    static void print (LinkedList<LinkedList<String>> listOfLists)
    {
        int count = 0;
        for (LinkedList<String> list: listOfLists) {
            for (String s: list) {
                System.out.print ("  " + s);
            }
            System.out.println ();
            count ++;
        }
        System.out.println (" => " + count + " different morphs");
    }

    static void printTest (LinkedList<LinkedList<String>> listOfLists)
    {
        //int count = 0;
        for (LinkedList<String> list: listOfLists) {
            for (String s: list) {
                System.out.print ("  " + s);
            }
            System.out.println ();
            //count ++;
        }
        //System.out.println (" => " + count + " different morphs");
    }
    

}
