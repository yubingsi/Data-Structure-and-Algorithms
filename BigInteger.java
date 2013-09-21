public class BigInteger {

	private byte[] digits;
	private boolean positive;
	
	public BigInteger() {
		this.digits = new byte[1];
		digits[0] = 0;
		this.positive = true;
	}
	
	public BigInteger(String num) {
		if(num == null || num.length() == 0) {
			throw new NumberFormatException("input should not be null or empty string");
		}
		int index = 0;
		char sign = num.charAt(0);
		if(sign == '-') {
			this.positive = false;
			index++;
		} else {
			this.positive = true;
		}
		
		int length = num.length() - index;
		digits = new byte[length];
		for(int i=length-1; i>=0; i--) {
			digits[i] = (byte)(num.charAt(index) - '0');
			index++;
		}
	}
	
	public BigInteger add(BigInteger num) {
		boolean resultSign;
		if(num.positive != this.positive) {
			int len = Math.max(this.digits.length, num.digits.length);
			byte[] result = new byte[len];
			if(this.digits.length > num.digits.length) {
				resultSign = this.positive;
				addDifferentSign(num.digits,0,(byte)0,result);
			} else if(this.digits.length < num.digits.length) {
				resultSign = num.positive;
				num.addDifferentSign(this.digits,0,(byte)0,result);
			} else {
				int sign = getSign(num);
				if(sign == 0) {
					return new BigInteger();
				} else if(sign == 1) {
					resultSign = this.positive;
					addDifferentSign(num.digits,0,(byte)0,result);
				} else {
					resultSign = num.positive;
					num.addDifferentSign(this.digits,0,(byte)0,result);
				}
			}
			int trueSize = getTrueSize(len,result);
			return generateNewBigInteger(trueSize,result,resultSign);
		} else {
			resultSign = num.positive;
			int resultLen = Math.max(this.digits.length, num.digits.length) + 1;
			byte[] result = new byte[resultLen];
			addSameSign(num.digits,0,(byte)0,result);
			int trueSize = getTrueSize(resultLen,result);
			return generateNewBigInteger(trueSize,result,resultSign);
		}
	}
	
	public BigInteger add(int num) {
		BigInteger number = new BigInteger(String.valueOf(num));
		return add(number);
	}
	
	public BigInteger negative() {
		this.positive = !this.positive;
		return this;
	}
	
	public BigInteger substract(BigInteger num) {
		BigInteger number = num.negative();
		return add(number);
	}
	
	public BigInteger substract(int num) {
		BigInteger number = new BigInteger(String.valueOf(num));
		return substract(number);
	}
	
	private void addSameSign(byte[] num,int index,byte carry,byte[] result) {
		if(carry == 0 && index >= num.length && index >= this.digits.length) {
			return ;
		}
		byte sum = carry;
		if(index < num.length) {
			sum += num[index];
		}
		if(index < this.digits.length) {
			sum += this.digits[index];
		}
		result[index] = (byte)(sum % 10);
		carry = (byte)(sum / 10);
		addSameSign(num,index+1,carry,result);
	}
	
	private void addDifferentSign(byte[] num, int index, byte borrow, byte[] result) {
		if(borrow == 0 && index >= num.length && index >= this.digits.length) {
			return;
		}
		int sum = 0 - borrow;
		if(index < this.digits.length) {
			sum += digits[index];
		}
		borrow = 0;
		if(index < num.length) {
			if(sum >= num[index]) {
				sum -= num[index];
			} else {
				borrow++;
				sum = sum + 10 - num[index];
			}	
		}
		result[index] = (byte)sum;
		addDifferentSign(num,index+1,(byte)borrow,result);
	}
	
	private int getSign(BigInteger num) {
		for(int i=num.digits.length-1; i>=0;i--) {
			if(num.digits[i] > this.digits[i]) {
				return 2;
			} else if(num.digits[i] < this.digits[i]) {
				return 1;
			}
		}
		return 0;
	}
	
	private int getTrueSize(int len, byte[] result) {
		int trueSize = len;
		for(int i=len-1; i>=0;i--) {
			if(result[i] != 0) {
				break;
			}
			trueSize--;
		}
		return trueSize;
	}
	
	private BigInteger generateNewBigInteger(int trueSize,byte[] result,boolean resultSign) {
		byte[] trueSizeArray = new byte[trueSize];
		for(int i=0; i<trueSize; i++) {
			trueSizeArray[i] = result[i];
		}
		BigInteger resultVal = new BigInteger();
		resultVal.digits = trueSizeArray;
		resultVal.positive = resultSign;
		return resultVal;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		if(!this.positive) {
			sb.insert(index,"-");
			index++;
		}
		for(int i=0; i<this.digits.length;i++) {
			sb.insert(index, digits[i]);
		} 
		return sb.toString();
	}
}
