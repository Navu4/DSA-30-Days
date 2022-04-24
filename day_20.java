public class day_20 {

    /**
     * Binary Search
     * 1. Binary search algo
     * 2. Search a 2D Matrix
     * 3. Koko Eating Bananas
     * 4. Search Rotated Sorted Array
     * 5. Find Minimum in Rotated Sorted Array
     * 6. Time Based Key-Value Store
     * 
     */

    // 1. Binary Search Algorithm
    // https://leetcode.com/problems/binary-search/
    public int BinarySearchAlgorithm(int[] nums, int target) {
        int si = 0, ei = nums.length - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return -1;
    }

    // 2. Search a 2D Matrix
    // https://leetcode.com/problems/search-a-2d-matrix/
    public int binarySearch(int[][] matrix, int target, int si, int ei) {
        while (si <= ei) {
            int mid = (si + ei) / 2;

            if (target >= matrix[mid][0] && target <= matrix[mid][matrix[0].length - 1]) {
                return mid;
            } else if (matrix[mid][0] > target) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return -1;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;

        int idx = binarySearch(matrix, target, 0, n - 1);
        if (idx == -1)
            return false;

        int si = 0, ei = m - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (matrix[idx][mid] == target) {
                return true;
            } else if (matrix[idx][mid] > target) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return false;
    }

    // 3. Koko Eating Bananas
    // https://leetcode.com/problems/koko-eating-bananas/
    public boolean isPossible(int[] arr, int sp, int h) {
        int time = 0;
        for (int ele : arr) {
            time += (int) Math.ceil((ele * 1.0) / sp); // Point me value hogi tbi Ceil aayega verna nahi aayega
        }

        return time <= h;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;

        int max = -1;
        for (int pile : piles)
            max = Math.max(max, pile);

        if (h <= n) {
            return max;
        }

        int lo = 0, hi = max, speed = max;
        while (lo <= hi) {
            int sp = lo + (hi - lo) / 2; // To avoid overflow

            if (isPossible(piles, sp, h) == true) {
                speed = sp;
                hi = sp - 1;
            } else {
                lo = sp + 1;
            }

        }
        return speed;
    }

    // 4. Search Rotated Sorted Array
    // https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[lo] <= nums[mid]) {
                if (nums[mid] > target && nums[lo] <= target) { // Yeh Condition Dekh ke likho
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else if (nums[mid] <= nums[hi]) {
                if (nums[mid] < target && nums[hi] >= target) { // Yeh Condition Dekh ke likho
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    // 5. Find Minimum in Rotated Sorted Array
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int n = nums.length;

        if (nums[lo] <= nums[hi]) {
            return nums[lo];
        }

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (mid - 1 > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            } else if (mid + 1 < n && nums[mid + 1] < nums[mid]) {
                return nums[mid + 1];
            } else if (nums[lo] <= nums[mid]) {
                lo = mid + 1;
            } else if (nums[mid] <= nums[hi]) {
                hi = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

    }
}
