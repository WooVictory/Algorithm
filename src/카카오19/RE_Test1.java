package 카카오19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by victory_woo on 2020/05/07
 * 카카오 19 기출.
 * 다시 푸는 중.
 * 오픈 채팅방.
 */
public class RE_Test1 {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        System.out.println(solution(record));
    }


    private static final String ENTER_DESC = "들어왔습니다.";
    private static final String LEAVE_DESC = "나갔습니다.";
    private static final String ENTER = "Enter";
    private static final String LEAVE = "Leave";
    private static final String CHANGE = "Change";

    public static String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        List<Information> list = new ArrayList<>();

        for (String s : record) {
            String[] cmd = s.split(" ");
            String uid = cmd[1];
            switch (cmd[0]) {
                case ENTER:
                    map.put(uid, cmd[2]);
                    list.add(new Information(uid, ENTER_DESC));
                    break;
                case LEAVE:
                    list.add(new Information(uid, LEAVE_DESC));
                    break;
                case CHANGE:
                    map.put(uid, cmd[2]);
                    break;
            }
        }

        String[] answer = new String[list.size()];
        StringBuilder sb;
        for (int i = 0; i < answer.length; i++) {
            Information info = list.get(i);
            String name = map.get(info.uid);
            sb = new StringBuilder(name);
            answer[i] = sb.append("님이 ").append(info.message).toString();
            System.out.println(answer[i]);
        }

        return answer;
    }

    static class Information {
        String uid;
        String message;

        Information(String uid, String message) {
            this.uid = uid;
            this.message = message;
        }
    }
}