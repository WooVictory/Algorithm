package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/07
 */
public class boj1525 {
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int start = 0;
        for (int i = 0; i < 3; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                int num = toInt(in[j]);
                if (num == 0) num = 9;

                start = start * 10 + num;
            }
        }

        bfs(start);

    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        q.add(start);
        map.put(start, 0);

        while (!q.isEmpty()) {
            int now = q.remove();
            String num = String.valueOf(now);
            int index = num.indexOf('9'); // 9의 인덱스를 찾는다.
            int x = index / 3, y = index % 3; // 9가 위치한 곳의 행과 열의 좌표를 구한다.

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;

                // 위치를 변경한다.
                StringBuilder next = new StringBuilder(num);
                char tmp = next.charAt(3 * x + y);
                next.setCharAt(3 * x + y, next.charAt(3 * nx + ny));
                next.setCharAt(3 * nx + ny, tmp);

                int value = Integer.parseInt(next.toString());
                if (!map.containsKey(value)) {
                    q.add(value);
                    map.put(value, map.get(now) + 1);
                }
            }
        }
        System.out.println(map.getOrDefault(123456789, -1));
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;
        int cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}