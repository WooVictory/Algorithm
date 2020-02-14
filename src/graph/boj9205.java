package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/14
 * 맥주 마시면서 걸어가기
 * bfs
 */
public class boj9205 {
    private static Location[] locations;
    private static boolean[] visit;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            n = toInt(br.readLine());
            // 입력을 한꺼번에 받기 위해서 크기를 확장한다.
            locations = new Location[n + 2];
            visit = new boolean[n + 2];

            // 상근이의 집, 편의점, 펜타포트 락 페스티벌의 위치를 모두 locations 배열에 넣어준다.
            for (int i = 0; i < n + 2; i++) {
                String[] in = br.readLine().split(" ");
                int x = toInt(in[0]), y = toInt(in[1]);
                locations[i] = new Location(x, y);
            }

            Location start = locations[0], end = locations[n + 1];
            bfs(start, end);
        }
    }

    private static void bfs(Location start, Location end) {
        LinkedList<Location> q = new LinkedList<>();
        boolean isSuccess = false;
        q.add(start);
        visit[0] = true;

        while (!q.isEmpty()) {
            Location current = q.remove();
            // 상근이의 위치가 도착 지점에 있으면, 도착한 것이므로 isSuccess 를 true 로 바꾸고 탈출한다.
            if (current.equals(end)) {
                isSuccess = true;
                break;
            }

            for (int i = 1; i < n + 2; i++) {
                if (!visit[i] && isPossible(current.x, current.y, locations[i].x, locations[i].y)) {
                    visit[i] = true;
                    q.add(locations[i]);
                }
            }
        }

        System.out.println(isSuccess ? "happy" : "sad");
    }

    // 거리가 1000 이하인지 확인한다.
    // 즉, 1000 이하라는 뜻은 20병을 마시는 동안에 도착할 수 있다는 뜻이다.
    private static boolean isPossible(int lx, int ly, int rx, int ry) {
        return (Math.abs(lx - rx) + Math.abs(ly - ry)) <= 1000;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
