package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/04/07
 * 치킨 배달.
 * 조합 + 완탐으로 생각됨..
 */
public class sw15686 {
    private static int n, m;
    private static int[][] map;
    private static ArrayList<Node> houses;
    private static ArrayList<Node> chickens;
    private static int[] set;
    private static int count, min = Integer.MAX_VALUE;
    private static Node[] combi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        houses = new ArrayList<>();
        chickens = new ArrayList<>();

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
                if (map[i][j] == 1) houses.add(new Node(i, j));

                if (map[i][j] == 2) {
                    chickens.add(new Node(i, j));
                    count++;
                }
            }
        }

        set = new int[m]; // m개만 제외하고 치킨 집을 폐쇄할 것이기 때문!
        combination(count, m, 0, 0);
        System.out.println(min);
    }

    private static void combination(int count, int r, int size, int index) {
        if (r == 0) {
            combi = new Node[size];
            for (int j = 0; j < size; j++) {
                combi[j] = chickens.get(set[j]);
            }
            check(combi);
            return;
        }

        if (index == count) return;

        set[size] = index;
        combination(count, r - 1, size + 1, index + 1);
        combination(count, r, size, index + 1);
    }

    private static void check(Node[] combi) {
        int sum = 0;
        for (Node house : houses) {
            int disMin = Integer.MAX_VALUE;

            for (Node chicken : combi) {
                int distance = getDistance(house, chicken);
                if (distance < disMin) disMin = distance;
            }

            sum += disMin;
        }

        if (sum < min) min = sum;
    }

    private static int getDistance(Node house, Node chicken) {
        return Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
    }

    private static void printMap() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) System.out.print(map[i][j] + " ");

            System.out.println();
        }
    }


    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
