package Math;

import java.util.Random;

public class RandomGenerator {

  private static final Random rnd = new Random();

  private static int rand5() {
    return rnd.nextInt(5) + 1;
  }

  private static int rand7() {
    return rnd.nextInt(7) + 1;
  }

  // generating rand5 by using rand7
  public static int myRand5() {

    while (true) {
      int result = rand7();
      if (result <= 5) {
        return result;
      }
    }
  }

  // generating rand7 by using rand5
  public static int myRand7() {
    while (true) {
      int outcome = (rand5() - 1) * 5 + rand5();
      if (outcome <= 21) {
        return outcome % 7;
      }
    }
  }
}