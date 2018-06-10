package ABC;

import java.util.*;

/**
 * Created by tianbingleng on 3/12/2017.
 */
public class GroupRecommendation {
    public static void main(String[] args) {
        String record1 = "A;B,C,D,E;G1,G2";
        String record2 = "B;A,C;G3,G4";
        String record3 = "C;A,B,E;G2,G4";
        String record4 = "D;A;G1,G4";
        String record5 = "E;A,C;G2,G4";
        String[] inputs = new String[5];
        inputs[0] = record1;
        inputs[1] = record2;
        inputs[2] = record3;
        inputs[3] = record4;
        inputs[4] = record5;

        List<List<String>> result = solution(inputs);

        System.out.println(result);
    }

    public static List<List<String>> solution(String[] inputs) {
        // people   friends set
        Map<String, Set<String>> friendshipMap = new HashMap<>();
        // people   group
        Map<String, Set<String>> groupMap = new HashMap<>();
        String[] peoples = new String[inputs.length];
        int i = 0;
        for (String input : inputs) {
            String[] tokens = input.split(";");
            String people = tokens[0];
            String friends = tokens[1];
            String groups = tokens[2];
            Set<String> friendSet = new HashSet<>();
            Set<String> groupSet = new HashSet<>();
            for (String friend : friends.split(",")) {
                friendSet.add(friend);
            }
            for (String group : groups.split(",")) {
                groupSet.add(group);
            }
            friendshipMap.put(people, friendSet);
            groupMap.put(people, groupSet);
            peoples[i++] = people;
        }

        List<List<String>> result = new ArrayList<>();
        for (String people : peoples) {
            List<String> list = new ArrayList<>();
            Map<String, Integer> groupCount = new HashMap<>();
            for (String friend : friendshipMap.get(people)) {
                for (String group : groupMap.get(friend)) {
                    int count = groupCount.getOrDefault(group, 0);
                    groupCount.put(group, count + 1);
                }
            }
            double size = groupCount.size();
//            if (people.equals("D")) {
//                System.out.println(groupCount);
//            }
            for (String group : groupCount.keySet()) {
                if (!groupMap.get(people).contains(group)) {
                    if ((double)groupCount.get(group) / size >= 0.5 ) {
                        list.add(group);
                    }
                }
            }
            result.add(list);
        }

        return result;

    }
}
