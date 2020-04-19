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
public class Problem17143Re {
    private static int r, c, m;
    private static int[][] speed, dir, size;
    private static ArrayList<Fish> list;
    private static int[] dx = {0, -1, 1, 0, 0};
    private static int[] dy = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        r = toInt(in[0]);
        c = toInt(in[1]);
        m = toInt(in[2]);

        list = new ArrayList<>();
        int x, y, s, d, z;
        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            x = toInt(in[0]);
            y = toInt(in[1]);
            s = toInt(in[2]);
            d = toInt(in[3]);
            z = toInt(in[4]);

            // 상어의 정보를 리스트를 통해 관리한다. 상어의 정보가 순서가 없고 무작위로 입력된다.
            list.add(new Fish(x - 1, y - 1, s, d, z));
        }
        solve();
    }

    private static void solve() {
        int sum = 0;
        for (int i = 0; i < c; i++) {
            // 정렬을 통해서 낚시왕이 이동하는 거리에 y 좌표 기준으로 순서대로 해당 열에 접근할 수 있도록 한다.
            Collections.sort(list);

            // 1. 낚시왕이 해당 열에 있는 상어를 잡는다.
            Iterator<Fish> iterator = list.iterator();
            while (iterator.hasNext()) {
                Fish fish = iterator.next();
                if (fish.y == i) {
                    //System.out.println(fish.x + ", " + fish.y);
                    sum += fish.z;
                    iterator.remove();
                    break;
                }
            }

            moveFish();
            survive();
        }

        System.out.println(sum);
    }

    // 상어들이 각자의 속력과 방향을 가지고 이동하게 된다.
    private static void moveFish() {
        for (Fish fish : list) {
            int x = fish.x;
            int y = fish.y;
            int s = fish.s;
            int d = fish.d;

            // s는 속력이고, 상어가 몇 칸을 이동해야 하는지 나타낸다.
            while (s > 0) {
                // 가지고 있는 방향만큼 이동한다.
                x += dx[d];
                y += dy[d];

                // 범위를 벗어났다는 것은 벽에 부딪혔다는 말이므로 방향을 바꿔 다시 이동해야 한다.
                if (x < 0 || y < 0 || x >= r || y >= c) {
                    // 벽에 부딪혔으므로 원래 자리로 돌아온다.
                    x -= dx[d];
                    y -= dy[d];

                    // 반대 방향으로 바꾼다. 바꾼 뒤, 이동해야하는 칸만큼 다시 이동한다.
                    d = changeDirection(d);
                    continue;
                }
                s--;
            }
            // 이동하고 난 뒤, 상어의 위치와 방향 정보를 업데이트 한다.
            fish.update(x, y, d);
        }
    }

    // 이동한 뒤에 한 칸에 여러 마리의 상어가 존재할 수 있기 때문에
    // 한 칸에 한 마리의 상어만 있을 수 있도록 조정한다.
    private static void survive() {
        speed = new int[r][c];
        dir = new int[r][c];
        size = new int[r][c];

        for (Fish fish : list) {
            int x = fish.x;
            int y = fish.y;

            // 해당 칸에 fish 의 크기보다 작거나 같은 상어만 들어간다.
            // 따라서 fish 보다 작은 상어는 들어갈 수 없으므로 이는 상어가 작은 물고기를 모두 잡아먹는다는 것으로 해석할 수 있다.
            if (size[x][y] <= fish.z) {
                speed[x][y] = fish.s;
                dir[x][y] = fish.d;
                size[x][y] = fish.z;
            }
        }

        // 리스트를 비워준다.
        list.clear();

        // 살아남은 상어들을 다시 리스트에 집어넣는다.
        // 위의 과정을 통해 한 칸에 한 마리의 상어만 존재한다.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (size[i][j] != 0) list.add(new Fish(i, j, speed[i][j], dir[i][j], size[i][j]));
            }
        }

        /*for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(size[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/
    }

    // 현재 방향을 기준으로 반대 방향으로 바꾼다.
    private static int changeDirection(int d) {
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

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    // 상어의 정보를 저장할 클래스를 만든다.
    static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int s;
        int d;
        int z;

        Fish(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        void update(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        // 정렬 이유 : 무작위로 입력되기 때문에 이 순서를 낚시왕이 이동하는 순서에 맞추면서 한 칸에 한 마리의 상어만 처리하기 위함도 있다.
        // 상어가 동일한 칸에 존재한다면, 크기가 더 큰 상어를 앞에 오도록 내림차순 정렬한다.
        // 이를 통해 큰 상어가 먼저 해당 칸을 차지하고 작은 상어는 그 칸에 들어오지 못하도록 구현한다.
        // 열만 같고 행이 다르다면 더 위에 있는 행 즉, 행 값을 내림차순 정렬하여 행 값이 작은 값을 먼저 오도록 정렬한다.
        // 아예 다른 칸에 존재한다면, 리스트는 시작 열을 기준으로 정렬하여, 낚시왕이 제대로 된 접근을 할 수 있게 한다.
        // 여기서 실수해서 20분 해맴... 실수하지 말자..!
        @Override
        public int compareTo(Fish that) {
            if (this.y == that.y) {
                if (this.x == that.x) {
                    return Integer.compare(that.z, this.z);
                } else {
                    return Integer.compare(this.x, that.x);
                }
            }
            return Integer.compare(this.y, that.y);
        }
    }
}
