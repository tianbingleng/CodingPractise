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

import org.junit.Assert;
import org.junit.Test;


public class LRUCacheTest {

    @Test
    public void testCase1() {
        LRUCache cache = new LRUCache(1);
        cache.put(2, 1);
        Assert.assertEquals(1, cache.get(2));
    }

    @Test
    public void testCase2() {
        LRUCache cache = new LRUCache( 2 /* capacity */ );
        cache.put(1, 1);
        cache.put(2, 2);
        Assert.assertEquals(1, cache.get(1));
        cache.put(3, 3);    // evicts key 2
        Assert.assertEquals(-1, cache.get(2));
        cache.put(4, 4);    // evicts key 1
        Assert.assertEquals(-1, cache.get(1));
        Assert.assertEquals(3, cache.get(3));
        Assert.assertEquals(4, cache.get(4));
    }

//    @Test
//    public void testCase3() {
//        //        LRUCache cache = new LRUCache( 2 /* capacity */ );
////        cache.put(2, 1);
////        cache.printList();
////        cache.put(2, 2);
////        cache.printList();
////        System.out.println(cache.get(2));       // returns 2
////        cache.printList();
////        cache.put(1, 1);    // evicts key 2
////        cache.printList();
////        cache.put(4, 1);    // evicts key 1
////        cache.printList();
////        System.out.println(cache.get(2));      // returns -1 (not found)
//    }

}
