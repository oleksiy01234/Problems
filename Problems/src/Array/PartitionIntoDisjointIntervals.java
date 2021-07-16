package Array;

/**
 * Given an array nums, partition it into two (contiguous) subarrays left and right so that:
 * <p>
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,0,3,8,6]
 * <p>
 * Output: 3
 * <p>
 * Explanation: left = [5,0,3], right = [8,6]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,1,1,0,6,12]
 * <p>
 * Output: 4
 * <p>
 * Explanation: left = [1,1,1,0], right = [6,12]
 * <p>
 * <p>
 * Note:
 * <p>
 * 2 <= nums.length <= 30000
 * 0 <= nums[i] <= 106
 * It is guaranteed there is at least one way to partition nums as described.
 */
public class PartitionIntoDisjointIntervals {
  /*
       The process divide the array to three partitions:
       a[0]...a[partitionSpot] | A[partitionSpot+1]...A[i-1] | A[i]...A[length-1]
                                                                 ^ currently visiting

       [0,partitionSpot] is the left partition
       [partitionSpot+1, i-1] is the second partition
       [i,length-1] is the last partition, which is to be processed.

       all elements from second partition are greater than or equal to the first partition's max.

       we maintain two max:
       leftPartitionMax: the max value for first partition [0->partitionSpot]
       maxUntilI: the max value for all elements we already visited [0, i];

       partitionSpot is the spot where we should partition the subarray[0, i-1],

       now if a[i] < leftPartitionMax, it means should re-partition subarray[0, i], with i as the partitionSpot
       and assign maxUntilI to leftPartitionMax, because now the first partition became: [0, i].
      */
  public int partitionDisjoint(int[] a) {
    int maxUntilI = a[0];
    int leftPartitionMax = a[0];
    int partitionSpot = 0;

    for (int i = 1; i < a.length; i++) {
      maxUntilI = Math.max(maxUntilI, a[i]);

      if (a[i] < leftPartitionMax) {
        leftPartitionMax = maxUntilI;
        partitionSpot = i;
      }
    }

    return partitionSpot + 1;
  }
  /*
      Input: nums = [5,0,3,8,6]

      i   a[i]    maxUntilI   leftPartitionMax   partitionIndex
                  5           5                  1
      1   0                   5                  2
      2   3                   5                  3
      3   8       8
      4   6       8



   */
}
