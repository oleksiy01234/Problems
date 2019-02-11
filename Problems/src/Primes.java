import java.util.Arrays;

class Primes {
  public static void main(String[] args) {
    System.out.println(countPrimes(10));
    System.out.println(isPrime(10));
  }

  private static int countPrimes(int n) {
    boolean[] primes = new boolean[n];
    Arrays.fill(primes, true);
    int count = 0;

    for (int i = 2; i < n; i++) {
      if (primes[i]) {
        count++;
        for (int j = i + i; j < n; j += i) {
          primes[j] = false;
        }
      }
    }

    return count;
  }

  private static boolean isPrime(int n) {
    if (n < 2) {
      return false;
    }

    for (int i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }
}