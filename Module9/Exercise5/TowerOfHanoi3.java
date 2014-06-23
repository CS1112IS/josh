
public class TowerOfHanoi3 {

    static int day = 0;
    public static void main (String[] argv)
    {
        // A 5-disk puzzle:
        day = 0;
	solveHanoi (4, 0, 1);
    }

    static void solveHanoi (int n, int i, int j)
    {
	// Bottom-out.
	if (n == 0) {
            // The smallest disk.
	    move (0, i, j);
	    return;
	}

	int k = other (i, j);
	solveHanoi (n-1, i, k);    // Step 1.
	move (n, i, j);            // Step 2.
	solveHanoi (n-1, k, j);    // Step 3.
    }


    static void move (int n, int i, int j)
    {
        // INSERT YOUR CODE HERE.
        // Before printing, convert n=0 to 'A', n=1 to 'B' etc.

        //Day 0 - use disk A
        char disk =' ';
        char[] diskLetters = {'A' , 'B' , 'C' , 'D' , 'E'};
        for(int t=0;t<5;t++){
            if (n==t){
                disk = diskLetters[t];
                break;
            }

        }

        System.out.println("Day " + day + " - use disk " + disk);
        day++;
    }


    static int other (int i, int j)
    {
        // Given two towers, return the third.
        if ( (i == 0) && (j == 1) ) {
            return 2;
        }
        else if ( (i == 1) && (j == 0) ) {
            return 2;
        }
        else if ( (i == 1) && (j == 2) ) {
            return 0;
        }
        else if ( (i == 2) && (j == 1) ) {
            return 0;
        }
        else if ( (i == 0) && (j == 2) ) {
            return 1;
        }
        else if ( (i == 2) && (j == 0) ) {
            return 1;
        }

        // We shouldn't reach here.
        return -1;
    }

}
