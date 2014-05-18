import java.math.*;

public class BigPower {

	static int count;
	static int count2;
	
    public static void main (String[] argv)
    {
        BigInteger X = new BigInteger ("3");
        BigInteger Y = new BigInteger ("2");
		count=0;
		count2=0;
        BigInteger Z = power (X, Y);
        BigInteger Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2);
		System.out.println("The count for the power function is: " + count);
		System.out.println("The count for the power2 function is: " + count2);
        
        X = new BigInteger ("3");
        Y = new BigInteger ("1");
		count=0;
		count2=0;
        Z = power (X, Y);
        System.out.println();
        Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2); 
		System.out.println("The count for the power function is: " + count);
		System.out.println("The count for the power2 function is: " + count2);

        X = new BigInteger ("2");
        Y = new BigInteger ("8");
		count=0;
		count2=0;
        Z = power (X, Y);
        System.out.println();
        Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2);	
		System.out.println("The count for the power function is: " + count);
		System.out.println("The count for the power2 function is: " + count2);

        X = new BigInteger ("2");
        Y = new BigInteger ("1000");
		count=0;
		count2=0;
        Z = power (X, Y);
        System.out.println();
        Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2);
		System.out.println("The count for the power function is: " + count);
		System.out.println("The count for the power2 function is: " + count2);

    }

////////////////////////////////////////////////////////////////////////////////////////////////
    
    static BigInteger zero = new BigInteger ("0");
    static BigInteger one = new BigInteger ("1");
    static BigInteger two = new BigInteger ("2");

    static BigInteger power (BigInteger A, BigInteger B)
    {
        if ( B.equals(zero) ) {
            return new BigInteger ("1");
        }
        BigInteger BMinus1 = B.subtract (one);
        count++;
        BigInteger temp = power (A, BMinus1);
        BigInteger P = A.multiply (temp);
        count++;
        return P;
        
    }
    
    static BigInteger power2 (BigInteger A, BigInteger N)
    {
    	if(N.equals(one))
    	{
    		return A;
    	}
    	if(N.equals(zero))
    	{
    		return one;
    	}
    	if(N.mod(two).equals(zero))
    	{
    		BigInteger newA = A.multiply(A);
    		count2++;
    		BigInteger newN = N.divide(two);
    		count2++;
    		BigInteger temp = power2(newA,newN);
    		return temp;
    	}
    	if(N.mod(two).equals(one))
    	{
    		BigInteger newA = A.multiply(A);
    		count2++;
    		BigInteger newN = (N.subtract(one)).divide(two);
    		count2++;
    		count2++;
    		BigInteger temp = power2(newA,newN);
    		BigInteger P = A.multiply(temp);
    		count2++;
    		return P;
    	}
    	return one;
    }

}
