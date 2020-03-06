package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/05
 * 2048
 * 시뮬레이션
 * 다시 풀어보기!
 */
public class sw12100Re {
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
        // 원본 배열을 newMap 에 copy 해놓는다.
        // map 이 계속해서 바뀌기 때문에 이를 복사해서 저장해 놓는다.
        int[][] copyMap = new int[n][n];
        copy(copyMap, map);

        // 최대 5번 이동시켜서 결과를 찾아야 한다.
        // 최대 값을 찾는다.
        if (count == 5) {
            findMaxValue();
            return;
        }

        // 상,하,좌,우 4방향에 대해서 모두 5번씩 탐색한다.
        for (int i = 0; i < 4; i++) {
            move(i);
            dfs(count + 1);
            copy(map, copyMap);
        }
    }

    // original -> copy 로 복사한다.
    private static void copy(int[][] copy, int[][] original) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = original[i][j];
            }
        }
    }

    // 최대 값을 찾는다.
    private static void findMaxValue() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (max < map[i][j]) max = map[i][j];
            }
        }
    }


    /**
     * 방향에 따라서 이동한다.
     * 왼쪽 방향으로 합치는 경우를 기준으로 설명한다.
     * 이유는 방향만 다를 뿐, 로직은 동일하기 때문이다.
     *
     * 1. 0이 아닌 원소를 큐에 넣는다.
     * 2. 위쪽 방향으로 합치기 때문에 위쪽에 있는 원소를 먼저 큐에 넣도록 한다.
     * 3. 위쪽부터 합치기 때문에 index = 0부터 시작한다.
     * 4. 큐가 비어있는지 확인한다. 큐에서 값을 꺼내진 말고 확인만 한다.
     * 5. newMap 은 처음에는 비어있기 때문에 위쪽 첫 칸에 큐에서 꺼낸 값을 넣는다.
     * 6. 그리고 큐에서 보고 있는 값이 newMap 에 들어가 있는 값과 같다면 두 값을 합칠 수 있다.
     * 따라서, 큐에서 뺀 다음 값을 합치고 index 를 증가시켜준다.
     * 7. index 가 증가했기 때문에 다음 칸은 0이므로 큐에서 뺀 값을 넣어준다.
     * 8. 마지막 else 는 처리되지 않은 곳을 처리하는 부분이다.
     *
     * 2        4
     * 2   ->   2
     * 2        0
     *
     * */
    private static void move(int direction) {
        LinkedList<Integer> q = new LinkedList<>();

        // 합쳐진 값들을 담을 배열.
        int[][] newMap = new int[n][n];

        switch (direction) {
            case 0:
                // 상. 위쪽 방향으로 합치는 경우
                // 위쪽으로 합치기 때문에 위에 있는 원소부터 먼저 넣어준다. (0이 아니어야 한다.)
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
                // 하. 아래쪽 방향으로 합치는 경우.
                // 아래쪽으로 합치기 때문에 아래에 위치한 원소부터 먼저 넣어준다.
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
                // 좌, 왼쪽 방향으로 합치는 경우.
                // 왼쪽으로 합치기 때문에 왼쪽에 위치한 원소부터 먼저 넣어준다.
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
                // 우. 오른쪽 방향으로 합치는 경우.
                // 오른쪽 방향으로 합치기 때문에 오른쪽에 위치한 원소부터 먼저 넣어준다.
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

        copy(map, newMap);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
