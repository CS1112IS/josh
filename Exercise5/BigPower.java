import java.math.*;

public class BigPower {

	static BigInteger countP = new BigInteger ("0");
	BigInteger countP2 = new BigInteger ("0");
	
    public static void main (String[] argv)
    {
        BigInteger X = new BigInteger ("3");
        BigInteger Y = new BigInteger ("2");
        BigInteger Z = power (X, Y);
        System.out.println();
        BigInteger Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2);

        
        X = new BigInteger ("3");
        Y = new BigInteger ("1");
        Z = power (X, Y);
        System.out.println();
        Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2);
        
        X = new BigInteger ("2");
        Y = new BigInteger ("8");
        Z = power (X, Y);
        System.out.println();
        Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2);


        X = new BigInteger ("2");
        Y = new BigInteger ("1000");
        Z = power (X, Y);
        System.out.println();
        Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2);

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
        System.out.println(1);
        BigInteger temp = power (A, BMinus1);
        BigInteger P = A.multiply (temp);
        System.out.println(1);
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
    		System.out.println(0);
    		BigInteger newN = N.divide(two);
    		System.out.println(0);
    		BigInteger temp = power2(newA,newN);
    		return temp;
    	}
    	if(N.mod(two).equals(one))
    	{
    		BigInteger newA = A.multiply(A);
    		System.out.println(0);
    		BigInteger newN = (N.subtract(one)).divide(two);
    		System.out.println(0);
    		System.out.println(0);
    		BigInteger temp = power2(newA,newN);
    		BigInteger P = A.multiply(temp);
    		System.out.println(0);
    		return P;
    	}
    	return one;
    }

}