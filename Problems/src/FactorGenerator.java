import java.util.ArrayList;
import java.util.List;

public class FactorGenerator {
  public static void main(String[] args) {
    System.out.println(getFactors(20).toString());
  }

  static List<List<Integer>> getFactors(int n) {
    List<Integer> factors = new ArrayList<>();
    for (int i = 2; i <= n / 2; i++) {
      if (n % i == 0) {
        factors.add(i);
      }
    }

    List<List<Integer>> res = new ArrayList<>();
    generateFactors(res, factors, new ArrayList<>(), n, 0);
    return res;
  }

  private static void generateFactors(List<List<Integer>> res, List<Integer> factors, List<Integer> thisResult, int n,
      int start) {
    if (n == 1) {
      if (!thisResult.isEmpty()) {
        res.add(new ArrayList<>(thisResult));
      }
      return;
    }

    for (int i = start; i < factors.size(); i++) {
      if (n % factors.get(i) == 0) {
        thisResult.add(factors.get(i));
        generateFactors(res, factors, thisResult, n / factors.get(i), i);
        thisResult.remove(thisResult.size() - 1);
      }
    }
  }

}