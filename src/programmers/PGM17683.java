package programmers;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * created by victory_woo on 2020/05/29
 * 방금그곡.
 */
public class PGM17683 {
    public static void main(String[] args) {
        //System.out.println(solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        //System.out.println(solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        System.out.println(solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
    }

    // 재생 시간이 가장 긴 음악이 먼저 오도록 정렬 기준을 정한다.
    // 만약, 재생 시간이 같다면 가장 먼저 입력된 음악이 반환될 것이다.
    // 배열 기반의 ArrayList 이기 때문에 먼저 추가 된 것이 인덱스가 작아서 먼저 반환된다.(배열의 인덱스)
    private static Comparator<Node> cmp = (a, b) -> b.playTime - a.playTime;
    private static String[] sharp = {"C#", "D#", "F#", "G#", "A#"};
    private static String[] basic = {"c", "d", "f", "g", "a"};

    public static String solution(String m, String[] musicinfos) {
        ArrayList<Node> result = new ArrayList<>();
        // 1. 먼저, 샵을 전부 소문자로 변환한다.
        m = convert(m);

        // 2. 음악 정보를 ,(콤마)로 구분하여 재생 시작, 종료 시간과 음악 제목, 악보를 얻는다.
        for (String info : musicinfos) {
            String[] infos = info.split(",");

            // 3. 재생 시간을 구한다.
            int playTime = Math.abs(getMinute(infos[0]) - getMinute(infos[1]));
            String title = infos[2];

            // 4. 악보도 샵을 전부 소문자로 변환한다.
            String content = convert(infos[3]);
            int len = content.length();

            // 5. 재생 시간에 따라 악보를 재생한다.
            // 1분에 악보의 음이 1개씩 재생된다.
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < playTime; i++) sb.append(content.charAt(i % len));

            // 6. 재생된 음악이 네오가 기억하던 멜로디를 포함하고 있다면 result 에 추가한다.
            // 네오가 기억한 멜로디가 여러 음악의 부분과 일치할 수 있기 때문에 1개가 아닐 수 있다.
            // 따라서 리스트에 저장한다.
            if (sb.toString().contains(m)) {
                result.add(new Node(title, playTime));
            }
        }

        // 리스트가 비어있는 경우는 네오가 기억한 멜로디가 포함된 음악이 없다는 것이다.
        // 따라서 (None) 을 반환한다.
        if (result.isEmpty()) return "(None)";
        else {
            // 그렇지 않다면 조건을 만족하는 음악이 여러 개 있고, 이 중에서 재생 시간이 긴 음악 제목을 반환한다.
            // 재생 시간이 같은 것이 여러 개 있다면 가장 먼저 입력된 음악을 반환한다.
            result.sort(cmp);
            return result.get(0).title;
        }
    }

    // C#, D#, A# 등을 소문자로 변환한다.
    // 그래야 C와 C#이 다르다는 걸 구별할 수 있다.
    // ABC, ABC#은 다르다. ABC, ABc가 되기 때문!
    private static String convert(String m) {
        for (int i = 0; i < sharp.length; i++) {
            m = m.replaceAll(sharp[i], basic[i]);
        }
        return m;
    }

    // 시간을 분으로 환산한다.
    private static int getMinute(String time) {
        int sum = 0;
        String[] times = time.split(":");
        sum += toInt(times[0]) * 60;
        sum += toInt(times[1]);
        return sum;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        String title;
        int playTime;

        public Node(String title, int playTime) {
            this.title = title;
            this.playTime = playTime;
        }
    }
}
