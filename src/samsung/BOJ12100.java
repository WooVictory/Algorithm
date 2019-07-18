package samsung;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 18/07/2019
 * 2048 게임 문제.
 * 1. 0이 아닌 블록을 큐에 넣는다.
 * 2. 상하좌우 다 돌면서 탐색한다.
 */
public class BOJ12100 {
    private static int n, max;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = parse(br.readLine());
        map = new int[n][n];

        // 입력.
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = parse(in[j]);
            }
        }

        dfs(0);
        bw.write(String.valueOf(max));
        bw.flush();
    }

    private static void dfs(int depth) {
        // 원본 배열을 복사해서 들고 있어야 한다. 원본 배열이 바뀌기 때문.
        // 나중에 dfs 끝나고 돌아오면 원본 배열을 복구해줘야 한다.
        int[][] copyMap = new int[n][n];
        copy(copyMap, map);

        // 5번 탐색했다면 최대값을 찾아서 출력한다.
        if (depth == 5) {
            findMaxNumber();
            return;
        }

        // 상하좌우 4방향에 대해서 각각 다섯번씩 탐색한다.
        for (int i = 0; i < 4; i++) {
            move(i);
            dfs(depth + 1);
            copy(map, copyMap);
        }
    }

    // 움직여준다.
    private static void move(int direction) {
        LinkedList<Integer> q = new LinkedList<>();
        int[][] newMap = new int[n][n];

        switch (direction) {
            case 0:
                // 상.
                // 0이 아닌 값을 큐에 넣는다.
                // 위쪽으로 합치는 것이므로 위의 원소부터 넣는다.
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[j][i] != 0) {
                            q.add(map[j][i]);
                        }
                    }

                    int index = 0;
                    // 위의 원소를 먼저 꺼내서 위에 차곡 놓는다.
                    // 다음으로 꺼낸 원소가 위에 꺼낸 원소와 같다면
                    // 상단 쪽으로 합친다.
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
                // 하.
                for (int i = 0; i < n; i++) {
                    for (int j = n - 1; j >= 0; j--) {
                        if (map[j][i] != 0) {
                            q.add(map[j][i]);
                        }
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
                // 좌측.
                // 좌측부터 담는게 맞음.
                // 큐에서 왼쪽먼저 넣고 왼쪽을 먼저 꺼낸다.
                // 그리고 왼쪽으로 밀기 때문에 이 순서가 맞음.
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] != 0) {
                            q.add(map[i][j]);
                        }
                    }

                    int index = 0;
                    while (!q.isEmpty()) {
                        int value = q.peek();
                        if (newMap[i][index] == 0) {
                            newMap[i][index] = q.remove();
                        } else if (newMap[i][index] == value) {
                            newMap[i][index] += q.remove();
                            index++;
                        } else {
                            index++;
                            newMap[i][index] = q.remove();
                        }
                    }
                }
                break;
            case 3:
                // 우측.
                for (int i = 0; i < n; i++) {
                    for (int j = n - 1; j >= 0; j--) {
                        if (map[i][j] != 0) {
                            q.add(map[i][j]);
                        }
                    }

                    int index = n - 1;
                    while (!q.isEmpty()) {
                        // 처음 원소를 빼지 않고 보기만 한다.
                        int value = q.peek();
                        // 처음 배열의 상태는 0이기 때문에 넣어준다.
                        if (newMap[i][index] == 0) {
                            newMap[i][index] = q.remove();
                            // 그 다음 꺼낸 원소가 이전에 원소의 값과 같다면 합칠 수 있으므로 합친다.
                        } else if (newMap[i][index] == value) {
                            newMap[i][index] += q.remove();
                            index--;
                        } else {
                            // 0도 아니고 같지도 않다면
                            // 원래 map 배열에서의 자리에 넣어준다.
                            index--;
                            newMap[i][index] = q.remove();
                        }
                    }
                }
                break;
        }
        copy(map, newMap);
    }

    // 최대 값을 찾는다.
    private static void findMaxNumber() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }

    // 원본 배열을 copy 배열로 복사한다.
    private static void copy(int[][] copyMap, int[][] originalMap) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = originalMap[i][j];
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}