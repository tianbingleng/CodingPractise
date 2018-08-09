////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 * Copyright © 2018 Unified Social, Inc.
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

package OOD.VendingMachine;

public enum Item {
    COKE("Coke", 25),
    PEPSI("Pepsi", 35),
    SODA("Soda", 45);

    private String name;
    private int price;

    private Item(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public long getPrice(){
        return price;
    }
}
