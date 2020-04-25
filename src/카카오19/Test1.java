package 카카오19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/04/24
 * 카카오 19 기출.
 * 오픈 채팅방.
 */
public class Test1 {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        solution(record);
    }

    private static final String ENTER = "Enter", LEAVE = "Leave", CHANGE = "Change";
    private static final String ENTER_DESCRIPTION = "님이 들어왔습니다.";
    private static final String LEAVE_DESCRIPTION = "님이 나갔습니다.";

    public static String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        ArrayList<User> list = new ArrayList<>();

        for (String aRecord : record) {
            String[] str = aRecord.split(" ");
            String action = str[0], uid = str[1];
            switch (action) {
                case ENTER:
                    map.put(uid, str[2]);
                    list.add(new User(uid, ENTER_DESCRIPTION));
                    break;
                case LEAVE:
                    list.add(new User(uid, LEAVE_DESCRIPTION));
                    break;
                case CHANGE:
                    map.put(uid, str[2]);
                    break;
            }
        }

        String name, description;
        StringBuilder sb;
        String[] answer = new String[list.size()];
        User user;

        for (int i = 0; i < list.size(); i++) {
            sb = new StringBuilder();
            user = list.get(i);
            name = map.get(user.uid);
            description = user.description;

            sb.append(name).append(description);
            answer[i] = sb.toString();
            System.out.println(answer[i]);
        }

        return answer;
    }

    static class User {
        String uid;
        String description;

        User(String uid, String description) {
            this.uid = uid;
            this.description = description;
        }
    }
}
