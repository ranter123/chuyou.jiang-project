package chuyou.jiang.algorithm.leetcode.list;

/**
 * @Author: ranter
 * @Date: 2020/6/10 10:57 下午
 * @Description: 两数相加
 */
public class addTwoNumbers {

    public static void main(String[] args) {

        ListNode l1 = addListNode(5);
        ListNode l2 = addListNode(5);
        printListNode( addTwoNumbers(l1, l2));
    }

    /**
     * 计算两数之和
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumListNode = null;
        ListNode currentNode = new ListNode();
        while (l1!= null || l2 != null) {
            int num1 = 0;
            int num2 = 0;
            if (l1 != null) {
                num1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                num2 = l2.val;
                l2 = l2.next;
            }
            currentNode.val = (num1 + num2)/10 == 0 ? num1 + num2 : (num1 + num2)%10;
            if (sumListNode == null) {
                sumListNode = currentNode;
            }
            if (num1 + num2 >= 10) {
                if (l1 != null) {
                    l1.val += 1;
                } else if (l2 != null){
                    l2.val += 1;
                } else {
                    currentNode.next = new ListNode((num1 + num2) / 10);
                    return sumListNode;
                }
            }
            if (l1== null && l2 == null) {
                currentNode.next = null;
            } else {
                currentNode.next = new ListNode();
            }
            currentNode = currentNode.next;

        }
        return sumListNode;
    }

    /**
     * 定义链表节点
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
        ListNode() {

        }
    }

    /**
     * 添加链表数组
     * @param nums
     * @return
     */
    public static ListNode addListNode(int ...nums) {
        ListNode listNode = null;
        ListNode currentNode = new ListNode();
        for (int i = 0; i < nums.length; i ++) {
            if (listNode == null) {
                currentNode.val = nums[i];
                listNode = currentNode;
            } else {
                currentNode.val = nums[i];
            }
            if (i == nums.length - 1) {
                currentNode.next = null;
            } else {
                currentNode.next = new ListNode();
            }
            currentNode = currentNode.next;
        }
        return listNode;
    }

    /**
     * 打印链表
     * @param listNode
     */
    public static void printListNode(ListNode listNode) {
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
