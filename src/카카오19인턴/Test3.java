package 카카오19인턴;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/04
 * 카카오 19 인턴 기출.
 * 불량 사용자.
 */
public class Test3 {
    public static void main(String[] args) {

//        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banned_id = {"*rodo", "*rodo", "******"};

//        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banned_id = {"fr*d*", "abc1**"};
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};

//        String[] user_id = {"frodo", "crodo"};
//        String[] banned_id = {"*rodo", "**odo"};
        System.out.println(solution(user_id, banned_id));
    }

    private static LinkedList<Integer> list;
    private static boolean[] visit;
    private static String[] users, bannedUsers;
    private static int count = 0;

    public static int solution(String[] user_id, String[] banned_id) {
        users = user_id;
        bannedUsers = banned_id;
        int n = user_id.length;
        int k = banned_id.length;

        list = new LinkedList<>();
        visit = new boolean[n];
        permutation(n, k);
        return count;
    }

    // 순열을 구한다.
    private static void permutation(int n, int k) {
        if (list.size() == k) {
            check();
            //print();
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

    private static HashSet<HashSet> set = new HashSet<>();

    private static void check() {
        boolean flag = true;
        for (int i = 0; i < bannedUsers.length; i++) {

            flag = checkId(bannedUsers[i], users[list.get(i)]);
            if (!flag) break;
        }

        if (flag) {
            HashSet<String> check = new HashSet<>();
            for (int index : list) check.add(users[index]);

            if (!set.contains(check)) {
                count++;
                set.add(check);
            }
        }
    }

    private static boolean checkId(String bannedId, String userId) {
        if (bannedId.length() != userId.length()) return false;

        for (int i = 0; i < bannedId.length(); i++) {
            if (bannedId.charAt(i) == '*') continue;

            if (bannedId.charAt(i) != userId.charAt(i)) return false;
        }
        return true;
    }

    private static void print() {
        for (int i : list) System.out.print(users[i] + " ");
        System.out.println();
    }
}