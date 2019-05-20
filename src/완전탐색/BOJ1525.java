package 완전탐색;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * created by victory_woo on 20/05/2019
 * 완탐 : 퍼즐
 */
public class BOJ1525 {
    private static final int N = 3;
    // 네 방향을 검사하기 위한 dx, dy 배열.
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int start = 0;

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(input[j]);
                if (temp == 0) {
                    temp = 9;
                }

                start = start * 10 + temp; // 2차원 배열의 값을 1차원으로 관리하기 위해 저장하는 변수.
            }
        }
        bfs(start);
        bw.flush();
    }

    private static void bfs(int start) throws IOException {
        LinkedList<Integer> q = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        q.add(start);
        map.put(start, 0); // start 가 key, count 가 value 가 된다.

        while (!q.isEmpty()) {
            int nowNumber = q.remove();
            String now = String.valueOf(nowNumber); // 큐에서 뺀 값을 문자열로 변환한다.
            int nine = now.indexOf('9'); // 9의 인덱스를 찾는다.
            int x = nine / 3; // 행이 3의 몫이 되기 때문에 9가 위치하고 있는 인덱스를 찾고 3으로 나눈다.
            int y = nine % 3; // 열이 3의 나머지가 되기 때문에 9가 위치하고 있는 인덱스를 찾고 3으로 나누어 떨어진 값을 취한다.

            // 네 방향을 검사하기 위해서.
            // 9가 존재하는 위치에서 갈 수 있는 네 방향을 모두 검사한다.
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 내에 존재하면
                if (0 <= nx && nx < 3 && 0 <= ny && ny < 3) {
                    StringBuilder next = new StringBuilder(now); // now 를 통해 StringBuilder 객체를 생성한다.

                    // 현재 위치와 다음 위치를 교환한다.
                    char tempValue = next.charAt(x * 3 + y); // 9가 존재하는 값을 임시 값에 저장.
                    next.setCharAt(x * 3 + y, (next.charAt(nx * 3 + ny)));
                    next.setCharAt(nx * 3 + ny, tempValue);

                    // 위치가 바뀐 next 를 다시 숫자로 변환해서
                    // map 에 포함되어 있는지 검사하고 포함되지 않았다면 map 에 넣어준다.
                    int number = Integer.parseInt(next.toString());
                    if (!map.containsKey(number)) {
                        q.add(number);
                        map.put(number, map.get(nowNumber) + 1); // 전에 저장된 value 값을 기반으로 횟수를 저장한다.
                    }
                }
            }
        }

        // 123456789 키값으로 저장된 값이 있으면 반환하고 없다면 -1을 반환한다.
        bw.write(map.getOrDefault(123456789, -1) + "");
    }
}
