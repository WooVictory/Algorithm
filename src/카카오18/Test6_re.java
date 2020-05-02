package 카카오18;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/02
 * 카카오 18 기출.
 * [1차] 셔틀 버스.
 */
public class Test6_re {
    public static void main(String[] args) {
        System.out.println(solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        int len = timetable.length;
        int[] minutes = new int[len];
        boolean[] visit = new boolean[len];

        // 1. timetable 배열을 파싱해서 분으로 표현한다.
        for (int i = 0; i < len; i++) {
            String[] line = timetable[i].split(":");
            int minute = toInt(line[0]) * 60 + toInt(line[1]);
            minutes[i] = minute;
        }

        // 2. 오름차순으로 정렬한다.
        Arrays.sort(minutes);

        int shuttleTime = 540; // 09:00을 분으로 표현한 값이다.
        int conTime = -1;

        // 3. n만큼 버스가 순회하면서 크루를 버스에 태운다. 태운 인원의 수가 제한 인원 수 m보다 작거나 같고
        // 마지막 버스라면, 콘은 이 버스를 타야 하므로 마지막으로 탄 크루의 도착 시간보다 1분 일찍 도착한다.(그래야 콘이 버스에 타고
        // 대기열에 그 크루가 남게 된다.)
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int index = 0; index < len; index++) {
                if (minutes[index] <= shuttleTime && !visit[index]) {
                    visit[index] = true;
                    count++;
                }

                // 제한 인원만큼 버스에 태웠고, 마지막 버스인 경우에는 콘은 이 버스를 타야 된다.
                // 따라서 마지막으로 버스에 탄 사람보다 1분 일찍 대기열에 도착하면 된다.
                if (m <= count) {
                    if (i == n) conTime = minutes[index] - 1;
                    break;
                }
            }

            shuttleTime += t;
        }

        // 4. 이 경우에는 버스에 크루들을 다 태웠는데도 빈 좌석이 있는 경우이다. 따라서 콘은 이 버스를 그냥 타면 된다.
        if (conTime == -1) conTime = 540 + (n - 1) * t;

        // 5. 계산된 콘의 가장 늦은 도착 시간을 다시 파싱하여 문자열 형태로 반환한다.

        int hh = conTime / 60;
        int mm = conTime % 60;

        StringBuilder sb = new StringBuilder();
        // 0보다 작은 경우, 08, 09 등의 형식으로 표현하기 위함!
        sb.append((hh / 10 == 0) ? "0" + hh : String.valueOf(hh));
        sb.append(":");
        sb.append((mm / 10 == 0) ? "0" + mm : String.valueOf(mm));

        return sb.toString();
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
