////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 * Copyright © 2019 Unified Social, Inc.
 * 180 Madison Avenue, 23rd Floor, New York, NY 10016, U.S.A.
 * All rights reserved.
 *
 * This software (the "Software") is provided pursuant to the license agreement you entered into with Unified Social,
 * Inc. (the "License Agreement").  The Software is the confidential and proprietary information of Unified Social,
 * Inc., and you shall use it only in accordance with the terms and conditions of the License Agreement.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND "AS AVAILABLE."  UNIFIED SOCIAL, INC. MAKES NO WARRANTIES OF ANY KIND, WHETHER
 * EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO THE IMPLIED WARRANTIES AND CONDITIONS OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT.
 */

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package confluent;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;


public class LRUCache {
    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    Node               head;
    Node               tail;
    int                capacity;
    Map<Integer, Node> nodeMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        if (!nodeMap.containsKey(key)) {
            return -1;
        }
        Node node = nodeMap.get(key);
        removeNode(node);
        setHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = nodeMap.get(key);
        if (node != null) {
            node.value = value;
            removeNode(node);
            setHead(node);
        } else {
            // node not exist, going to create
            node = new Node(key, value);
            if (nodeMap.size() >= capacity) {
                int lastKey = tail.key;
                removeNode(tail);
                nodeMap.remove(lastKey);
                setHead(node);
            } else {
                setHead(node);
            }
            nodeMap.put(key, node);
        }
    }

    private void removeNode(Node node) {
        // we only need to care current node (prev, next)
        // because it will cause NPE when either is null

        // if node.next is NULL case (TAIL case)
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        // if node.perv is NULL case (HEAD case)
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
    }

    private void setHead(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            // current node
            node.prev = null;
            node.next = head;
            // old head
            head.prev = node;
            // new head
            head = node;
        }
    }

    public void printList() {
        Node curr = head;
        System.out.print("List: ");
        while (curr != null) {
            System.out.print("[" + curr.key + ", " + curr.value +"] -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 1 /* capacity */ );
        cache.put(2, 1);
        Assert.assertEquals(1, cache.get(2));


//        LRUCache cache = new LRUCache( 2 /* capacity */ );
//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(1));       // returns 1
//        cache.put(3, 3);    // evicts key 2
//        System.out.println(cache.get(2));       // returns -1 (not found)
//        cache.put(4, 4);    // evicts key 1
//        System.out.println(cache.get(1));      // returns -1 (not found)
//        System.out.println(cache.get(3));      // returns 3
//        System.out.println(cache.get(4));      // returns 4

//        LRUCache cache = new LRUCache( 2 /* capacity */ );
//        cache.put(2, 1);
//        cache.printList();
//        cache.put(2, 2);
//        cache.printList();
//        System.out.println(cache.get(2));       // returns 2
//        cache.printList();
//        cache.put(1, 1);    // evicts key 2
//        cache.printList();
//        cache.put(4, 1);    // evicts key 1
//        cache.printList();
//        System.out.println(cache.get(2));      // returns -1 (not found)
    }

}
