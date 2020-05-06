package 카카오19인턴;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 19 인턴 기출.
 * 다시 푸는 중.
 * 불량 사용자.
 */
public class RE_Test3 {
    public static void main(String[] args) {
        //System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
        //System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}));
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
    }

    private static HashSet<HashSet> sets;
    private static LinkedList<Integer> list;
    private static boolean[] visit;
    private static int count = 0;
    private static String[] userId, bannedId;

    public static int solution(String[] user_id, String[] banned_id) {
        userId = user_id;
        bannedId = banned_id;

        int n = user_id.length;
        int k = banned_id.length;

        sets = new HashSet<>();
        list = new LinkedList<>();
        visit = new boolean[n];

        permutation(n, k);
        return count;
    }

    // 1. 순열을 통해서 k개의 선택된 응모 사용자 아이디 목록을 구한다.
    private static void permutation(int n, int k) {
        if (k == list.size()) {
            check();
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                list.add(i);

                permutation(n, k);
                visit[i] = false;
                list.removeLast();
            }
        }
    }

    // 2. banned_id 와 모두 일치하는 목록인지 확인한다.
    private static void check() {
        boolean flag = true;
        for (int i = 0; i < bannedId.length; i++) {
            if (!compareIds(bannedId[i], userId[list.get(i)])) flag = false;

            if (!flag) break;
        }

        // 선택된 응모 사용자의 아이디 목록이 banned_id와 모두 일치하는 경우.
        // 해당 목록의 구성을 하나씩 저장하여 순서가 다르지만, 내용이 같은 아이디 목록이 들어올 경우, 예외 처리를 한다.
        if (flag) {
            HashSet<String> set = new HashSet<>();
            for (int index : list) set.add(userId[index]);

            // 해당 set 구성이 sets 에 포함되지 않은 경우에만 카운트를 증가하고 sets 에 추가한다.
            if (!sets.contains(set)) {
                count++;
                sets.add(set);
            }
        }
    }

    // 불량 사용자 목록의 아이디와 응모 사용자의 아이디가 매칭되는지 확인한다.
    private static boolean compareIds(String banned, String user) {
        if (banned.length() != user.length()) return false;
        for (int i = 0; i < banned.length(); i++) {
            if (banned.charAt(i) == '*') continue;

            if (banned.charAt(i) != user.charAt(i)) return false;
        }
        return true;
    }

}
