package programmers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/04/02
 * 여행 경로.
 * bfs/dfs.
 *
 */
public class PGM43164 {
    private static String route;
    private static boolean[] visit;
    private static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) {
        String[][] s = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] s2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        solution(s);
        solution(s2);
    }

    public static String[] solution(String[][] tickets) {
        visit = new boolean[tickets.length];

        for (int i = 0; i < tickets.length; i++) {
            String departure = tickets[i][0], arrive = tickets[i][1];
            if (departure.equals("ICN")) {
                visit[i] = true;
                route = departure + ",";

                dfs(tickets, arrive, 1);
                visit[i] = false;
            }
        }

        Collections.sort(result);
        System.out.println(result.get(0));
        String[] answer = result.get(0).split(",");
        return answer;
    }

    private static void dfs(String[][] tickets, String arrive, int count) {
        route += arrive + ",";
        if (count == tickets.length) {
            result.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            String start = tickets[i][0], end = tickets[i][1];
            if (!visit[i] && arrive.equals(start)) {
                visit[i] = true;
                dfs(tickets, end, count + 1);

                // 백트래킹.
                visit[i] = false;
                route = route.substring(0, route.length() - 4);
            }
        }
    }
}
