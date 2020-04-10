package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/08
 * 나무 재테크.
 * 구현 혹은 시뮬레이션 문제.
 * 봄,여름,가을,겨울에 대한 조건은 문제를 통해 확인하면 된다.
 * 삽입, 삭제가 이루어지기 때문에 ArrayList 보다는 LinkedList 자료 구조를 사용하는 게 낫다.
 * 실제로 ArrayList 를 사용하면 시간 초과가 난다.
 */
public class sw16235Re {
    private static int N, M, K;
    private static int[][] map;
    private static int[][] fuel;
    private static LinkedList<Tree> trees; // 나무 정보를 관리하기 위한 list.
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    // 상하좌우, 대각선 방향까지 총 8방향.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        M = toInt(in[1]);
        K = toInt(in[2]);

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

        // 나무에 대한 정보.
        int x, y, z;
        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            x = toInt(in[0]);
            y = toInt(in[1]);
            z = toInt(in[2]);

            // (1,1)부터 이기 때문에 -1을 해줘야 함. -> 1. 내가 빠뜨린 부분.
            trees.add(new Tree(x - 1, y - 1, z, true));
        }

        System.out.println(solve());
    }

    // K년이 지난 후 살아남은 나무의 수를 반환한다.
    private static int solve() {
        for (int i = 0; i < K; i++) {
            // 1. 봄.
            for (Tree tree : trees) {
                if (tree.age <= fuel[tree.x][tree.y]) {
                    fuel[tree.x][tree.y] -= tree.age;
                    tree.growAge();
                } else {
                    tree.die();
                }
            }

            // 2. 여름. -> 나무 제거를 위해 이터레이터를 사용한다.
            Iterator<Tree> iterator = trees.iterator();
            while (iterator.hasNext()) {
                Tree tree = iterator.next();
                if (!tree.alive) {
                    fuel[tree.x][tree.y] += tree.age / 2;
                    iterator.remove();
                }
            }

            // 3. 가을.
            // 번식을 하기 위한 조건 중, 나무의 나이가 5의 배수여야 한다. -> 2. 내가 빠뜨린 부분.
            LinkedList<Tree> newTrees = new LinkedList<>();
            for (Tree tree : trees) {
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

            // 새롭게 심어진 나무들의 나이가 어리기 때문에 나이가 어린 나무를 먼저 처리하는 조건을 처리하기 위해서
            // trees 의 더 앞으로 넣어준다.
            trees.addAll(0, newTrees);

            // 4. 겨울.
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    fuel[j][k] += map[j][k];
                }
            }
        }

        return trees.size();
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

        void growAge() {
            this.age++;
        }

        void die() {
            alive = false;
        }
    }
}
