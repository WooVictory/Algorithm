package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * created by victory_woo on 2020/04/17
 * 낚시왕.
 * 구현.
 */
public class Problem17143 {
    private static int r, c, m;
    private static int[][] map, speed, dir;
    private static ArrayList<Shark> sharks;
    private static int[] dx = {0, -1, 1, 0, 0};
    private static int[] dy = {0, 0, 0, 1, -1};
    // 상, 하, 우, 좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        r = toInt(in[0]);
        c = toInt(in[1]);
        m = toInt(in[2]);

        sharks = new ArrayList<>();

        int x, y, s, d, z;
        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            x = toInt(in[0]);
            y = toInt(in[1]);
            s = toInt(in[2]);
            d = toInt(in[3]);
            z = toInt(in[4]);

            // 문제는 (1,1)부터 시작하지만, 인덱스를 (0,0)부터 하기 때문!
            sharks.add(new Shark(x - 1, y - 1, s, d, z));
        }

        solve();
    }

    private static void solve() {
        int sum = 0;
        int time = 0;
        while (time < c) {
            // 행과 가까운 순으로 정렬을 한다.
            Collections.sort(sharks);

            // 해당 열에서 사람이 땅과 가장 가까운 물고기를 한마리 잡는다.
            Iterator<Shark> iterator = sharks.iterator();
            while (iterator.hasNext()) {
                Shark shark = iterator.next();
                if (shark.y == time) {
                    System.out.println(shark.x + ", " + shark.y);
                    sum += shark.z;
                    iterator.remove();
                    break;
                }
            }

            move();
            survive();
            time++;

        }

        System.out.println(sum);
    }

    // 상어가 움직인다.
    private static void move() {
        for (Shark shark : sharks) {
            int x = shark.x;
            int y = shark.y;
            int d = shark.d; // 방향.
            int s = shark.s; // 속력.
            // 속력이 0이 될때까지 감소하면서 움직인다.

            while (s > 0) {
                // 해당 방향으로 상어가 한칸 이동한다.
                x += dx[d];
                y += dy[d];

                // 범위를 벗어난다는 것은 벽을 만났다는 것이다.
                // 이때는 방향을 바꿔준다.
                if (x < 0 || y < 0 || x >= r || y >= c) {

                    // 벽에 닿았기 때문에 원래 자리로 돌아온 뒤, 방향을 바꾼다.
                    x -= dx[d];
                    y -= dy[d];

                    d = changeDir(d);

                    // 방향을 바꾼 채로 한번 더 이동해야 한다.
                    continue;
                }

                // 속력만큼 이동한다.
                s--;
            }

            // 바뀐 상어의 위치와 방향을 업데이트한다.
            shark.update(x, y, d);
        }

        //print();
    }

    // 벽에 부딪히면 반대 방향으로 방향을 바꾼다.
    private static int changeDir(int d) {
        if (d == 1) {
            return 2;
        } else if (d == 2) {
            return 1;
        } else if (d == 3) {
            return 4;
        } else if (d == 4) {
            return 3;
        }
        return 0;
    }

    // 상어가 이동한 뒤, 살아남은 상어를 골라야 한다.
    // 이동한 뒤에는 한 칸에 여러 상어가 들어있을 수 있기 때문이다.
    private static void survive() {
        // 이동 후의 위치, 크기, 방향을 다시 list 에 담기 위함.
        map = new int[r][c];
        speed = new int[r][c];
        dir = new int[r][c];

        for (Shark shark : sharks) {
            int x = shark.x;
            int y = shark.y;

            // 비어있는 자리에 상어의 정보를 넣는다.
            // 그리고 map 에 이미 상어의 정보가 들어있다면, 기존의 상어보다 큰 상어가 있으면 바꿔준다.
            // 이를 통해 한 칸에 한 마리의 상어만 존재하도록 한다.
            if (map[x][y] <= shark.z) {
                map[x][y] = shark.z;
                speed[x][y] = shark.s;
                dir[x][y] = shark.d;
            }
        }

        // 비워준다.
        sharks.clear();

        // 그리고 살아남은 상어를 다시 담아준다.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != 0) {
                    sharks.add(new Shark(i, j, speed[i][j], dir[i][j], map[i][j]));
                }
            }
        }

        //print();
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    private static void print() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Shark implements Comparable<Shark> {
        int x;
        int y;
        int s; // 속력.
        int d; // 방향.
        int z; // 크기.

        Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        // 이동 후, 상어의 위치와 방향을 업데이트 해준다.
        void update(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public int compareTo(Shark that) {
            // x,y 좌표가 같으면 상어의 크기가 큰 상어를 더 앞쪽에 배치하여, 이후에 작은 상어가 들어오더라도 무시될 수 있게 한다.
            // 그게 아니라 같은 칸에 있지만, x 좌표가 다른 경우에는 x 좌표가 더 작은 걸 앞으로 오도록 한다.
            // 이를 통해서 낚시꾼이 더 빨리 땅과 가까운 상어를 잡을 수 있게 한다.
            if (this.y == that.y) {
                if (this.x == that.x) {
                    return Integer.compare(that.z, this.z); // 크기에 대해서 내림 차순.
                } else {
                    return Integer.compare(this.x, that.x); // x 좌표에 대해서 오름 차순.
                }
            }
            return Integer.compare(this.y, that.y); // y 좌표에 대해서 오름 차순.
        }
    }
}

