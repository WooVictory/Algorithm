package 카카오18;

/**
 * created by victory_woo on 2020/05/03
 * 카카오 18 기출
 * [1차] 추석 트래픽.
 */
public class Test7_3 {
    public static void main(String[] args) {
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

    private static final int MS = 1000;

    public static int solution(String[] lines) {
        int len = lines.length;
        int answer = 0;
        int[] startTimes = new int[len];
        int[] endTimes = new int[len];

        getTimes(lines, startTimes, endTimes);

        // 하나씩 시작점과 끝점을 뽑아서 각각을 기준으로 하는 윈도우를 만들어 1초 동안의 처리량을 구한다.
        // 처리량은 1초 동안 겹치는 값들이 존재한다면 1초 안에 처리할 수 있으므로 처리량을 증가시킨다.
        for (int i = 0; i < len; i++) {
            int startCnt = 0, endCnt = 0;
            int startPoint = startTimes[i], endOfStartPoint = startPoint + MS;
            int endPoint = endTimes[i], startOfEndPoint = endPoint + MS;

            for (int j = 0; j < len; j++) {
                if (startPoint <= endTimes[j] && startTimes[j] < endOfStartPoint) {
                    startCnt++;
                }

                if (endPoint <= endTimes[j] && startTimes[j] < startOfEndPoint) {
                    endCnt++;
                }
            }

            // 최대값을 찾기 위해 갱신한다.
            answer = answer > startCnt ? answer : startCnt;
            answer = answer > endCnt ? answer : endCnt;
        }

        // 시작점을 기준으로 하는 윈도우를 생성해서 처리량을 구한다.
        for (int i = 0; i < len; i++) {
            int count = 0;
            int startPoint = startTimes[i];
            int endPoint = startPoint + MS;

            for (int j = i; j < len; j++) {
                if (startPoint <= startTimes[j] && startTimes[j] < endPoint) { // 다른 로그의 시작점이 윈도우에 걸쳐있는 경우.
                    count++;
                } else if (startPoint <= endTimes[j] && endTimes[j] < endPoint) { // 다른 로그의 끝 점이 윈도우에 걸쳐있는 경우.
                    count++;
                } else if (startTimes[j] <= startPoint && endPoint <= endTimes[j]) { // 다른 로그가 윈도우를 포함하는 경우.
                    count++;
                }
            }

            // 최대값을 확인해 저장한다.
            if (answer < count) answer = count;
        }

        // 끝 점을 기준으로 하는 윈도우를 생성해서 처리량을 구한다.
        // 시작점을 처리한 것과 같은 방식이다.
        for (int i = 0; i < len; i++) {
            int count = 0;
            int startPoint = endTimes[i];
            int endPoint = startPoint + MS;

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

    private static void getTimes(String[] lines, int[] startTimes, int[] endTimes) {
        for (int i = 0; i < lines.length; i++) {
            int startTime, endTime = 0, processTime;
            // lines[] 배열을 공백으로 짤라서 응답 완료 시간과 처리 시간을 구한다.
            String[] times = lines[i].split(" ");
            // 그 중에서 응답 완료 시간을 구하기 위해 :으로 짜른다.
            String[] response = times[1].split(":");

            // 처리 시간을 구하기 위해 뒤에 s를 까지 자른 뒤, double 로 처리하여 밀리초를 곱하고 int 로 형 변환한다.
            processTime = (int) (toDouble(times[2].substring(0, times[2].length() - 1)) * MS);

            // 응답 완료 시간을 구하기 위해 h,m,s를 다 따로 구해서 누적해준다.
            // 밀리초 단위로 처리하기 위해 1000(MS)를 곱한다.
            endTime += toInt(response[0]) * 3600 * MS;
            endTime += toInt(response[1]) * 60 * MS;
            endTime += (int) (toDouble(response[2]) * MS);

            // 시작시간은 응답 완료 시간 - 처리 시간 + 1이다.(밀리초 단위이기 때문에 1을 더함)
            startTime = endTime - processTime + 1;

            // 시작 시간과 종료 시간을 표현하는 배열에 값을 담아준다.
            startTimes[i] = startTime;
            endTimes[i] = endTime;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    private static double toDouble(String value) {
        return Double.parseDouble(value);
    }
}
