
public class WordSearchPuzzle {

	static int x = 0;
	static int y = 0;
	static int tempX = 0;
	static int tempY = 0;
	static char[] testWord = new char[6];
	
    public static void main (String[] args){
    
        char[][] puzzle = {
        {'v', 'h', 'n', 'b', 'u', 'b', 'q', 's', 'b', 'r'},
        {'p', 'k', 'j', 'w', 's', 'y', 'a', 'd', 'd', 'o'},
	    {'y', 'c', 'e', 's', 'd', 'r', 'n', 'c', 'e', 'k'},
	    {'d', 'd', 'a', 'e', 't', 'w', 'r', 'z', 'v', 'x'},
	    {'g', 'l', 'g', 'a', 'l', 'a', 'u', 'b', 'r', 't'},
	    {'c', 'n', 'c', 'f', 'z', 's', 't', 'd', 'n', 'l'},
	    {'w', 'o', 'w', 'h', 'i', 'l', 'e', 'i', 'g', 'b'},
	    {'h', 'y', 'm', 'j', 'h', 'k', 'r', 'o', 'c', 'e'},
	    {'n', 'n', 's', 'j', 'k', 'm', 'g', 'v', 'u', 'm'},
	    {'v', 'v', 'j', 'y', 'y', 'c', 'u', 'e', 'v', 'z'}
        };
        String[] words = {"class", "else", "int", "return", "static", "void", "while"};
        findWords (puzzle, words);
    }



    static void findWords (char[][] puzzle, String[] words)
    {
        // INSERT YOUR CODE HERE AND IN OTHER METHODS.
    	//System.out.println(puzzle[tempY][tempX]);
    	//move(puzzle,"upRight");
    	//System.out.println(puzzle[tempY][tempX]);
    	
    	for(int i=0;i<(puzzle.length*puzzle.length);i++){
    		if(checkFirstLetter(puzzle,words)){
    			checkFollowingLetters(puzzle);
    		}
    		nextPlace();
    	}
    	
    	
    	//move(puzzle,upRight);
    	
    	/*
    	System.out.println(y);
    	System.out.println(x);
    	System.out.println(move(puzzle,"up"));
    	System.out.println(y);
    	System.out.println(x);
    	*/
    }
    /*
     * Create method that moves through the puzzle in one of 8 directions
     * It takes in a direction, changes it into an integer, and then chooses a case
     */
    static char move (char[][] puzzle, String direction){
    	/*create int to convert string to ints for cases
    	 * create char to store letter at new position
    	 */
    	int checkDirection = 0;
    	char result=' ';
    	//if statements to convert string to ints
    	if(direction.equals("up")){
    		checkDirection = 1;
    	}else if(direction.equals("down")){
    		checkDirection = 2;
    	}else if(direction.equals("left")){
    		checkDirection = 3;
    	}else if(direction.equals("right")){
    		checkDirection = 4;
    	}else if(direction.equals("upLeft")){
    		checkDirection = 5;
    	}else if(direction.equals("upRight")){
    		checkDirection = 6;
    	}else if(direction.equals("downLeft")){
    		checkDirection = 7;
    	}else if(direction.equals("downRight")){
    		checkDirection = 8;
    	}
    	
    	//start switch statements
    	switch (checkDirection){
		case 1:
			//up
			//System.out.println("up");
			result = puzzle[tempY-1][tempX];
			tempY--;
			break;
		case 2:
			//down
			//System.out.println("down");
			result = puzzle[tempY+1][tempX];
			tempY++;
			break;
		case 3:
			//left
			//System.out.println("left");
			result = puzzle[tempY][tempX-1];
			tempX--;
			break;
		case 4:
			//right
			//System.out.println("right");
			result = puzzle[tempY][tempX+1];
			tempX++;
			break;
		case 5:
			//upLeft
			//System.out.println("up Left");
			result = puzzle[tempY-1][tempX-1];
			tempY--;
			tempX--;
			break;
		case 6:
			//upRight
			//System.out.println("up Right");
			result = puzzle[tempY-1][tempX+1];
			tempY--;
			tempX++;
			break;
		case 7:
			//downLeft
			//System.out.println("down Left");
			result = puzzle[tempY+1][tempX-1];
			tempY++;
			tempX--;
			break;
		case 8:
			//downRight
			//System.out.println("down Right");
			result = puzzle[tempY+1][tempX+1];
			tempY++;
			tempX++;
			break;
		default:
			System.out.println("Defaulted");
    	}
    	return result;
    }
    /*
     * create method to move to next position in the wordsearch
     * when it reaches the end of a row, it sets x to 0 and y to y+1
     * If it is at the last position of the word search, it returns "Finished searching"
     */
   static void nextPlace (){
	   if(x==9 && y==9){
		   System.out.println("Finished Searching");
	   }else if(x<= 8){
		   x++;
		   //System.out.println("next place");
	   }else{
		   x=0;
		   y++;
		   //System.out.println("next line");
	   }
   }
   /*
    * create method to check to see if the first letter of a word in the wordsearch matches
    * with the character at a specific index of the wordsearch
    * if this evaluates true, the word is stored in a charArray
    * if this evaluates false, run nextPlace
    */
   static boolean checkFirstLetter (char[][] puzzle, String[] words){
	   boolean result = false;
	   for(int i = 0; i< words.length;i++){
		   if(words[i].charAt(0)==puzzle[y][x]){
			   result = true;
			   //System.out.println("FOUND " + puzzle[y][x] + " at " + y + " | " + x);
			   testWord=words[i].toCharArray();
			   break;
		   }
	   }
	   return result;
   }
   
