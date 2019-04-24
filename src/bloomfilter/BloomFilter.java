package bloomfilter;

import java.util.BitSet;

public class BloomFilter {

	//determine the size of bit array
	private int size = 2<<24;
	//determine the number of hash function (different seeds)
	private int[] seeds = {3, 5, 7, 11, 13, 31, 37, 61};
	private BitSet bits = new BitSet(size);

	BloomFilter() {
	}

	//add an element to Bloom Filter
	void add(String str) {
		for (int i = 0; i < seeds.length; i++){
			bits.set(hash(seeds[i], str), true);
		}
	}

	//query whether Bloom Filter contains the element
	boolean query(String str) {
		for (int i = 0; i < seeds.length; i++){
			if (!bits.get(hash(seeds[i], str)))
				return false;
		}
		return true;
	}

	//Your hash function
	private int hash(int seed, String str) {
		int result = 0;
		int length = str.length();
		for (int i = 0; i < length; i++){
			result = result * seed + str.charAt(i);
		}
		return (size - 1) & result;
	}

}
