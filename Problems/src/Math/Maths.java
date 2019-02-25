package Math;

import Util.Util;

class Maths {

	static int powerSum(int x, int n) {
		return powerSum(x, n, 0);
	}

	// Find the number of ways that x can be expressed as the sum of the Nth powers
	static int powerSum(int x, int n, int i) {
		x -= (int) Math.pow(i, n); // substract i^n from x. i starts with 0

		if (x == 0) {
			return 1;
		} else if (x < 0) {
			return 0;
		}

		int sum = 0;
		for (int j = i + 1; Math.pow(j, n) <= x; j++) {
			sum += powerSum(x, n, j);
		}
		return sum;
	}

	static void largestBase2LogUnder() {
		int n = Util.getUserInput().nextInt();
		System.out.print("largest int <= log base 2 of " + n + " is ");

		int counter = 0;

		while (n / 2 > 0) {
			counter++;
			n /= 2;
		}

		System.out.println(counter);
	}

	static int getNumOfDigits(long num) {
		int count = 0;

		while (num != 0) {
			num /= 10;
			count++;
		}

		return count;
	}

	// 123->321; -20->-2
	static int reverse(int x) {
		long result = 0;

		while (x != 0) {
			result *= 10;
			result += x % 10;
			x /= 10;

			if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
				return 0;
			}
		}

		return (int) result;
	}

	public double myPow(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}

		double ans = 1;
		double currentProduct = x;
		for (long i = N; i > 0; i /= 2) {
			if ((i % 2) == 1) {
				ans *= currentProduct;
			}
			currentProduct *= currentProduct;
		}
		return ans;
	}
}