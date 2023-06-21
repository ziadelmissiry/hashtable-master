package ci583.htable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;


/**
 * A HashTable with no deletions allowed. Duplicates overwrite the existing value. Values are of
 * type V and keys are strings -- one extension is to adapt this class to use other types as keys.
 *
 * The underlying data is stored in the array `arr', and the actual values stored are pairs of
 * (key, value). This is so that we can detect collisions in the hash function and look for the next
 * location when necessary.
 */

public class Hashtable<V> {

	private Object[] arr;
	private int max;
	private int itemCount;

	private final double maxLoad = 0.5;

	// ENUM
	public static enum PROBE_TYPE {
		LINEAR_PROBE, QUADRATIC_PROBE, DOUBLE_HASH;
	}

	PROBE_TYPE probeType;
	private final BigInteger DOUBLE_HASH_MAX = BigInteger.valueOf(8);

	/**
	 * Create a new Hashtable with a given initial capacity and using a given probe type
	 * @param initialCapacity
	 * @param pt
	 */


	public Hashtable(int initialCapacity, PROBE_TYPE pt) {

		int correctedCapacity_Var = nextPrime(initialCapacity);

		arr = new Object[correctedCapacity_Var];
		probeType = pt;
		max = arr.length;

	}

	/**
	 * Create a new Hashtable with a given initial capacity and using the default probe type
	 * @param initialCapacity
	 */

	public Hashtable(int initialCapacity) {

		this(initialCapacity, PROBE_TYPE.LINEAR_PROBE); // Link between constructor 1 & 2 (constructor chaining)
	}

	/**
	 * Store the value against the given key. If the loadFactor exceeds maxLoad, call the resize
	 * method to resize the array. the If key already exists then its value should be overwritten.
	 * Create a new Pair item containing the key and value, then use the findEmpty method to find an unoccupied
	 * position in the array to store the pair. Call findEmmpty with the hashed value of the key as the starting
	 * position for the search, stepNum of zero and the original key.
	 * containing
	 * @param key
	 * @param value
	 */
	public void put(String key, V value) {

		int hashKey_VAr = hash(key);

		Pair foundPair_Var = findPair(hashKey_VAr, key, 0);

		if (foundPair_Var == null) {
			int newEntryIndex_Var = findEmptyOrSameKey(hashKey_VAr, 0, key);
			Pair pair = new Pair(key, value);
			arr[newEntryIndex_Var] = pair;
			itemCount++;
		} else {

			foundPair_Var.value = value;
		}
		if (maxLoad < getLoadFactor()) {
			resize();
		} else {
		}
	}

	/**
	 * Get the value associated with key, or return null if key does not exists. Use the find method to search the
	 * array, starting at the hashed value of the key, stepNum of zero and the original key.
	 * @param key
	 * @return
	 */
	public V get(String key) {

		int hashKey_VAr = hash(key);

		return find(hashKey_VAr,key,0);

	}

	/**
	 * Return true if the Hashtable contains this key, false otherwise
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key) {

		int hashKey_VAr = hash(key);

		return findPair(hashKey_VAr, key, 0) != null;
	}

	/**
	 * getKeys
	 * Return all the keys in this Hashtable as a collection
	 * Search trough the array and check for keys.
	 * @return
	 */
	public Collection<String> getKeys() {

		ArrayList<String> keys_Var = new ArrayList<String>(itemCount);

		for (int a = 0; a < arr.length; a++ ){

			if (arr[a] != null) {

				Pair keysPair = (Pair) (arr[a]);
				keys_Var.add(keysPair.key);

			}

		}

		return keys_Var;
	}

	/**
	 * Return the load factor, which is the ratio of itemCount to max
	 * @return
	 */
	public double getLoadFactor() {

		double actualLoadFactor_Var = (double)itemCount / (double)max;
		return actualLoadFactor_Var;
	}

	/**
	 * return the maximum capacity of the Hashtable
	 * @return
	 */
	public int getCapacity() {
		return arr.length;
	}

	/**
	 * Find the value stored for this key, starting the search at position startPos in the array. If
	 * the item at position startPos is null, the Hashtable does not contain the value, so return null.
	 * If the key stored in the pair at position startPos matches the key we're looking for, return the associated
	 * value. If the key stored in the pair at position startPos does not match the key we're looking for, this
	 * is a hash collision so use the getNextLocation method with an incremented value of stepNum to find
	 * the next location to search (the way that this is calculated will differ depending on the probe type
	 * being used). Then use the value of the next location in a recursive call to find.
	 * @param startPos
	 * @param key
	 * @param stepNum
	 * @return
	 */
	private V find(int startPos, String key, int stepNum) {

		Object entry_Var = arr [startPos];


		if (entry_Var == null) {
			return null;
		}

		if (((Pair)entry_Var).key.equals(key)) {
			return ((Pair)entry_Var).value;
		}

		int nextLoc = getNextLocation(startPos,stepNum,key);
		V value = find(nextLoc,key,++stepNum) ;
		return value;


	}

	/**
	 * Information about findPair method
	 * @param startPos
	 * @param key
	 * @param stepNum
	 * @return
	 */