   /*create method to check if following letters match word in charArray
    * first must see which directions index can move (edge cases of corners and top,bottom,left,right)
    * then run check methods
    */
   static void checkFollowingLetters (char[][] puzzle){
	 //set tempX and tempY equal to coordinates x and y
	   tempX = x;
	   tempY = y;
	   //create booleans for each possible direction. they will be used in conjunction with count to see if word matches
	   boolean top = false;
	   boolean bottom = false;
	   boolean left = false;
	   boolean right = false;
	   boolean topLeft = false;
	   boolean topRight = false;
	   boolean bottomLeft = false;
	   boolean bottomRight = false;
	   //create count for every direction. will be used with boolean to see if word matches
	   int countTop = 0;
	   int countBottom = 0;
	   int countLeft = 0;
	   int countRight = 0;
	   int countTopLeft = 0;
	   int countTopRight = 0;
	   int countBottomLeft = 0;
	   int countBottomRight = 0;
	   
	   boolean result = false;
	   
	   
	   
		   
	   //write if statements checking for edge cases
	   //top case
	   if(tempY==0 && tempX !=0 && tempX != (puzzle.length-1)){
		   //run top
		   if(move(puzzle,"down")==testWord[1]){
			   bottom = true;
			   countBottom++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"left")==testWord[1]){
			   left = true;
			   countLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"right")==testWord[1]){
			   right = true;
			   countRight++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"downLeft")==testWord[1]){
			   bottomLeft = true;
			   countBottomLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"downRight")==testWord[1]){
			   bottomRight = true;
			   countBottomRight++;
		   }
		   tempX=x;
		   tempY=y;
	   }
	   //bottom case
	   else if(tempY==(puzzle.length-1) && tempX != 0 && tempX != (puzzle.length-1)){
		   //run botttom
		   
		   if(move(puzzle,"up")==testWord[1]){
			   top = true;
			   countTop++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"left")==testWord[1]){
			   left = true;
			   countLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"right")==testWord[1]){
			   right = true;
			   countRight++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"upLeft")==testWord[1]){
			   topLeft = true;
			   countTopLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"upRight")==testWord[1]){
			   topRight = true;
			   countTopRight++;
		   }
		   tempX=x;
		   tempY=y;
	   }
	   //left case
	   else if(tempX==0 && tempY !=0 && tempY != (puzzle.length-1)){
		   //run left
		   
		   if(move(puzzle,"up")==testWord[1]){
			   top = true;
			   countTop++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"down")==testWord[1]){
			   bottom = true;
			   countBottom++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"right")==testWord[1]){
			   right = true;
			   countRight++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"upRight")==testWord[1]){
			   topRight = true;
			   countTopRight++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"downRight")==testWord[1]){
			   bottomRight = true;
			   countBottomRight++;
		   }
		   tempX=x;
		   tempY=y;
	   }
	   //right case
	   else if(tempX==(puzzle.length-1) && tempY != 0 && tempY != (puzzle.length-1)){
		   //run right
		   
		   if(move(puzzle,"up")==testWord[1]){
			   top = true;
			   countTop++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"down")==testWord[1]){
			   bottom = true;
			   countBottom++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"left")==testWord[1]){
			   left = true;
			   countLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"upLeft")==testWord[1]){
			   topLeft = true;
			   countTopLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"downLeft")==testWord[1]){
			   bottomLeft = true;
			   countBottomLeft++;
		   }
		   tempX=x;
		   tempY=y;
	   }
	   //topLeft case
	   else if(tempX==0 && tempY==0){
		   //run topLeft
		   
		   if(move(puzzle,"down")==testWord[1]){
			   bottom = true;
			   countBottom++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"right")==testWord[1]){
			   right = true;
			   countRight++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"downRight")==testWord[1]){
			   bottomRight = true;
			   countBottomRight++;
		   }
		   tempX=x;
		   tempY=y;
	   }
	   //topRight case
	   else if(tempX==(puzzle.length-1) && tempY==0){
		   //run topRight
		   
		   if(move(puzzle,"down")==testWord[1]){
			   bottom = true;
			   countBottom++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"left")==testWord[1]){
			   left = true;
			   countLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"downLeft")==testWord[1]){
			   bottomLeft = true;
			   countBottomLeft++;
		   }
		   tempX=x;
		   tempY=y;
	   }
	   //bottomLeft case
	   else if(tempX==0 && tempY == (puzzle.length-1)){
		   //run bottomLeft
		   
		   if(move(puzzle,"up")==testWord[1]){
			   top = true;
			   countTop++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"right")==testWord[1]){
			   right = true;
			   countRight++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"upRight")==testWord[1]){
			   topRight = true;
			   countTopRight++;
		   }
		   tempX=x;
		   tempY=y;
	   }
	   //bottomRight case
	   else if(tempX==(puzzle.length-1) && tempY == (puzzle.length-1)){
		   //run bottomRight
		   
		   if(move(puzzle,"up")==testWord[1]){
			   top = true;
			   countTop++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"left")==testWord[1]){
			   left = true;
			   countLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"upLeft")==testWord[1]){
			   topLeft = true;
			   countTopLeft++;
		   }
		   tempX=x;
		   tempY=y;
	   }
	   //middle case
	   else{
		   //run middle
		   
		   if(move(puzzle,"up")==testWord[1]){
			   top = true;
			   countTop++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"down")==testWord[1]){
			   bottom = true;
			   countBottom++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"left")==testWord[1]){
			   left = true;
			   countLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"right")==testWord[1]){
			   right = true;
			   countRight++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"upLeft")==testWord[1]){
			   topLeft = true;
			   countTopLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"upRight")==testWord[1]){
			   topRight = true;
			   countTopRight++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"downLeft")==testWord[1]){
			   bottomLeft = true;
			   countBottomLeft++;
		   }
		   tempX=x;
		   tempY=y;
		   if(move(puzzle,"downRight")==testWord[1]){
			   bottomRight = true;
			   countBottomRight++;
		   }
		   tempX=x;
		   tempY=y;
	   }
	   
	   if(top==true){
		   for(int i=1;i<testWord.length;i++){
			   if(tempY!=0){
				   if(move(puzzle,"up")==testWord[i]){
					   countTop++;
				   }
			   }
		   }
		   if(countTop==testWord.length){
			   String foundWord = new String(testWord);
			   System.out.println("'" + foundWord + "' " + "found at " + "[" + y + "," + x + "] " + "going up");
		   }
		   tempX=x;
		   tempY=y;
	   }
	   
	   if(left==true){
		   for(int i=1;i<testWord.length;i++){
			   if(tempX!=0){
				   if(move(puzzle,"left")==testWord[i]){
					   countLeft++;
				   }
			   }
		   }
		   if(countLeft==testWord.length){
			   String foundWord = new String(testWord);
			   System.out.println("'" + foundWord + "' " + "found at " + "[" + y + "," + x + "] " + "going left");
		   }
		   tempX=x;
		   tempY=y;
	   }
	   
	   if(right==true){
		   for(int i=1;i<testWord.length;i++){
			   if(tempX!=(puzzle.length-1)){
				   if(move(puzzle,"right")==testWord[i]){
					   countRight++;
				   }
			   }
		   }
		   if(countRight==testWord.length){
			   String foundWord = new String(testWord);
			   System.out.println("'" + foundWord + "' " + "found at " + "[" + y + "," + x + "] " + "going right");
		   }
		   tempX=x;
		   tempY=y;
	   }
	   
	   if(topLeft==true){
		   for(int i=1;i<testWord.length;i++){
			   if(tempX!=0 && tempY!=0){
				   if(move(puzzle,"upLeft")==testWord[i]){
					   countTopLeft++;
				   }
			   }
		   }
		   if(countTopLeft==testWord.length){
			   String foundWord = new String(testWord);
			   System.out.println("'" + foundWord + "' " + "found at " + "[" + y + "," + x + "] " + "going diagonally up and to the left");
		   }
		   tempX=x;
		   tempY=y;
	   }
	   
	   if(topRight==true){
		   for(int i=1;i<testWord.length;i++){
			   if(tempX!=(puzzle.length-1) && tempY!=0){
				   if(move(puzzle,"upRight")==testWord[i]){
					   countTopRight++;
				   }
			   }
		   }
		   if(countTopRight==testWord.length){
			   String foundWord = new String(testWord);
			   System.out.println("'" + foundWord + "' " + "found at " + "[" + y + "," + x + "] " + "going diagonally up and to the right");
		   }
		   tempX=x;
		   tempY=y;
	   }
	   
	   if(bottomLeft==true){
		   for(int i=1;i<testWord.length;i++){
			   if(tempX!=0 && tempY != (puzzle.length-1)){
				   if(move(puzzle,"downLeft")==testWord[i]){
					   countBottomLeft++;
				   }
			   }
		   }
		   if(countBottomLeft==testWord.length){
			   String foundWord = new String(testWord);
			   System.out.println("'" + foundWord + "' " + "found at " + "[" + y + "," + x + "] " + "going diagonally down and to the left");
		   }
		   tempX=x;
		   tempY=y;
	   }
	   
	   if(bottomRight==true){
		   //System.out.println("reached second check");
		   for(int i=1;i<testWord.length;i++){
			  // System.out.println("reached for loop");
			   if(tempX!=(puzzle.length-1) && tempY != (puzzle.length-1)){
				   //System.out.println("reached first if statement");
				   if(move(puzzle,"downRight")==testWord[i]){
					   //System.out.println("Reached second if statement");
					   countBottomRight++;
					   //System.out.println(countBottomRight);
				   }
			   }
		   }
		   if(countBottomRight==testWord.length){
			   String foundWord = new String(testWord);
			   System.out.println("'" + foundWord + "' " + "found at " + "[" + y + "," + x + "] " + "going diagonally down and to the right");
		   }
		   tempX=x;
		   tempY=y;
	   }
	   
   }
}
