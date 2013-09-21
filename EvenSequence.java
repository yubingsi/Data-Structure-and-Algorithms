/**
 * This class is used to generate even sequence
 * under multi threads
 */
public class EvenSequence {
	
	private int i;

	public synchronized void next() { 
		i++;
		i++;
	}
       
	public synchronized int getValue() {
		return i;
	}

	public static void main(String[] args) {
		final EvenSequence s = new EvenSequence();
		
		new Thread() {
			public void run() {
				while(true) {
					int val  = s.getValue();
					if(val %2 != 0) {
						System.out.println(val);
						System.exit(0);
					}
				}
			}
		}.start();
		
		while(true) {
			s.next();
		}
	}
}
