public class ArrayToolBox{
	
	public ArrayToolBox()
	{

	
	}

	/*
	this is a function to search over an array for a value
	@value is the item we are searching for
	@index is the current location in the array
	@A is the array we are searching
	The function returns true if found false if not.
	*/

	public static boolean search(int[] A, int value, int index)
	{
		for(int i=0; i<A.length; i++)
		{
			if(value == A[i])
			{
				return true;
			}	
		}
		return false;
	}

	public static boolean searchRecurse (int[] A, int value, int index)
	{
		if(index >= A.length)
		{
			System.out.println("Element not found");
			return false;
		}
		if(A[index] == value)
		{
			System.out.println("Element is found");
			return true;
		}

		return searchRecurse(A,value,index+1);
	}

	public static void quickSortRecurseive (int[] data, int left, int right)
	{
		if(left<right)
		{
			
		}
		else
	}
}

