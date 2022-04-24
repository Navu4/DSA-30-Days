import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class day_19 {

    /**
     * Heap / Priority Queue
     * 
     * 1. Heap and Heap sort
     * 2. Kth Largest Element in a Stream
     * 3. Last Stone Weight
     * 4. K Closest Points to Origin
     * 5. Kth Largest Element in an Array
     * 6. Task Scheduler
     * 
     */

    // 2.Kth Largest Element in a Stream
    // https://leetcode.com/problems/kth-largest-element-in-a-stream/
    class KthLargest {
        private int k = -1;
        private PriorityQueue<Integer> pq;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.pq = new PriorityQueue<>();

            for (int ele : nums) {
                this.pq.add(ele);

                if (this.pq.size() > this.k)
                    this.pq.remove();
            }

        }

        public int add(int val) {
            this.pq.add(val);

            if (this.pq.size() > this.k)
                this.pq.remove();

            return this.pq.peek() != null ? this.pq.peek() : -1;
        }
    }

    // 3. Last Stone Weight
    // https://leetcode.com/problems/last-stone-weight/
    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int stone : stones)
            pq.add(stone);

        while (pq.size() > 1) {
            int ele1 = pq.remove();
            int ele2 = pq.size() != 0 ? pq.remove() : 0;

            if (ele1 == ele2)
                continue;

            pq.add(ele1 - ele2);
        }

        return pq.peek() == null ? 0 : pq.peek();
    }

    // 4. K Closest Points to Origin
    // https://leetcode.com/problems/k-closest-points-to-origin/
    public class Pair1 {
        int x1, y1;

        Pair1() {
        }

        Pair1(int x1, int y1) {
            this.x1 = x1;
            this.y1 = y1;
        }
    }

    public double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair1> pq = new PriorityQueue<Pair1>((a, b) -> {
            return (distance(b.x1, b.y1, 0, 0) - distance(a.x1, a.y1, 0, 0)) > 0 ? 1 : -1;
        });

        for (int[] point : points) {
            pq.add(new Pair1(point[0], point[1]));
            if (pq.size() > k)
                pq.remove();
        }

        int[][] ans = new int[k][2];
        int i = 0;
        while (pq.size() != 0) {
            Pair1 rp = pq.remove();
            ans[i][0] = rp.x1;
            ans[i][1] = rp.y1;

            i++;
        }

        return ans;
    }

    // 5. Kth Largest Element in an Array
    // https://leetcode.com/problems/kth-largest-element-in-an-array/
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int ele : nums) {
            pq.add(ele);

            if (pq.size() > k) {
                pq.remove();
            }
        }

        return pq.remove();
    }

    // 6. Task Scheduler
    // https://leetcode.com/problems/task-scheduler/
    public class Pair {
        char ch;
        int i = 0, o = 0;

        Pair() {
        }

        Pair(char ch, int o) {
            this.ch = ch;
            this.o = o;
        }

        Pair(char ch, int o, int i) {
            this.ch = ch;
            this.i = i;
            this.o = o;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        int noOfTasks = tasks.length;

        if (n < 1) {
            return noOfTasks;
        }

        int interval = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return b.o - a.o;
        }); // Override
        for (char key : map.keySet()) {
            pq.add(new Pair(key, map.get(key)));
        }

        ArrayList<Pair> que = new ArrayList<>();

        while (pq.size() != 0 || que.size() != 0) {
            Pair rp = null;

            interval++;
            if (pq.size() != 0) {
                rp = pq.remove();
                rp.i = interval + n;
                rp.o--;
                // System.out.print(rp.ch + " -> ");
            } else {
                // System.out.print("idle -> ");
            }

            if (rp != null && rp.o > 0) {
                que.add(rp);
                // System.out.println(rp.ch + " " + rp.o + " " + rp.i);
            }
            if (que.size() != 0 && que.get(0).i == interval) {
                Pair np = que.get(0);
                que.remove(0);

                pq.add(np);
            }
        }
        System.out.println();

        return interval;
    }

    public static void main(String[] args) {

    }
}
