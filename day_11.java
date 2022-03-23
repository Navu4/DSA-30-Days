public class day_11{
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
 
    // https://leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        ListNode curr = head, prev = null;
        
        while(curr != null){
            ListNode forw = curr.next;
            
            curr.next = prev;
            prev = curr;
            curr = forw;
        }

        return prev;
    }

    // https://leetcode.com/problems/middle-of-the-linked-list/submissions/
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode slow = head, fast = head;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }

    // https://leetcode.com/problems/palindrome-linked-list/
    public ListNode reverse(ListNode head){
        if(head == null || head.next == null)
            return head;
        
        ListNode curr = head, prev = null;
        while(curr != null){
            ListNode forw = curr.next;
            
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        
        return prev;
    }
    
    public ListNode middleNodeP(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode slow = head, fast = head;
        
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }

    
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;
        
        ListNode middle = middleNodeP(head);
        ListNode secondHead = middle.next;
        middle.next = null;
        
        secondHead = reverse(secondHead);
        
        ListNode curr1 = head, curr2 = secondHead;
        boolean isPalindromeLL = true;
        while(curr2 != null){
            if(curr1.val != curr2.val){

                isPalindromeLL = false;
                break;
            }
            
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        
        return isPalindromeLL;
    }

    // https://leetcode.com/problems/reorder-list/ 
    public ListNode middleNodeFold(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
    
    public ListNode reverseFold(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode curr = head, prev = null;
        while(curr != null){
            ListNode forw = curr.next;
            
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        
        return prev;
    }
    
    
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        
        
        ListNode mid = middleNodeFold(head);
        ListNode nHead = mid.next;
        mid.next =null;
        
        nHead = reverseFold(nHead);
        
        ListNode c1 = head, c2 = nHead;
        
        while(c1 != null && c2 != null){
            ListNode c1Next = c1.next;
            ListNode c2Next = c2.next;
            
            c1.next = c2;
            c2.next = c1Next;
            
            c1 = c1Next;
            c2 = c2Next;
        }
        
    }


    // UnFold https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/linked-list/unfold-of-linkedlist/ojquestion#!
    public static ListNode reverseListUnfold(ListNode head) {
        ListNode curr = head, prev = null;
        
        while(curr != null){
            ListNode forw = curr.next;
            
            curr.next = prev;
            prev = curr;
            curr = forw;
        }

        return prev;
    }


    public static void unfold(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        
        ListNode c1 = head, c2 = head.next;
        ListNode p1 = head, p2 = head.next;
        
        while(c1 != null && c2 != null){
            ListNode c1n = c2.next;
            ListNode c2n = null;
            
            if(c1n != null){
                c2n = c1n.next;
            }
            
            c1.next = c1n;
            c2.next = c2n;
            
            c1 = c1.next;
            c2 = c2.next;
        }
        
        if(c1 != null){
            c1.next = null;
        }
        
        if(c2 != null){
            c2.next = null;
        }
        
        ListNode nHead = reverseListUnfold(p2);
        while(p1.next != null){
            p1 = p1.next;
        }
        
        p1.next = nHead;
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null || list2 == null){
            return list2 == null ? list1: list2;
        }
        ListNode head = new ListNode(-1);
        
        ListNode c1 = list1, c2 = list2, curr = head;
        while(curr != null && c1 != null && c2 != null) {
            if(c1.val < c2.val){
                curr.next = c1;
                c1 = c1.next;
            } else {
                curr.next = c2;
                c2 = c2.next;
            }
            
            curr = curr.next;
        }
        
        if(c1 != null){
            curr.next = c1;
        }
        if(c2 != null){
            curr.next = c2;
        }
        
        
        return head.next;
    }


    // https://leetcode.com/problems/merge-k-sorted-lists/
    public ListNode mergeTwoSortedList(ListNode head1, ListNode head2){
        if(head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }
        
        
        ListNode nHead = new ListNode(-1);
        ListNode curr = nHead, c1 = head1, c2 = head2;
        while(c1 != null && c2 != null){
            if(c1.val < c2.val){
                curr.next = c1;
                c1 = c1.next;
            } else {
                curr.next = c2;
                c2 = c2.next;
            }
            
            curr = curr.next;
        }
        
        if(c1 != null){
            curr.next = c1;
        }
        if(c2 != null){
            curr.next = c2;
        }
        ListNode temp = nHead;
        nHead = nHead.next;
        temp.next = null;
        return nHead;
    }
    
    public ListNode mergeSort(ListNode[] lists, int si, int ei){
        if(si == ei){
            return lists[si];
        }
        
        int mid = (si + ei) / 2;
        return mergeTwoSortedList(mergeSort(lists, si, mid), mergeSort(lists, mid + 1, ei));
    }
    
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)  // IMPORTANT BASE CASE 
            return null;
        
        return mergeSort(lists, 0, lists.length - 1);
    }


    // https://leetcode.com/problems/sort-list/
    public ListNode mergeTwoSortedLinkedList(ListNode head1, ListNode head2){
        if(head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }
                                                                             
        ListNode c1 = head1, c2 = head2, nHead = new ListNode(-1);
        ListNode curr = nHead;
        
        while(c1 != null && c2 != null){
            if(c1.val < c2.val){
                curr.next = c1;
                c1 = c1.next;
            } else {
                curr.next = c2;
                c2 = c2.next;
            }
            
            curr = curr.next;
        }
        
        if(c1 != null){
            curr.next = c1;
        }
        if(c2 != null){
            curr.next = c2;
        }
                                                                             
        ListNode temp = nHead;
        nHead = nHead.next;
        
        temp.next = null;
        return nHead;
    }
    
    public ListNode middleNodeMerge(ListNode head){
        if(head == null || head.next == null)
            return head;
        
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode mid = middleNodeMerge(head);
        ListNode nHead =  mid.next;
        mid.next =null;
        
        return mergeTwoSortedLinkedList(sortList(head),sortList(nHead));
    }


    // https://leetcode.com/problems/remove-linked-list-elements/
    public ListNode removeElements(ListNode head, int val) {
        if(head == null || head.next == null){
            return head != null && head.val == val ? null : head;
        }
        
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        
        ListNode curr = head, prev = dummyHead;
        while(curr != null){
            ListNode forw = curr.next;
            
            if(curr.val == val){
                prev.next = forw;
                curr.next = null;
            }
            else
                prev = curr;                
            
            
            curr = forw;
        }
        
        return dummyHead.next;
    }


    // https://leetcode.com/problems/odd-even-linked-list/
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode even = new ListNode(-1), odd = new ListNode(-1);
        ListNode c1 = even, c2 = odd, curr = head;
        int index = 1;
        
        while(curr != null){
            if(index % 2 == 0){
                c1.next = curr;
                c1 = c1.next;
            } else {
                c2.next = curr;
                c2 = c2.next;
            }
            index++;
            curr = curr.next;
        }
        c2.next = even.next;
        c1.next = null; 
        
        return odd.next;
    }


    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null){
            return head != null && n == 1 ? null : head; 
        }
        
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        
        ListNode fast = head, slow = head, prev = dummyHead;
        while(fast != null && n-- > 0){
            fast = fast.next;
        }
        
        while(fast != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        
        prev.next = prev.next.next;
        
        
        return dummyHead.next;
    }

    // https://nados.io/question/segregate-01-node-of-linkedlist-over-swapping-nodess
    public static ListNode segregate01(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode ones = new ListNode(-1);
        ListNode zeros = new ListNode(-1);
        
        ListNode curr = head, c1 = zeros, c2 = ones;
        while(curr != null){
            if(curr.val == 0){
                c1.next = curr;
                c1 = c1.next;
            } else {
                c2.next = curr;
                c2 = c2.next;
            }

            curr = curr.next;
        }

        c1.next = ones.next;
        c2.next = null;

        return zeros.next;
    }


    // https://nados.io/question/segregate-node-of-linkedlist-over-last-index
    public static ListNode getTail(ListNode head){
        ListNode curr = head;
        while ( curr.next != null ) {
            curr = curr.next;
        }

        return curr;
    }

    
    public static ListNode segregateOnLastIndex(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode lastNode = getTail(head);

        ListNode smaller = new ListNode(-1), larger = new ListNode(-1);
        ListNode s = smaller, l = larger, curr = head;

        while(curr != null){
            if(lastNode.val < curr.val){
                l.next = curr;
                l = l.next;
            } else {
                s.next = curr;
                s = s.next;
            }
            curr = curr.next;
        }

        l.next = null;
        lastNode.next = larger.next;
        larger.next = lastNode;

        return larger.next;
    }

    // Segregate Node Of Linkedlist Over Pivot Index
    public static ListNode getNodeAt(ListNode head, int idx){
        ListNode curr = head;
        while(curr != null && idx-- > 0){
            curr = curr.next;
        }
        return curr; 
    }
  
    public static ListNode segregate(ListNode head, int pivotIdx) {
      if(head == null || head.next == null)
          return head;
  
      ListNode pivotNode = getNodeAt(head, pivotIdx);
      ListNode smaller = new ListNode(-1), larger = new ListNode(-1);
      ListNode s = smaller, l = larger, curr = head;
  
      while(curr != null){
  
          if(curr == pivotNode){
              curr = curr.next;
              continue;
          } else if(curr.val > pivotNode.val){
              l.next = curr;
              l = l.next;
          } else {
              s.next = curr;
              s = s.next;
          }
  
          curr = curr.next;
      }
  
      s.next = pivotNode;
      pivotNode.next = larger.next;
      l.next = null;
  
      return smaller.next;
  
    }
  
    

    public static void main(String[] args){
        
    }
}