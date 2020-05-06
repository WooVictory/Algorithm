package 카카오18;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 18 기출.
 * 다시 푸는 중.
 * 셔틀 버스.
 */
public class RE_Test6 {
    public static void main(String[] args) {
        System.out.println(solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        int len = timetable.length;
        int[] minutes = new int[len];
        boolean[] visit = new boolean[len];

        // 1. timetable -> 분으로 바꿔서 배열에 담아준다.
        for (int i = 0; i < len; i++) {
            String[] time = timetable[i].split(":");
            minutes[i] = toInt(time[0]) * 60 + toInt(time[1]);
        }

        // 2. 오름차순 정렬.
        Arrays.sort(minutes);

        int shuttleTime = 540;
        int conTime = -1;

        // 3. n 회 만큼 버스를 운행한다.
        for (int i = 1; i <= n; i++) {
            int count = 0;
            // 4. 도착 대기열에서 대기 순으로 뽑아 셔틀 버스의 도착 시간보다 작거나 같은 크루만 버스에 태운 뒤, 태웠음을 표시한다.
            for (int j = 0; j < len; j++) {
                if (minutes[j] <= shuttleTime && !visit[j]) {
                    visit[j] = true;
                    count++;
                }

                // 태워야할 인원을 다 태운 경우.
                if (m <= count) {
                    // 만약, 마지막 버스라면 버스에 탄 마지막 크루보다 1분 더 일찍 도착해야 콘이 버스를 탈 수 있다.
                    // 그래야 콘이 버스에 타고, 크루가 대기열에 남는다.
                    if (i == n) conTime = minutes[j] - 1;
                    break;
                }
            }

            // 셔틀 버스의 시간을 증가시킨다.
            shuttleTime += t;
        }

        // 5. 콘이 버스에 타지 못한 경우.
        // 버스에 태워야 할 인원만큼 다 태우지 못한 경우이므로 이때는 좌석이 남아있다.
        // 따라서 콘은 이 버스를 타면 된다.
        if (conTime == -1) {
            conTime = 540 + (n - 1) * t;
        }

        int hh = conTime / 60;
        int mm = conTime % 60;

        StringBuilder sb = new StringBuilder();
        sb.append((hh < 10) ? "0" + hh : String.valueOf(hh));
        sb.append(":");
        sb.append((mm < 10) ? "0" + mm : String.valueOf(mm));

        return sb.toString();
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
