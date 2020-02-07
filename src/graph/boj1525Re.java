package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/07
 */
public class boj1525Re {
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int start = 0;
        for (int i = 0; i < 3; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                int num = toInt(in[j]);
                if (num == 0) num = 9;

                // 2차원 배열을 하나의 정수로 만든다.
                start = start * 10 + num;
            }
        }

        bfs(start);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        // map : 해당 정수를 만드는데, 이동한 횟수를 저장한다.
        // key : 해당 정수, value : 이동한 횟수.

        q.add(start);
        map.put(start, 0);

        while (!q.isEmpty()) {
            int nowNum = q.remove();
            String now = String.valueOf(nowNum);
            int index = now.indexOf('9'); // 9가 위치한 인덱스를 찾는다.
            int x = index / 3, y = index % 3;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;

                // 9의 위치를 다음으로 이동할 곳과 바꿔준다.
                StringBuilder sb = new StringBuilder(now);
                char tmp = sb.charAt(3 * x + y);
                sb.setCharAt(3 * x + y, sb.charAt(3 * nx + ny));
                sb.setCharAt(3 * nx + ny, tmp);

                // 정수로 바꾼 뒤, 해당 정수를 키 값으로 하는 값이 map 에 저장되어 있는지 확인한다.
                // 없다면 큐에 해당 정수를 넣고, map 에 정수를 넣는다. value 에는 (지금 정점의 이동횟수 + 1)을 넣어준다.
                int num = toInt(sb.toString());
                if (!map.containsKey(num)) {
                    q.add(num);
                    map.put(num, map.get(nowNum) + 1);
                }
            }
        }

        if (map.containsKey(123456789)) System.out.println(map.get(123456789));
        else System.out.println(-1);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
