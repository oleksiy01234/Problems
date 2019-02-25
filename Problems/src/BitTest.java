import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.BitSet;
import java.util.Scanner;

class BitTest {
	static int singleNumber(int[] a) {
		int result = 0;

		for (int i : a) {
			result ^= i;
		}

		return result;
	}

	public static long numberOfInts = ((long) Integer.MAX_VALUE) + 1;
	public static byte[] bitfield = new byte[(int) (numberOfInts / 8)];

	public static void findOpenNumber() throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader("Ch 10. Sorting and Searching/Q10_07_Missing_Int/input.txt"));
		while (in.hasNextInt()) {
			int n = in.nextInt();
			/*
			 * Finds the corresponding number in the bitfield by using the OR operator to
			 * set the nth bit of a byte (e.g., 10 would correspond to bit 2 of index 1 in
			 * the byte array).
			 */
			bitfield[n / 8] |= 1 << (n % 8);
		}

		for (int i = 0; i < bitfield.length; i++) {
			for (int j = 0; j < 8; j++) {
				/*
				 * Retrieves the individual bits of each byte. When 0 bit is found, finds the
				 * corresponding value.
				 */
				if ((bitfield[i] & (1 << j)) == 0) {
					System.out.println(i * 8 + j);
					in.close();
					return;
				}
			}
		}
		in.close();
	}

	// 4kB = 32 * 2^10 bits
	public static void checkDuplicates(int[] array) {
		BitSet bs = new BitSet(32000);
		for (int i = 0; i < array.length; i++) {
			int num = array[i];
			int num0 = num - 1; // bitset starts at 0, numbers start at 1
			if (bs.get(num0)) {
				System.out.println(num);
			} else {
				bs.set(num0);
			}
		}
	}

	public int getSum(int a, int b) {
		if (b == 0) {
			return a;
		}

		/*
			  a  |  b
			-----------
			1001 | 0011
			1010 | 0010
			1000 | 0100
			1100 | 0000 -> b is 0: return a
		
		
		
		*/

		int carry = (a & b) << 1;

		int partialSum = a ^ b;

		return getSum(partialSum, carry);
	}

	public int countNumberOfOnes(int n) {
		int count = 0;
		while (n != 0) {
			count += (n & 1);
			n = n >>> 1;
		}

		return count;
	}
}