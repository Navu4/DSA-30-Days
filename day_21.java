public class day_21 {

    /**
     * Two pointer important questions
     * 1. Container With Most Water
     * 2. Trapping Rain Water
     */

    // 1.Container With Most Water
    // https://leetcode.com/problems/container-with-most-water/
    public int area(int h, int w) {
        return h * w;
    }

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int max = 0;
        while (l < r) {
            int a = area(Math.min(height[l], height[r]), r - l);
            max = Math.max(a, max);

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return max;
    }

    // 2. Trapping Rain Water
    // https://leetcode.com/problems/trapping-rain-water/
    public static int trap(int[] height) {
        int water = 0;

        int[] leftToRight = new int[height.length];
        int[] rightToLeft = new int[height.length];

        int maxL = 0;
        for (int i = 0; i < leftToRight.length; i++) {
            leftToRight[i] = maxL;
            maxL = Math.max(maxL, height[i]);
        }

        int maxR = 0;
        for (int i = rightToLeft.length - 1; i >= 0; i--) {
            rightToLeft[i] = maxR;
            maxR = Math.max(maxR, height[i]);
        }

        int[] minLR = new int[height.length];
        for (int k = 0; k < minLR.length; k++) {
            minLR[k] = Math.min(leftToRight[k], rightToLeft[k]);
        }

        for (int k = 0; k < minLR.length; k++) {
            int w = minLR[k] - height[k];
            water += w > 0 ? w : 0;
        }

        return water;
    }

    // OR
    public static int optimisedTrap(int[] height) {
        int l = 0, r = height.length - 1;
        int water = 0;

        int leftMax = 0;
        int rightMax = 0;

        while (l < r) {
            int leftWater = leftMax - height[l];
            int rightWater = rightMax - height[r];

            water += (leftWater > 0 ? leftWater : 0) + (rightWater > 0 ? rightWater : 0);
            leftMax = Math.max(leftMax, height[l]);
            rightMax = Math.max(rightMax, height[r]);

            if (leftMax <= rightMax) {
                l++;
            } else {
                r--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] arr = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println(trap(arr));
    }
}