	private Pair findPair(int startPos, String key, int stepNum) {

		Object entry_VAr = arr [startPos];

		if (entry_VAr == null) {
			return null;
		}


		if (((Pair)entry_VAr).key.equals(key)) {
			return ((Pair)entry_VAr);
		}


		int nextLoc_VAr = getNextLocation(startPos,stepNum,key);
		Pair pair_Var = findPair(nextLoc_VAr,key,stepNum+1);
		return pair_Var;

		/**
		 * Information about while loop avoid recursive call
		 *
		 * I have tried to avoid an recursive call, cause of wrong hash function has been causing stackoverflow error
		 * With proper hash function problem no occurs any more
		 */

	}


	/**
	 * Find the first unoccupied location where a value associated with key can be stored, starting the
	 * search at position startPos. If startPos is unoccupied, return startPos. Otherwise use the getNextLocation
	 * method with an incremented value of stepNum to find the appropriate next position to check
	 * (which will differ depending on the probe type being used) and use this in a recursive call to findEmpty.
	 * @param startPos
	 * @param stepNum
	 * @param key
	 * @return
	 */
	private int findEmptyOrSameKey(int startPos, int stepNum, String key) {

		Object entry_Var = arr[startPos];
		if (entry_Var == null) {

			return startPos;

		}
		int nextLoc_Var = getNextLocation (startPos,stepNum,key);

		return findEmptyOrSameKey (nextLoc_Var, ++stepNum, key);

	}

	/**
	 * Finds the next position in the Hashtable array starting at position startPos. If the linear
	 * probe is being used, we just increment startPos. If the double hash probe type is being used,
	 * add the double hashed value of the key to startPos. If the quadratic probe is being used, add
	 * the square of the step number to startPos.
	 * @param startPos
	 * @param stepNum
	 * @param key
	 * @return
	 */
	private int getNextLocation(int startPos, int stepNum, String key) {
		int step = startPos;
		switch (probeType) {
			case LINEAR_PROBE:
				step++;
				break;
			case DOUBLE_HASH:
				step += doubleHash(key);
				break;
			case QUADRATIC_PROBE:
				step += stepNum * stepNum;
				break;
			default:
				break;
		}
		return step % max;
	}

	/**
	 * A secondary hash function which returns a small value (less than or equal to DBL_HASH_K)
	 * to probe the next location if the double hash probe type is being used
	 * @param key
	 * @return
	 */
	private int doubleHash(String key) {
		BigInteger hashVal_Var = BigInteger.valueOf(key.charAt(0) - 96);
		for (int i = 0; i < key.length(); i++) {
			BigInteger c = BigInteger.valueOf(key.charAt(i) - 96);
			hashVal_Var = hashVal_Var.multiply(BigInteger.valueOf(27)).add(c);
		}
		return DOUBLE_HASH_MAX.subtract(hashVal_Var.mod(DOUBLE_HASH_MAX)).intValue();
	}

	/**
	 * Return an int value calculated by hashing the key. See the lecture slides for information
	 * on creating hash functions. The return value should be less than max, the maximum capacity
	 * of the array
	 * @param key
	 * @return
	 */
	private int hash(String key) {

		int hashVal_Var = key. charAt (0) - 96;
		for (int i =0; i<key. length (); i++) {
			int c = key. charAt (i) - 96;
			hashVal_Var = ( hashVal_Var * 27 + c) % max ;
		}
		return Math.abs(hashVal_Var);


	}


	/**
	 * method
	 *
	 */

	boolean [] primes_Num = new boolean[0];

	// prime numbers s
	public void S_Number(int size_VAr){

		primes_Num = new boolean[size_VAr];

		for (int i = 0; i < size_VAr; i++)
			primes_Num[i] = true;

		primes_Num[0] = primes_Num[1] = false;


		//
		for (int i = 2; i < primes_Num.length; i++){

			if(primes_Num[i]){

				for (int j = 2; i*j < primes_Num.length; j++){
					primes_Num[i*j] = false;
				}

			}

		}

	}



	/**
	 * Return true prime
	 * @param n
	 * @return
	 */


	private boolean isPrime(int n) {

		return primes_Num[n];

	}

	/**
	 * Get prime number
	 * @param n
	 * @return
	 */
	private int nextPrime(int n) {


		if ( n > primes_Num.length){
			S_Number(Math.max(n*2,10000));
		}

		for (int k = n; k < primes_Num.length; k++) {

			if (isPrime(k)) {

				return k;

			}

		}
		S_Number(primes_Num.length*2);

		return nextPrime(n);

	}

	/**
	 * Resize the hashtable, to be used when the load factor exceeds maxLoad. The new size of
	 * the underlying array should be the smallest prime number which is at least twice the size
	 * of the old array.
	 */
	private void resize() {


		if (getLoadFactor() >= maxLoad ){
			int newArraySize_VAr = nextPrime(max*2);
			Object[] oldArrayVar = arr;
			arr = new Object[newArraySize_VAr];
			max = newArraySize_VAr;
			itemCount = 0;
			for (int a = 0; a < oldArrayVar.length; a++ ){

				if (oldArrayVar[a] != null) {

					Pair oldPair_Var = (Pair) (oldArrayVar[a]);
					put(oldPair_Var.key , oldPair_Var.value);

				}
			}
		}
	}

	private class Pair {
		private String key;
		private V value;

		public Pair(String key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}