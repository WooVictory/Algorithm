package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/05
 * 2048
 * 시뮬레이션 구현 문제.
 */
public class sw12100 {
    private static int n, max;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int count) {
        // 원본 배열은 copyMap 에 복사해둔다.
        // 나중에 dfs()가 끝나서 돌아왔을 때, 복구하기 위함이다.
        int[][] copyMap = new int[n][n];
        copy(copyMap, map);

        // 5번 탐색을 했으면 최댓값을 찾는다.
        if (count == 5) {
            printResult();
            return;
        }

        // 상,하,좌,우 방향에 대해서 각각 5번씩 탐색을 한다.
        // 왜냐하면 5번이 최대 탐색 횟수이기 때문!
        for (int i = 0; i < 4; i++) {
            move(i);
            dfs(count + 1);
            copy(map, copyMap);
            // 마지막에 원본을 저장했던 copyMap 으로 map 배열을 복구한다.(초기 상태로)
            // 이렇게 해야 다음 for 문을 진행할 때, 원본 map 배열을 사용할 수 있다.
        }
    }

    /**
     * 위쪽으로 이동하는 경우를 기준으로 설명한다.
     * 방향이 다를 뿐, 로직은 동일하기 때문이다.
     * 큐에 있는 원소를 먼저 확인한다.
     * 위쪽으로 합치기 때문에 맨 윗쪽부터 보기 위해 index 가 0부터 시작한다.
     * 처음에는 0이기 때문에 큐에서 꺼낸 값을 넣는다.
     * 그 자리에는 큐에서 꺼낸 원소가 있기 때문에 0이 아니다. 그리고 큐에서 방금 꺼내서 본 원소가
     * newMap 자리에 있는 원소와 같다면 합칠 수 있다. 따라서 newMap 에 합쳐준다. 그리고 index 를 증가시켜준다.
     * 증가시킨 index 에 대해서 newMap 은 0이기 때문에 큐에서 꺼내서 넣는다.
     * 2     4
     * 2  -> 2
     * 2     0
     * */
    // 방향에 따라서 움직여준다.
    private static void move(int dir) {
        LinkedList<Integer> q = new LinkedList<>();
        // 합쳐진 값들을 담을 배열.
        int[][] newMap = new int[n][n];
        switch (dir) {
            case 0:
                // 상 즉, 위쪽인 경우.
                // 0이 아닌 원소들을 큐에 넣어준다.
                // 위쪽 방향으로 합치는 것이기 때문에 위의 원소를 먼저 넣어준다.
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[j][i] != 0) q.add(map[j][i]);
                    }

                    int index = 0;
                    while (!q.isEmpty()) {
                        int value = q.peek();
                        if (newMap[index][i] == 0) {
                            newMap[index][i] = q.remove();
                        } else if (newMap[index][i] == value) {
                            newMap[index][i] += q.remove();
                            index++;
                        } else {
                            index++;
                            newMap[index][i] = q.remove();
                        }
                    }
                }
                break;

            case 1:
                // 하 즉, 아래쪽인 경우.
                // 아래 방향으로 합치는 것이기 때문에 아래의 원소를 먼저 넣어준다.
                for (int i = 0; i < n; i++) {
                    for (int j = n - 1; j >= 0; j--) {
                        if (map[j][i] != 0) q.add(map[j][i]);
                    }

                    int index = n - 1;
                    while (!q.isEmpty()) {
                        int value = q.peek();
                        if (newMap[index][i] == 0) {
                            newMap[index][i] = q.remove();
                        } else if (newMap[index][i] == value) {
                            newMap[index][i] += q.remove();
                            index--;
                        } else {
                            index--;
                            newMap[index][i] = q.remove();
                        }
                    }
                }

                break;

            case 2:
                // 좌 즉, 왼쪽인 경우.
                // 왼쪽 방향으로 합치는 것이기 때문에 왼쪽의 원소를 먼저 넣어준다.
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] != 0) q.add(map[i][j]);
                    }

                    int index = 0;
                    while (!q.isEmpty()) {
                        int value = q.peek();
                        if (newMap[index][i] == 0) {
                            newMap[index][i] = q.remove();
                        } else if (newMap[index][i] == value) {
                            newMap[index][i] += q.remove();
                            index++;
                        } else {
                            index++;
                            newMap[index][i] = q.remove();
                        }
                    }
                }
                break;

            case 3:
                for (int i = 0; i < n; i++) {
                    for (int j = n - 1; j >= 0; j--) {
                        if (map[i][j] != 0) q.add(map[i][j]);
                    }

                    int index = n - 1;
                    while (!q.isEmpty()) {
                        int value = q.peek();
                        if (newMap[index][i] == 0) {
                            newMap[index][i] = q.remove();
                        } else if (newMap[index][i] == value) {
                            newMap[index][i] += q.remove();
                            index--;
                        } else {
                            index--;
                            newMap[index][i] = q.remove();
                        }
                    }
                }
                break;
        }

        // 바뀐 newMap -> map 으로 복사한다.
        copy(map, newMap);
    }

    // 최대값을 찾는다.
    private static void printResult() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }

    // 배열을 복사한다.
    private static void copy(int[][] copy, int[][] original) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, n);
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
