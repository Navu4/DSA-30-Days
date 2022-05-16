package interview;

public class binarySearch {

    // 704. Binary Search
    // https://leetcode.com/problems/binary-search/
    public int binarysearch(int[] nums, int target) {
        int si = 0, ei = nums.length - 1;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    // 74. Search a 2D Matrix
    // https://leetcode.com/problems/search-a-2d-matrix/
    public boolean searchMatrix(int[][] arr, int tar) {
        int n = arr.length, m = arr[0].length;

        int si = 0, ei = n - 1, row = -1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid][0] <= tar && arr[mid][m - 1] >= tar) {
                row = mid;
                break;
            } else if (arr[mid][0] < tar) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        if (row == -1)
            return false;

        si = 0;
        ei = m - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[row][mid] == tar)
                return true;
            else if (arr[row][mid] < tar)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return false;
    }

    // 875. Koko Eating Bananas
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

    // 33. Search in Rotated Sorted Array
    // https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] nums, int target) {
        int n = nums.length;
        int lo = 0, hi = n - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else if (nums[mid] <= nums[hi]) {
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    // 153. Find Minimum in Rotated Sorted Array
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

    // 4. Median of Two Sorted Arrays
    // https://leetcode.com/problems/median-of-two-sorted-arrays/

    public static void main(String[] args) {

    }
}
