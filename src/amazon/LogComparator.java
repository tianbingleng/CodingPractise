package amazon;

import java.util.*;

public class LogComparator {
    public static void main (String[] args) {

//        String a = "a";
//        String b = "b";
//        String c = "A";
//        String d = "1";
//        System.out.println("Comparing:"+ a + ", "+b+":" + a.compareTo(b)); // a, b
//        System.out.println("Comparing:"+ a + ", "+c+":" + a.compareTo(c)); // A, a
//        System.out.println("Comparing:"+ a + ", "+d+":" + a.compareTo(d)); // 1, a

        // Compare letters, just compareTo() -> it will A-Za-z order.
        // Compare letter & Digits (ID)
        String[] a = {
            "a1 9 2 3 1",
            "g1 Act car",
            "zo4 4 7",
            "ab1 off KEY dog",
            "a8 act zoo"
        };
        String[] b = {
            "mi2 jog mid pet",
            "wz3 34 54 398",
            "a1 alps cow bar",
            "x4 45 21 7"
        };
        String[] c = {
            "t2 13 121 98",
            "r1 box ape bit",
            "b4 xi me nu",
            "br8 eat nim did",
            "w1 has uni gry",
            "f3 52 54 31"
        };

        String[] d = {
            "c1 word only content",
            "ge3 if word only then does not contain numbers",
            "b42 7432",
            "we13 another word only",
            "a1 2352461",
            "a51 word only content"
            };

        List<String> logs = Arrays.asList(d);
        System.out.println("----Original----");
        printLogs(logs);
        System.out.println("----After Sort..----");
        logs = compareLogs(logs);
        printLogs(logs);
    }

    static public List<String> compareLogs(List<String> logs) {
        Collections.sort(logs, new LogsComparator());
        return logs;
    }

    // create a comparator class
    static class LogsComparator implements Comparator<String>{
        @Override
        public int compare(String log1, String log2) {
            // get the rest of the string and id.
            String id1 =  log1.substring(0, log1.indexOf(" "));
            String context1 = log1.substring(id1.length() + 1);
            String id2 =  log2.substring(0, log2.indexOf(" "));
            String context2 = log2.substring(id2.length() + 1);
            // check string context or integer context
            boolean isLog1Integer = Character.isDigit(context1.charAt(0));
            boolean isLog2Integer = Character.isDigit(context2.charAt(0));
            if (isLog1Integer && isLog2Integer) {
                return 0;
            } else if (isLog1Integer) {
                return 1;
            } else if (isLog2Integer){
                return -1;
            }
            // now all are string comparison
            if (context1.compareTo(context2) == 0) {
                return id1.compareTo(id2);
            }
            return context1.compareTo(context2);
        }
    }

    static public void printLogs(List<String> logs) {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
