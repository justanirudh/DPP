package competitions;

import java.util.*;

/**
 * Created by paanir on 10/8/17.
 */
public class BlendLabs {


    public static void main(String[] args) {

        Map<String, SortedSet<String>> map = new TreeMap<>();
        Set<String> allAppIds = new TreeSet<>();

        Scanner stdin = new Scanner(System.in);
        stdin.nextLine();
        while (stdin.hasNextLine()) {
            String[] info = stdin.nextLine().split(",");
            String appId = info[3];
            String docType = info[2];
            if(!map.containsKey(docType))
                map.put(docType, new TreeSet<>());
            map.get(docType).add(appId);
            allAppIds.add(appId );
        }

        for(String key : map.keySet()){

            SortedSet<String> appIds = map.get(key);
            Set<String> copyAllSet = new TreeSet<>(allAppIds);
            copyAllSet.removeAll(appIds);
            if(copyAllSet.isEmpty())
                continue;
            System.out.println(key);
            List<String> sortedList = new ArrayList<>(copyAllSet);
            Collections.sort(sortedList);
            String res = String.join(" ", sortedList);
            System.out.println(res);
        }

    }
}
