package 카카오19인턴;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/04
 * 카카오 19 인턴 기출.
 * 불량 사용자.
 */
public class Test3_re {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(solution(user_id, banned_id));
    }

    private static LinkedList<Integer> list;
    private static boolean[] visit;
    private static String[] userId, bannedId;
    private static HashSet<HashSet> hashSets; // 제재 아이디들을 담는다.
    private static int count = 0;

    public static int solution(String[] user_id, String[] banned_id) {
        userId = user_id;
        bannedId = banned_id;

        int n = user_id.length;
        int r = banned_id.length;

        list = new LinkedList<>();
        visit = new boolean[n];
        hashSets = new HashSet<>();
        permutation(n, r);

        return count;
    }

    // 1. 가능한 응모 사용자의 목록의 순열을 모두 구한다.
    private static void permutation(int n, int r) {
        if (list.size() == r) {
            check();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                list.add(i);

                permutation(n, r);
                visit[i] = false;
                list.removeLast();
            }
        }
    }

    // 2. 순열이 만들어지면 불량 사용자 목록과 매칭되는지 확인한다.
    private static void check() {
        boolean flag = true;
        for (int i = 0; i < bannedId.length; i++) {
            flag = checkUser(bannedId[i], userId[list.get(i)]);
            if (!flag) break;
        }

        // 3. 문제의 제한 조건을 처리하기 위함이다.
        // 제재 아이디 목록을 구했을 때, 나열된 순서와 관계없이 목록의 내용이 동일하다면 같은 것으로 처리한다.
        // 예로, user=[frodo, crodo], banned = [*rodo, **odo] 라고 한다면
        // 제재 아이디 목록 = [frodo, crodo], [crodo, frodo] 가 나온다.
        // 서로 다른 순열이지만, banned 와 부합한다. 하지만 조건에 의해 2개가 아닌 1개가 정답이다.
        if (flag) {
            // 제재 아이디 목록에서 아이디 하나씩 담는 set.
            HashSet<String> set = new HashSet<>();

            // list 에서 인덱스를 가지고 와서 set 에 제재 아이디가 될 아이디를 담는다.
            for (int index : list) set.add(userId[index]);

            // hashSets : 제재 아이디들을 담는다.
            // 제재 아이디들을 담는 hashSets 에 이번에 만든 제재 아이디 값들이 존재하는지 확인한다.
            // 존재한다면, 이미 같은 구성의 제재 아이디를 담았으므로 추가하지 않고 카운트도 하지 않는다.
            // 존재하지 않았다면, 해당 구성의 제재 아이디를 담고 카운트 한다.
            if (!hashSets.contains(set)) {
                count++;
                hashSets.add(set);
            }
        }
    }

    // 선택된 응모 사용자 아이디의 목록과 불량 사용자 목록과 매칭되는지 확인한다.
    // 목록 모두가 같아야 한다.
    // 1) 먼저, 길이가 같아야 한다.
    // 2) * 문자는 건너뛰고 다른 문자가 모두 같은지 확인한다.
    private static boolean checkUser(String banned_id, String user_id) {
        if (banned_id.length() != user_id.length()) return false;
        for (int i = 0; i < banned_id.length(); i++) {
            if (banned_id.charAt(i) == '*') continue;

            if (banned_id.charAt(i) != user_id.charAt(i)) return false;
        }
        return true;
    }
}
