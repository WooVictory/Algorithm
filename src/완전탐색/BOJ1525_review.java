package 완전탐색;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 20/05/2019
 */
public class BOJ1525_review {
    private static final int N = 3;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int start = 0;
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int temp = parse(input[j]);
                if (temp == 0) {
                    temp = 9;
                }

                start = start * 10 + temp;
            }
        }

        bw.write(bfs(start) + "");
        bw.flush();
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    private static int bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        q.add(start);
        map.put(start, 0);

        while (!q.isEmpty()) {
            int nowNumber = q.remove();
            String now = String.valueOf(nowNumber);
            int nine = now.indexOf('9'); // 9의 인덱스를 알아낸다.
            int x = nine / 3;
            int y = nine % 3;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < 3 && 0 <= ny && ny < 3) {
                    StringBuilder next = new StringBuilder(now);
                    // 9가 위치한 곳과 다른 방향과 위치 교환!
                    char tempValue = next.charAt(x * 3 + y);
                    next.setCharAt(x * 3 + y, next.charAt(nx * 3 + ny));
                    next.setCharAt(nx * 3 + ny, tempValue);

                    int number = Integer.parseInt(next.toString());

                    if (!map.containsKey(number)) {
                        q.add(number);
                        map.put(number, map.get(nowNumber) + 1);
                    }
                }
            }
        }
        return map.getOrDefault(123456789, -1);

    }
}
