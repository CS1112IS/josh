public class Test{

public static void main(String[] args)
	{
		ArrayToolBox tool = new ArrayToolBox();
		int[] a = {1,4,72,65,23,17,57,94,54,10,100,3,22,1001,34,8};

		boolean flag = tool.searchRecurse(a,3,0);
		
		if(flag)
		{
			System.out.println(3 + " was found!");
		}else System.out.println(3+ " was not found");

		if(flag2)
		{
			System.out.println(1001 + " was found!");
		}else System.out.println(1001 + " was not found");
	}



}
