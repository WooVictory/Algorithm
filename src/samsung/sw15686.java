package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/05/27
 * 치킨 배달.
 */
public class sw15686 {
    private static int n, m;
    private static int[][] map;
    private static ArrayList<Node> list, houses;
    private static int[] set;
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n + 1][n + 1];
        list = new ArrayList<>();
        houses = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = toInt(in[j - 1]);
                if (map[i][j] == 2) list.add(new Node(i, j, map[i][j]));

                if (map[i][j] == 1) houses.add(new Node(i, j, map[i][j]));
            }
        }


        int size = list.size();
        int r = list.size() - m;
        set = new int[m];
        combination(size, m, 0, 0);
        System.out.println(result);

    }

    private static void combination(int N, int R, int index, int target) {
        if (R == 0) {
            check();
            /*for (int a : set) System.out.print(a + " ");
            System.out.println();*/
            return;
        }

        if (N == target) return;

        set[index] = target;
        combination(N, R - 1, index + 1, target + 1);
        combination(N, R, index, target + 1);
    }

    private static void check() {
        int total = 0;

        for (Node house : houses) {
            // 이게 for 반복문 밖에 있어서 제대로 된 값이 안나왔음... ㅜㅜ
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < set.length; i++) {
                Node chicken = list.get(set[i]);
                int distance = Math.abs(chicken.x - house.x) + Math.abs(chicken.y - house.y);

                min = Math.min(min, distance);
            }

            total += min;
        }

        result = Math.min(result, total);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;
        int num;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
