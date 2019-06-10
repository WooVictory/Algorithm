package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 08/06/2019
 * bfs : 퍼즐
 */
public class BOJ1525 {
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private static int start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                int number = parse(in[j]);
                if (number == 0) {
                    number = 9;
                }
                start = start * 10 + number;
            }
        }

        bfs(start);
    }

    private static void bfs(int start) {
        // q : bfs 를 탐색하기 위해 사용한다.
        // map : 찾는 숫자가 몇번만에 이동할 수 있는지를 저장하기 위해 사용한다.
        LinkedList<Integer> q = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        q.add(start);
        map.put(start, 0);

        while (!q.isEmpty()) {
            int nowNumber = q.remove();
            String nowStr = String.valueOf(nowNumber);
            int nine = nowStr.indexOf('9'); // 9가 위치하는 곳을 찾는다.

            // 9가 위치하는 곳을 2차원 배열과 같이 인덱스화 한다.
            int x = nine / 3;
            int y = nine % 3;

            // 퍼즐을 옮길 수 있는 4 방향에 대해서 탐색한다.
            // 9가 위치한 퍼즐이 다른 방향으로 이동할 수 있는지 탐색하는 것이다.
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 해당 범위를 만족하는지 검사한다.
                if (-1 < nx && nx < 3 && -1 < ny && ny < 3) {
                    // String 연산을 줄이기 위해 StringBuilder 를 사용한다.
                    StringBuilder sb = new StringBuilder(nowStr);

                    // swap 하는 과정.
                    char temp = sb.charAt(x * 3 + y);
                    sb.setCharAt(x * 3 + y, sb.charAt(nx * 3 + ny));
                    sb.setCharAt(nx * 3 + ny, temp);

                    // int 로 다시 변환한다.
                    int number = Integer.parseInt(sb.toString());
                    // map 에 해당 key 값으로 저장된 값이 있는지 확인한다.
                    // 없으면 큐에 넣고, map 에 넣으면서 현재 nowNumber 가 등장한 횟수에 +1을 해준다.
                    if (!map.containsKey(number)) {
                        q.add(number);
                        map.put(number, map.get(nowNumber) + 1);
                    }
                }
            }
        }
        System.out.println(map.getOrDefault(123456789, -1));
    }


    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
