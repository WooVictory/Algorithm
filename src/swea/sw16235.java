package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/08
 * 나무 재테크.
 * 구현, 시뮬레이션 문제.
 */
public class sw16235 {
    private static int N, M, K;
    private static int[][] map, f;
    // f : 현재 땅의 양분을 저장할 배열.
    private static LinkedList<Tree> trees;
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    // 문제에서 제시한 대로 8방향에 대해서 delta 배열을 선언한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        M = toInt(in[1]);
        K = toInt(in[2]);

        map = new int[10][10]; // N의 최댓값이 10
        f = new int[10][10];
        trees = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = toInt(in[j]);
                f[i][j] = 5; // 처음에 양분은 모든 칸에 5만큼 들어있다.
            }
        }

        int x, y, z;
        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            x = toInt(in[0]);
            y = toInt(in[1]);
            z = toInt(in[2]);

            trees.add(new Tree(x - 1, y - 1, z, true));
        }

        System.out.println(solve());
    }

    // 봄, 여름, 가을, 겨울을 반복하는데 결국은 K년 만큼 반복해야 한다.
    // 봄 ~ 겨울까지 순서대로 구현하면 된다.
    private static int solve() {

        for (int i = 0; i < K; i++) {
            // 1. 봄.
            for (Tree tree : trees) {
                // 나무의 나이가 나무가 위치한 양분보다 작거나 같으면 나무는 양분을 먹을 수 있고, 나무의 나이는 1증가한다.
                // 양분을 먹었으므로 f 배열에서 나무의 나이만큼 빼준다.
                if (tree.age <= f[tree.x][tree.y]) {
                    f[tree.x][tree.y] -= tree.age;
                    tree.age++;
                } else {
                    tree.alive = false;
                }
            }

            // 2. 여름.
            // 나무가 죽어 있다면 해당 나무의 나이를 2로 나눈 값을 나무가 있던 위치에 양분으로 더해준다.
            // 그리고 리스트에서 그 나무를 제거한다. 따라서 이터레이터를 사용한다.
            Iterator<Tree> it = trees.iterator();
            while (it.hasNext()) {
                Tree tree = it.next();
                if (!tree.alive) {
                    f[tree.x][tree.y] += tree.age / 2;
                    it.remove();
                }
            }


            LinkedList<Tree> newTrees = new LinkedList<>();
            // 3. 가을.
            // 새로 추가되어야 하는 나무의 위치를 구하고 범위를 확안한 뒤, 조건을 만족하면 나무를 리스트에 추가한다.
            // 새로운 나무를 추가해야 하는데, trees 에 대한 for 반복문을 돌고 있기 때문에 하나의 리스트를 생성해서 추가한 뒤, 나중에
            // 반복문이 끝나면 합쳐주도록 한다.
            for (Tree tree : trees) {
                // 나무의 나이가 5의 배수일 경우.
                // 8방향에 나무를 추가로 심어줘야 한다.
                if (tree.age % 5 == 0) {
                    for (int j = 0; j < 8; j++) {
                        int nx = tree.x + dx[j];
                        int ny = tree.y + dy[j];

                        if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                            newTrees.add(new Tree(nx, ny, 1, true));
                        }
                    }
                }
            }

            // 새로운 나무를 앞쪽에 넣어주면, 정렬할 필요가 없어진다.
            // 문제의 조건에서 하나의 칸에 여러 나무가 존재할 수 있고, 이렇게 여러 나무가 존재하는 경우에는
            // 나이가 어린 나무부터 양분을 먹도록 해야 한다.
            // 따라서 가을에 번식을 통해서 생성되는 나무는 모두 1살이기 때문에 나이가 가장 적다. 이를 기존의 trees 의 맨 앞에 넣어주면
            // 정렬이 필요 없다.
            trees.addAll(0, newTrees);

            // 4. 겨울에는 로봇이 돌아다니며 map 배열의 값만큼 양분을 추가해준다.
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    f[j][k] += map[j][k];
                }
            }
        }

        // k번 만큼 반복하게 되면 trees 에는 살아 있는 나무만 들어가게 된다.
        // 따라서 trees 의 size 를 반환하면 된다.
        return trees.size();

    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Tree {
        int x;
        int y;
        int age;
        boolean alive; // 봄에 나무가 나이만큼 양분을 먹지 못하면 죽을 수 있기 때문에 살아 있는지 죽어 있는지 여부를 판별하기 위한 alive 변수.

        public Tree(int x, int y, int age, boolean alive) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.alive = alive;
        }
    }


}
