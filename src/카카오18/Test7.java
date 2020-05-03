package 카카오18;

/**
 * created by victory_woo on 2020/05/03
 * 카카오 18 기출.
 * [1차] 추석 트래픽.
 */
public class Test7 {
    public static void main(String[] args) {
        //String[] lines = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        //String[] lines = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
        String[] lines = {
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        };
        System.out.println(solution(lines));
    }

    private static final int MILLI = 1000;

    public static int solution(String[] lines) {
        int len = lines.length;
        int[] startTimes = new int[len];
        int[] endTimes = new int[len];
        int answer = 0;

        // 파싱해서 startTime, endTime 값으로 쪼갠다.
        getTimes(lines, startTimes, endTimes);

        // 시작점을 기준으로 하는 윈도우를 만들어 처리량을 확인한다.
        for (int i = 0; i < len; i++) {
            int count = 0;
            int startPoint = startTimes[i];
            int endPoint = startPoint + 1000;

            for (int j = i; j < len; j++) {
                if (startPoint <= startTimes[j] && startTimes[j] < endPoint) {
                    count++;
                } else if (startPoint <= endTimes[j] && endTimes[j] < endPoint) {
                    count++;
                } else if (startTimes[j] <= startPoint && endPoint <= endTimes[j]) {
                    count++;
                }
            }

            if (answer < count) answer = count;
        }

        // 끝점을 기준으로 하는 윈도우를 만들어 처리량을 확인한다.
        for (int i = 0; i < len; i++) {
            int count = 0;
            int startPoint = endTimes[i];
            int endPoint = startPoint + 1000;

            for (int j = i; j < len; j++) {
                if (startPoint <= startTimes[j] && startTimes[j] < endPoint) {
                    count++;
                } else if (startPoint <= endTimes[j] && endTimes[j] < endPoint) {
                    count++;
                } else if (startTimes[j] <= startPoint && endPoint <= endTimes[j]) {
                    count++;
                }
            }

            if (answer < count) answer = count;
        }

        return answer;
    }

    // 시작 시간과 종료 시간을 구해서 배열을 채운다.
    private static void getTimes(String[] lines, int[] startTimes, int[] endTimes) {
        for (int i = 0; i < lines.length; i++) {
            String[] times = lines[i].split(" ");
            String[] responseTime = times[1].split(":");
            int processTime = (int) (toDouble(times[2].substring(0, times[2].length() - 1)) * 1000); // 처리 시간을 구하고 밀리초 처리까지 한다.
            int startTime, endTime = 0;

            endTime += toInt(responseTime[0]) * 3600 * MILLI;
            endTime += toInt(responseTime[1]) * 60 * MILLI;
            endTime += (int) (toDouble(responseTime[2]) * MILLI);
            System.out.println(endTime);
            startTime = endTime - processTime + 1;

            startTimes[i] = startTime;
            endTimes[i] = endTime;
        }
    }

    private static double toInt(String value) {
        return Integer.parseInt(value);
    }

    private static double toDouble(String value) {
        return Double.parseDouble(value);
    }
}
