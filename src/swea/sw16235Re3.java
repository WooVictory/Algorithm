package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/10
 * 나무 재테크.
 * 구현 문제.
 */
public class sw16235Re3 {
    private static int N, M, K, answer = 0;
    private static int[][] map, fuel;
    private static LinkedList<Tree> trees;
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        M = toInt(in[1]);
        K = toInt(in[2]);

        // N의 최댓값은 10이기 때문에 10으로 배열을 초기화한다.
        map = new int[10][10];
        fuel = new int[10][10];
        trees = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = toInt(in[j]);
                fuel[i][j] = 5;
            }
        }

        int x, y, age;
        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            x = toInt(in[0]);
            y = toInt(in[1]);
            age = toInt(in[2]);

            trees.add(new Tree(x - 1, y - 1, age, true));
        }


        solve();
        System.out.println(answer);
    }

    private static void solve() {
        for (int i = 0; i < K; i++) {
            // 1. 봄. -> 나무가 자신의 나이만큼 땅의 양분을 먹어야 하므로, 나무의 나이가 땅의 양분보다 작거나 같은 경우에만 해당한다.
            for (Tree tree : trees) {
                if (tree.age <= fuel[tree.x][tree.y]) {
                    fuel[tree.x][tree.y] -= tree.age;
                    tree.age++;
                } else {
                    tree.alive = false;
                }
            }

            // 2. 여름. -> 봄에 죽은 나무가 양분으로 변하게 되며, 죽은 나무의 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 더해진다.
            // 그리고 trees 리스트에서 해당 나무를 제거한다. 따라서 이터레이터를 사용한다.
            Iterator<Tree> it = trees.iterator();
            while (it.hasNext()) {
                Tree tree = it.next();
                if (!tree.alive) {
                    fuel[tree.x][tree.y] += tree.age / 2;
                    it.remove();
                }
            }

            // 3. 가을. -> 나무의 인접한 8방향으로 번식한다. (나무의 나이가 5의 배수인 경우에만 번식이 가능하다.)
            // 어린 나무의 나이가 앞으로 오면, 나중에 정렬이 필요 없으므로 추가된 나무들을 기존의 나무 앞쪽으로 추가시켜 준다.
            LinkedList<Tree> newTrees = new LinkedList<>();
            for (Tree tree : trees) {
                if (tree.age % 5 == 0) {
                    for (int j = 0; j < 8; j++) {
                        int nx = tree.x + dx[j];
                        int ny = tree.y + dy[j];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                        newTrees.add(new Tree(nx, ny, 1, true));
                    }
                }
            }
            trees.addAll(0, newTrees);

            // 4. 겨울. -> 로봇이 돌아다니면서 기존의 양분을 가지고 있는 땅에 map 배열의 양분을 추가해준다.
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    fuel[j][k] += map[j][k];
                }
            }
        }

        answer = trees.size();
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Tree {
        int x;
        int y;
        int age;
        boolean alive;

        Tree(int x, int y, int age, boolean alive) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.alive = alive;
        }
    }
}
