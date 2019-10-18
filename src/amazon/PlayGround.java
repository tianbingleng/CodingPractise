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

package amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayGround {
    public static void main(String[] args) {
        List<String> filterStr = calcSubSets(3, 1);
        System.out.println(filterStr);
    }
    private static List<String> calcSubSets (int n, int subSetCount) {
        List<String> subSets = new ArrayList<>();
        if (n < subSetCount) {
            return subSets;
        }
        double subSetSize = (double) n / (double) subSetCount;
        for (int i = 1; i <= subSetCount; i++) {
            int lowerBound = (int) Math.floor((i - 1) * subSetSize);
            int upperBound = (int) (Math.floor(i * subSetSize) == n ?
                Math.floor(i * subSetSize) : Math.floor(i * subSetSize) - 1);
            String subSet = "[" + Integer.toString(lowerBound) + "," + Integer.toString(upperBound) + "]";
            subSets.add(subSet);
        }
        return subSets;
    }
}
