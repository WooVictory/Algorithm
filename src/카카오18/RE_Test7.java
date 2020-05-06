package 카카오18;

/**
 * created by victory_woo on 2020/05/06
 */
public class RE_Test7 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{
                "2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"}));

        System.out.println(solution(new String[]{
                "2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"}));
    }

    private static final int MILLI = 1000;

    public static int solution(String[] lines) {
        int answer = 0;
        int len = lines.length;
        int[] startTimes = new int[len];
        int[] endTimes = new int[len];

        getTimes(lines, startTimes, endTimes);

        // 시작점과 끝점, 각각을 기준으로 윈도우를 만들어 확인한다.
        for (int i = 0; i < len; i++) {

            // 시작점을 기준으로 윈도우를 만든다.
            int startCount = 0;
            int startPoint = startTimes[i], startSection = startPoint + MILLI;

            // 다른 로그의 시작점이 윈도우에 걸치는 지
            // 다른 로그의 끝점이 윈도우에 걸치는 지
            // 로그가 윈도우를 포함하는 지
            for (int j = 0; j < len; j++) {
                if (startPoint <= startTimes[j] && startTimes[j] < startSection) {
                    startCount++;
                } else if (startPoint <= endTimes[j] && endTimes[j] < startSection) {
                    startCount++;
                } else if (startTimes[j] <= startPoint && startSection <= endTimes[j]) {
                    startCount++;
                }
            }

            // 최대값을 갱신한다.
            if (answer < startCount) answer = startCount;

            // 끝점을 기준으로 윈도우를 만든다.
            int endCount = 0;
            int endPoint = endTimes[i], endSection = endPoint + MILLI;

            // 다른 로그의 시작점이 윈도우에 걸치는 지
            // 다른 로그의 끝점이 윈도우에 걸치는 지
            // 다른 로그가 윈도우를 포함하는 지
            for (int j = 0; j < len; j++) {
                if (endPoint <= startTimes[j] && startTimes[j] < endSection) {
                    endCount++;
                } else if (endPoint <= endTimes[j] && endTimes[j] < endSection) {
                    endCount++;
                } else if (startTimes[j] <= endPoint && endSection <= endTimes[j]) {
                    endCount++;
                }
            }

            // 최대값을 갱신한다.
            if (answer < endCount) answer = endCount;
        }

        return answer;
    }

    private static void getTimes(String[] lines, int[] startTimes, int[] endTimes) {
        for (int i = 0; i < lines.length; i++) {
            String[] times = lines[i].split(" ");
            String[] responseTimes = times[1].split(":");

            int endTime = 0, starTime = 0, processTime = 0;
            processTime = (int) (toDouble(times[2].substring(0, times[2].length() - 1)) * MILLI);

            endTime += toInt(responseTimes[0]) * 3600 * MILLI;
            endTime += toInt(responseTimes[1]) * 60 * MILLI;
            endTime += (int) (toDouble(responseTimes[2]) * MILLI);

            starTime = endTime - processTime + 1;
            startTimes[i] = starTime;
            endTimes[i] = endTime;
        }
    }

    private static double toDouble(String value) {
        return Double.parseDouble(value);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

}
