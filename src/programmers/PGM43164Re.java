package programmers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/04/02
 * 여행 경로.
 * 다시 푸는 중!
 * <p>
 * 조건 : 공항은 무조건 3글자.
 */
public class PGM43164Re {
    private static boolean[] visit;
    private static String route;
    private static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) {
        String[][] s = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        solution(s);
    }

    public static String[] solution(String[][] tickets) {
        int n = tickets.length;
        visit = new boolean[n];
        // 항공권을 모두 사용했는지에 대해 방문 여부를 체크하기 위한 배열.

        // 시작과 도착 지점을 뽑아서 시작점이 ICN 인 경우에만 탐색을 시작한다.
        for (int i = 0; i < n; i++) {
            String departure = tickets[i][0], arrive = tickets[i][1];
            if (departure.equals("ICN")) {
                visit[i] = true; // 방문 여부를 체크한다.
                route = departure + ","; // 시작점을 붙여 route 경로를 만든다.
                dfs(tickets, arrive, 1); // dfs() 함수를 호출한다.
                visit[i] = false; // dfs 호출이 끝난 뒤, 해당 인덱스에 대한 visit 배열을 초기화한다.
            }
        }

        Collections.sort(result);
        return result.get(0).split(",");
    }

    // dfs 탐색을 진행한다.
    // 매개변수로 받은 arrive 를 기존의 route 경로와 결합해서 경로를 이어준다.
    private static void dfs(String[][] tickets, String arrive, int count) {
        route += arrive + ",";
        // 종료 조건은 tickets 즉, 모든 항공권을 다 사용한 경우이다.
        // 모든 항공권을 사용했으므로 경로를 만들었기 때문에 해당 경로를 result 에 추가한다.
        if (count == tickets.length) {
            result.add(route);
            return;
        }

        // 티켓을 꺼내서 항공권을 확인한다.
        for (int i = 0; i < tickets.length; i++) {
            String start = tickets[i][0], end = tickets[i][1];
            // 방문한 적이 없고, 매개 변수 arrive 와 티켓의 출발지가 같으면 경로가 이어지므로 조건 만족.
            // 따라서 탐색을 다시할 수 있다.
            if (!visit[i] && arrive.equals(start)) {
                visit[i] = true;
                dfs(tickets, end, count + 1);

                // 백트래킹.
                // 방문 여부를 다시 되돌려준다.
                // 이 경로가 아닌 다른 경로를 통해서 결과 경로를 만들 수 있기 때문에 방문 배열을 되돌려 주고
                // route 에 추가했던 마지막 경로를 빼준다.
                visit[i] = false;
                route = route.substring(0, route.length() - 4);
                // 뒤에 3글자를 빼고 잘라서 다시 route 에 할당해준다.
            }
        }
    }
}