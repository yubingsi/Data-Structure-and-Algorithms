import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is an Iterator that can be used to wrap an existing 
 * iterator but skip all null values in the iteration.
 * @author Yubing
 * @param <T> Using generic T
 */
public class NonNullIterator<T> implements Iterator<T> {
	private Iterator<T> iterator;
	private T nextItem;  // records the next item
	private boolean testedHasNext; // if did hasNext() at current position
	private boolean didNext;  // if did next() after hasNext()
	
	/**
	 * Constructor
	 * @param iterator : Take an Iterator as parameter
	 */
	public NonNullIterator(Iterator<T> iterator) {
		this.iterator = iterator;
		testedHasNext = false;
		didNext = true;
		nextItem = null;
	}
	
	@Override
	/**
	 * Returns true if the iteration has more non-null elements
	 */
	public boolean hasNext() {
		testedHasNext = true;
		while(iterator.hasNext()){
			if(didNext) {
				nextItem = iterator.next();
			}
			if(nextItem != null) {
				didNext = false;
				return true;
			}
		}
		didNext = false;
		return false;
	}

	@Override
	/**
	 * Returns the next non-null element in the iteration.
	 */
	public T next() {
		if((nextItem == null && testedHasNext) || (nextItem == null && !hasNext())) {
			throw new NoSuchElementException();
		}
 		T item = nextItem;
		nextItem = null;
		testedHasNext = false;
		didNext = true;
		return item;
	}

	@Override
	/**
	 * {@inheritDocs}
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}

