package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 18/07/2019
 * 2048(easy)
 * 조지게 어렵네..
 */
public class sw2048 {
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
        // 이전 상태로 돌아가기 위해 map 원본을 복사해서 가지고 있어야 한다.
        int[][] tmpMap = new int[n][n];
        copy(tmpMap, map);

        if (depth == 5) {
            findMaxNumber();
            return;
        }

        for (int i = 0; i < 4; i++) {
            merge(i);
            dfs(depth + 1);
            // map 배열에 newMap 배열이 복사되기 때문에
            // dfs 가 끝나서 돌아오면 map 배열을 원래대로 돌려놔야 한다.
            copy(map, tmpMap);
        }
    }

    // 최대값을 찾는 함수.
    private static void findMaxNumber() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (max < map[i][j]) {
                    max = map[i][j];
                }
            }
        }
    }

    // 이전의 배열 값들을 유지하기 위해서 복사해야 한다.
    private static void copy(int[][] arr1, int[][] arr2) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(arr2[i], 0, arr1[i], 0, n);
        }
    }

    private static void merge(int d) {
        LinkedList<Integer> q = new LinkedList<>();
        int[][] newMap = new int[n][n];

        // 상인 경우.
        if (d == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 0이 아닌 값들만 큐에 넣는다.
                    if (map[j][i] != 0) {
                        q.add(map[j][i]);
                    }
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
                        newMap[index][i] = q.poll();
                    }
                }
            }
        }

        // 하인 경우.
        if (d == 1) {
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
        }

        // 좌측인 경우.
        if (d == 2) {
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
        }

        // 우측인 경우.
        if (d == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = n - 1; j >= 0; j--) {
                    if (map[i][j] != 0) {
                        q.add(map[i][j]);
                    }
                }
                int index = n - 1;
                while (!q.isEmpty()) {
                    int value = q.peek();
                    if (newMap[i][index] == 0) {
                        newMap[i][index] = q.remove();
                    } else if (newMap[i][index] == value) {
                        newMap[i][index] += q.remove();
                        index--;
                    } else {
                        index--;
                        newMap[i][index] = q.remove();
                    }
                }
            }
        }

        copy(map, newMap);
    }


    private static void printMap(int[][] m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}