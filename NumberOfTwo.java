public class NumberOfTwo {
        
        /**
	 * Return number of 2s between 0-n
	 */
	public static int count(int n) {
		if(n < 0) {
			return -1;
		}
		int count = 0;
		String num = String.valueOf(n);
		int len = num.length();
		for(int digit=0; digit<len;digit++) {
			count += compute(n,digit);
		}
		return count;
	}
	
	public static int compute(int n,int d) {
		int powerOf10 = (int)Math.pow(10, d);
		int nextPower = powerOf10 * 10;
		int digit = n / powerOf10 % 10;
		int right = n % (int)Math.pow(10, d-1);
		
		int down = n - n % nextPower;
		int up = down + nextPower;
		
		if(digit < 2) {
			return down / 10;
		} else if(digit > 2) {
			return up / 10;
		} else {
			return down / 10 + right + 1;
		}
	}
}
