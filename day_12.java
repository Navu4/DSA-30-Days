import java.util.HashMap;

public class day_12 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode random;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        ListNode(int val, ListNode next, ListNode random){
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }
 


    /**
     * 1. Remove Duplicates 
     *      All Variants
     * 2. Cycle in a Linked List
     *      All Question 
     * 3. Intersection in LinkedList 
     *      All Questions 
     * 4. LRU
     * 5. Reverse LinkedList in K groups
     * 6. Copy List using Random Pointer
     */


    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    public ListNode deleteDuplicates(ListNode head) {
        if(head ==  null || head.next == null){
            return head;
        }
        
        ListNode nHead = new ListNode(-10000);
        nHead.next = head;
        ListNode curr = head, prev = nHead;
        
        while(curr != null){
            ListNode forw = curr.next;
            
            if(curr.val == prev.val){
                prev.next = forw;
                curr.next = null;
                
            } else {
                prev = curr;
            } 
            
            curr = forw;
        }
        
        return nHead.next;
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/submissions/
    public ListNode deleteDuplicatesII(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode nHead = new ListNode(-(int)1e8);
        nHead.next = head;
        ListNode curr = head.next, prev = nHead;
        
        while(curr != null) {
            boolean isDuplicate = false;
            
            while(curr != null && prev.next.val == curr.val){
                isDuplicate = true;
                curr = curr.next;
            }
            
            if(isDuplicate){
                prev.next = curr;
            } else {
                prev = prev.next;
            }
            
            if(curr != null)
                curr = curr.next;
        }
        
        return nHead.next;
    }


    // https://leetcode.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;

        }

        return false;
    }

    // https://leetcode.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        
        boolean hasCycle = false;
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        
            if(fast == slow){
                hasCycle = true;
                break;
            }
        }
        
        if(hasCycle == true) {
            slow = head;
            while(fast != slow){
                fast = fast.next;
                slow = slow.next;
            }
            
            return fast;
        } else {
            return null;
        } 
    }

    // https://leetcode.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tailA = headA;
        
        while(tailA.next != null)
            tailA = tailA.next;
        
        tailA.next = headB;
        
        ListNode fast = headA, slow = headA;
        boolean hasIntersected = false;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            
            if(fast == slow){
                hasIntersected= true;
                break;
            }
        }
        
        ListNode intersectingNode = null;
        if(hasIntersected == true){
            slow = headA;
            while(fast != slow){
                fast = fast.next;
                slow = slow.next;
            }
            
            intersectingNode = fast;
            tailA.next = null;
            return intersectingNode;
        } else {
            tailA.next = null;
            return null;
        }
        
    }

     // https://leetcode.com/problems/reverse-nodes-in-k-group/
     public static int length(ListNode head){
        ListNode curr = head;
        int len = 0;
        while ( curr != null ) {
            curr = curr.next;
            len++;
        }

        return len;
    }

    public static ListNode tHead = null, tTail = null;

    public static void addFirstNode(ListNode node) {
        if(tHead == null) {
            tHead = tTail = node;
        } else {
            node.next = tHead;
            tHead = node;
        }
    }

    public static ListNode reverseInKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 0)
            return head;

        int len = length(head);
        ListNode curr = head, oHead = null, oTail = null;

        while(len >= k){
            int tempK = k;

            while(tempK-- > 0){
                ListNode forw = curr.next;

                curr.next = null;
                addFirstNode(curr);
                curr = forw;
            }

            if(oHead == null){
                oHead = tHead;
                oTail = tTail;
            } else {
                oTail.next = tHead;
                oTail = tTail;
            }

            tHead = tTail = null;
            len -= k;
        } 

        oTail.next = curr; 

        return oHead;
    }


    // https://leetcode.com/problems/copy-list-with-random-pointer/
    public static ListNode copyRandomList(ListNode head) {
        HashMap<ListNode, ListNode> map = new HashMap<>();
        ListNode curr = head, nHead = new ListNode(-1); 
        ListNode prev = nHead;
     
        while ( curr != null ) {
            ListNode newNode = new ListNode(curr.val);
            prev.next = newNode;

            map.put(curr, newNode);

            prev = prev.next;
            curr = curr.next;   
        }

        ListNode c1 = head, c2 = nHead.next;
        while ( c1 != null && c2 != null ) {
            ListNode randomP = c1.random;

            c2.random = map.get(randomP);

            c1 = c1.next;
            c2 = c2.next;
        }

        return nHead.next;
    }



    // https://leetcode.com/problems/lru-cache/
    class LRUCache {
        class ListNode {
            int val, key;
            ListNode next, prev;
        
            ListNode(int key, int val){
                this.val = val;
                this.key = key;
            }
            
            ListNode(int key, int val, ListNode prev, ListNode next){
                this.key = key;
                this.val = val;
                this.prev = prev;
                this.next = next;
            }
        }
    
        private int capacity = -1;
        private ListNode head = null, tail = null;
        private HashMap<Integer, ListNode> map = new HashMap<>();
        
        private void updateDoublyLinkedList(ListNode node) {
            if(node == null)
                return;
            
            ListNode prev = node.prev, forw = node.next;
            
            prev.next = forw;
            forw.prev = prev;
            
            addFirst(node);
        }
        
        private void addFirst(ListNode node) {
            if(node == null)
                return;
            
            ListNode temp = this.head.next;
            this.head.next = node;
            node.prev = this.head;
            
            node.next = temp;
            temp.prev = node;
        }
        
        private void addValToList(ListNode node) {
            if(node == null)
                return;
            
            addFirst(node);
            if(map.size() > this.capacity){
                ListNode rNode = this.tail.prev;
                
                ListNode prev = rNode.prev;
                prev.next = this.tail;
                this.tail.prev = prev;
                
                rNode.prev = rNode.next = null;
                map.remove(rNode.key);
            } 
        }
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.head = new ListNode(-1, -1);
            this.tail = new ListNode(-1, -1);
            
            head.next = tail;
            tail.prev = head;
        }
        
        public int get(int key) {
            if(map.containsKey(key)){
                ListNode node = map.get(key);
                updateDoublyLinkedList(node);
                
                return node.val;
            } else {
                return -1;
            }
        }
        
        public void put(int key, int value) {
            if(map.containsKey(key)){
                ListNode node = map.get(key);
                node.val = value;
                
                updateDoublyLinkedList(node);
            } else {
                ListNode node = new ListNode(key, value);
                map.put(key, node);
                
                addValToList(node);
            }
        }
    }
}
