package 카카오18;

/**
 * created by victory_woo on 2020/05/03
 * 카카오 18 기출
 * [1차] 추석 트래픽.
 */
public class Test7_re {
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

        processTimes(lines, startTimes, endTimes);

        // 시작점을 기준으로 하는 윈도우를 만들어서 처리량을 확인한다.
        for (int i = 0; i < len; i++) {
            int count = 0;
            int startPoint = startTimes[i];
            int section = startPoint + MS;

            for (int j = i; j < len; j++) {
                // 다른 로그의 시작점이 윈도우에 걸쳐있는 경우.
                if (startPoint <= startTimes[j] && startTimes[j] < section) {
                    count++;
                } else if (startPoint <= endTimes[j] && endTimes[j] < section) {
                    // 다른 로그의 끝 점이 윈도우에 걸쳐 있는 경우.
                    count++;
                } else if (startTimes[j] <= startPoint && section <= endTimes[j]) {
                    // 로그가 윈도우를 포함하고 있는 경우.
                    count++;
                }
            }

            if (answer < count) answer = count;
        }

        // 끝 점을 기준으로 하는 윈도우를 만들어서 처리량을 확인한다.
        for (int i = 0; i < len; i++) {
            int count = 0;
            int startPoint = endTimes[i];
            int section = startPoint + MS;

            for (int j = i; j < len; j++) {
                // 다른 처리량의 시작점이 윈도우에 걸쳐있는 경우.
                if (startPoint <= startTimes[j] && startTimes[j] < section) {
                    count++;
                } else if (startPoint <= endTimes[j] && endTimes[j] < section) {
                    // 다른 처리량의 끝 점이 윈도우에 걸쳐 있는 경우.
                    count++;
                } else if (startTimes[j] <= startPoint && section <= endTimes[j]) {
                    // 로그가 윈도우를 포함하고 있는 경우.
                    count++;
                }
            }

            if (answer < count) answer = count;
        }

        return answer;
    }

    // 입력으로 문자열로 구성된 시간이 들어오기 때문에 이를 가공해야 한다.
    // 밀리초를 곱해서 초 단위로 처리할 것이다.
    // 함수 안에서 지역으로 선언하면 endTime 변수가 계산이 한번 진행되면서
    // double 타입에 맞게 타입 캐스팅되어서
    // for 문 안에서 변수를 매번 선언해서 사용하는 편이 낫다.
    private static void processTimes(String[] lines, int[] startTimes, int[] endTimes) {
        for (int i = 0; i < lines.length; i++) {
            // lines 배열을 공백으로 짤라서 앞 부분은 버리고 응답 완료 시간과 처리 시간을 구한다.
            String[] times = lines[i].split(" ");
            String[] responseTime = times[1].split(":");
            int startTime, endTime = 0, processTime;
            processTime = (int) (toDouble(times[2].substring(0, times[2].length() - 1)) * MS);

            endTime += toInt(responseTime[0]) * 3600 * MS;
            endTime += toInt(responseTime[1]) * 60 * MS;
            endTime += (int) (toDouble(responseTime[2]) * MS);
            System.out.println(endTime);

            // 시작시간은 응답 완료 시간 - 처리 시간을 뺀 뒤, 1밀리초를 더하면 된다.
            // 모두 밀리초로 단위를 맞췄기 때문에 여기서는 1밀리초를 더한다.
            // 시작점과 끝 점을 포함하기 때문에 정확한 1초를 계산하기 위함이다.
            startTime = endTime - processTime + 1;

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
