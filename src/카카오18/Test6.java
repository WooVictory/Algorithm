package 카카오18;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/01
 * 카카오 18 기출.
 * [1차] 셔틀버스.
 */
public class Test6 {
    public static void main(String[] args) {
        System.out.println(solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
    }

    // n : 셔틀 버스의 운행 횟수
    // t : 셔틀 버스의 간격
    // m : 셔틀 버스에 태울 수 있는 크루의 제한 인원
    public static String solution(int n, int t, int m, String[] timetable) {
        int len = timetable.length;
        int[] minutes = new int[len];
        boolean[] visit = new boolean[len];

        // 1. 시간을 분으로 만들어서 minutes 배열에 담는다.
        for (int i = 0; i < len; i++) {
            String[] line = timetable[i].split(":");
            int minute = toInt(line[0]) * 60 + toInt(line[1]);
            minutes[i] = minute;
        }

        // 대기열 도착 순으로 정렬한다.
        Arrays.sort(minutes);

        int shuttleTime = 540; // 셔틀 버스의 첫 출발 시간 = 09:00을 분으로 표현함.
        int conTime = -1;

        for (int i = 1; i <= n; i++) {
            int count = 0;

            for (int index = 0; index < len; index++) {
                if (minutes[index] <= shuttleTime && !visit[index]) {
                    visit[index] = true;
                    count++;
                }

                // 버스에 정원을 다 태웠고, 마지막 버스인 경우.
                // 콘은 버스에 타야하기 때문에 마지막에 탄 크루보다 1분 일찍 도착하면 그 크루는 대기열에 남아있게 되고, 콘이 탈 수 있다.
                if (m <= count) {
                    if (i == n) conTime = minutes[index] - 1;
                    break;
                }
            }

            // 셔틀 버스의 간격 시간을 더해준다.
            shuttleTime += t;
        }

        // 버스에 대기열에 있는 크루를 다 태우고, 빈 좌석이 남아 있는 경우.
        // 콘은 마지막 버스에 타면 된다.
        if (conTime == -1) {
            conTime = 540 + (n - 1) * t;
        }

        // conTime 을 시간과 분으로 만든다.
        int hh = conTime / 60;
        int mm = conTime % 60;

        // 그리고 StringBuilder 객체를 이용해 시간과 분을 00:00 형태로 만든다.
        StringBuilder sb = new StringBuilder();

        // 0보다 작은 애들은 01,02,09 등의 시간 표현을 위해 이런 작업을 진행한다.
        // 시간과 분 모두 적용된다.
        sb.append((hh / 10 == 0) ? "0" + hh : String.valueOf(hh));
        sb.append(":");
        sb.append((mm / 10 == 0) ? "0" + mm : String.valueOf(mm));

        return sb.toString();
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}