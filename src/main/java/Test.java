import cn.hutool.core.date.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class Node {
    char val;
    Node next;
}

public class Test {

    static Node create(String s) {
        Node root = new Node();
        Node tmp = root;
        for (int i = 0; i < s.length(); i++) {
            tmp.val = s.charAt(i);
            tmp.next = new Node();
            tmp = tmp.next;
        }
        return root;
    }

    static List<String> getTimeInterval(String searchTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 0");
        DateTime today = new DateTime();
        long todayTimeStamp = today.getTime();
        long day = 60 * 60 * 24 * 1000;
        List<String> res = new ArrayList<>();
        if ("全部".equals(searchTime)) {
            return null;
        } else if ("今天".equals(searchTime)) {
            DateTime endTime = new DateTime(todayTimeStamp + day);
            res.add(sdf.format(today));
            res.add(sdf.format(endTime));
        } else if ("昨天".equals(searchTime)) {
            DateTime startTime = new DateTime(todayTimeStamp - day);
            res.add(sdf.format(startTime));
            res.add(sdf.format(today));
        } else if ("七天内".equals(searchTime)) {
            DateTime startTime = new DateTime(todayTimeStamp - 6 * day);
            DateTime endTime = new DateTime(todayTimeStamp + day);
            res.add(sdf.format(startTime));
            res.add(sdf.format(endTime));
        } else if ("七天前".equals(searchTime)) {
            DateTime endTime = new DateTime(todayTimeStamp - 6 * day);
            res.add(null);
            res.add(sdf.format(endTime));
        }
        return res;
    }

}
