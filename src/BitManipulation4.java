/**
 * Created by tianbingleng on 18/6/2017.
 */
public class BitManipulation4 {
    static public void main(String[] args) {
//        Test Case for bitReverse
        int x = 29;
        System.out.println(decimalToHex(x));

    }

    static public String decimalToHex(int num) {
        // assume num >= 0
        if (num == 0) {
            return "0x0";
        }
        StringBuilder sb = new StringBuilder("");
        while(num > 0) {
            //append from right digit to left
            sb.append(convert(num % 16));
            num = num / 16;
        }
        sb.append("x0");
        return sb.reverse().toString();
    }
    static char convert(int digit) {
        // assume 0 <= digit <= 15
        if ( digit < 10) {
            return (char) (digit + '0');
        } else {
            return (char) ((digit - 10) + 'A');
        }

    }

}