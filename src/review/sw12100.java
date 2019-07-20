package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 20/07/2019
 * SW 역량 기출 2048 Review.
 * 큐에 다 넣고 빼면서 검사해야 하는데,
 * 큐에 하나 넣고 빼고 검사하고, 하나 넣고 빼고 검사하고 이렇게 해서 틀렸었음.
 */
public class sw12100 {
    private static int n, max;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = parse(in[j]);
            }
        }

        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int depth) {
        // 원본 배열을 변경시키기 때문에 유지해야 한다.
        // 따라서 배열을 복사하여 복구시키기 위해서 사용한다.
        int[][] tmpMap = new int[n][n];
        copy(tmpMap, map);

        // 상,하,좌,우 각 방향에 대해서 5번씩 탐색한다.
        // 5번 탐색 후에 결과를 출력한다.
        if (depth == 5) {
            findAnswer();
            return;
        }

        // 상,하,좌,우 네 방향에 대해서 탐색을 진행한다.
        for (int i = 0; i < 4; i++) {
            move(i);
            dfs(depth + 1);
            copy(map, tmpMap);
        }
    }

    // 정답을 출력하는 함수.
    private static void findAnswer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }

    // 방향에 따라서 블록을 합친다.
    private static void move(int d) {
        int[][] newMap = new int[n][n];
        LinkedList<Integer> q = new LinkedList<>();

        // 상.
        if (d == 0) {
            // 위쪽으로 합칠 수 있는 부분을 확인하기 위하여
            // 위에서부터 빼서 큐에 넣는다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[j][i] != 0) {
                        q.add(map[j][i]);
                    }

                }
                /*
                 그리고 인덱스를 0으로 시작하여 위에서부터 차근 차근 newMap 에 넣는다.
                 큐에서 뺀 값인 value 를 먼저, 0인 곳에 넣는다.
                 그리고 다음에 뺀 값을 할당하기 전에 배열에 들어가 있는 값과 같은지 확인한다.
                 이전 값과 현재 뺀 값이 같다면 합칠 수 있기 때문에 합치고, index 를 증가시킨다.
                 그리고 다음 값은 다음 칸에 할당한다.
                 이로 인해서 3개의 값이 같더라도 한 번 합쳐지므로
                 한 번의 이동으로 합쳐진 블록이 다시 합쳐지는 것을 방지한다.
                 */
                int index = 0;
                while (!q.isEmpty()) {
                    int value = q.remove();
                    if (newMap[index][i] == 0) {
                        newMap[index][i] = value;
                    } else if (newMap[index][i] == value) {
                        newMap[index][i] += value;
                        index++;
                    } else {
                        index++;
                        newMap[index][i] = value;
                    }
                }
            }
        } else if (d == 1) { // 하.
            for (int i = 0; i < n; i++) {
                for (int j = n - 1; j >= 0; j--) {
                    if (map[j][i] != 0) {
                        q.add(map[j][i]);
                    }

                }
                int index = n - 1;
                while (!q.isEmpty()) {
                    int value = q.remove();
                    if (newMap[index][i] == 0) {
                        newMap[index][i] = value;
                    } else if (newMap[index][i] == value) {
                        newMap[index][i] += value;
                        index--;
                    } else {
                        index--;
                        newMap[index][i] = value;
                    }
                }
            }
        } else if (d == 2) { // 좌.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != 0) {
                        q.add(map[i][j]);
                    }

                }
                int index = 0;
                while (!q.isEmpty()) {
                    int value = q.remove();
                    if (newMap[i][index] == 0) {
                        newMap[i][index] = value;
                    } else if (newMap[i][index] == value) {
                        newMap[i][index] += value;
                        index++;
                    } else {
                        index++;
                        newMap[i][index] = value;
                    }
                }
            }

        } else if (d == 3) { // 우.
            for (int i = 0; i < n; i++) {
                for (int j = n - 1; j >= 0; j--) {
                    if (map[i][j] != 0) {
                        q.add(map[i][j]);
                    }
                }

                int index = n - 1;
                while (!q.isEmpty()) {
                    int value = q.remove();
                    if (newMap[i][index] == 0) {
                        newMap[i][index] = value;
                    } else if (newMap[i][index] == value) {
                        newMap[i][index] += value;
                        index--;
                    } else {
                        index--;
                        newMap[i][index] = value;
                    }
                }
            }
        }

        copy(map, newMap);
    }

    private static void copy(int[][] copyed, int[][] original) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyed[i][j] = original[i][j];
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}