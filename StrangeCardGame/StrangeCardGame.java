import java.util.*;

public class StrangeCardGame {

	public static void main (String[] argv)
	{
		//use cards numbered 0,...,9 and deal 5 cards to each player
		playGame(5, 10);
	}

	static void playGame(int dealSize, int numCards)
	{
		//cards dealt out to player 1:
		LinkedList<Integer> player1 = new LinkedList<Integer>();
		//cards dealt out to player 2:
		LinkedList<Integer> player2 = new LinkedList<Integer>();

		//The pile between the two players:
		LinkedList<Integer> pile = new LinkedList<Integer>();

		//make the cards and shuffle them randomly.
		int[] cards = new int [numCards];
		for (int i=0; i<cards.length; i++) {
			cards[i] = i;
		}
		shuffle (cards);

		//deal cards to each player.
		int cardCount = 0;
		for (int k=0; k<dealSize; k++) {
			player1.add (cards[cardCount]);
			player2.add (cards[cardCount+1]);
			cardCount += 2;
		}

		//now play
		boolean done = false;
		int round = 0;

		while (! done) {

			//each player plays their first card.
			int player1first = player1.removeFirst ();
			pile.add (player1first);
			int player2first = player2.removeFirst ();
			pile.add (player2first);

			System.out.println ("Round 0: player 1's card = " + player1first + " player 2's card = " + player2first);

			if (player1first > player2first+2) {
				//add pile into player 1's cards
				addListToList (pile, player1);
				System.out.println("  => player 1 gets pile");
			}
			else if (player2first > player1first+2) {
				//Add pile into player 2's cards
				addListToList (pile, player2);
				System.out.println ("  => player 2 gets pile");
			}
			else {
				System.out.println ("  => both cards added to pile");
			}

			if (player1.isEmpty()) {
				System.out.println ("Player 2 wins!");
				done = true;
			}
			else if (player2.isEmpty()) {
				System.out.println ("Player 1 wins!");
				done = true;
			}

		} //end-while

	}

	static void shuffle (int[] A)
	{
		// ... random permutation ...
		for(int i=0; i<A.length-1; i++) {
		int k = UniformRandom.uniform (i, A.length-1);
		int temp = A[i];
		A[i] = A[k];
		A[k] = temp;
		}
	}

	static void addListToList (LinkedList<Integer> list1, LinkedList<Integer> list2)
	{
		//... extract every item in list 1 and add them to list 2...

		while(!list1.isEmpty())
			{
				int tempRemove = list1.removeFirst();
				list2.add(tempRemove);
			}
	}
}



