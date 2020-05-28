package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/05/27
 * 치킨 배달.
 * 조합 + 완탐.
 */
public class SW15686_REVIEW {
    private static ArrayList<Node> chickens, houses;
    private static int[] set;
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int m = toInt(in[1]);

        // 1. 치킨집과 집을 각각의 리스트에 저장한다.
        int[][] map = new int[n + 1][n + 1];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = toInt(in[j - 1]);

                if (map[i][j] == 1) houses.add(new Node(i, j));
                if (map[i][j] == 2) chickens.add(new Node(i, j));
            }
        }

        // 2. 조합 개념을 사용하여 치킨집 중에서 가능한 조합의 m개를 뽑는다.
        set = new int[m];
        combination(chickens.size(), m, 0, 0);
        System.out.println(result);
    }

    // 치킨집 중에서 가능한 조합의 m개를 뽑는다.
    private static void combination(int N, int R, int index, int target) {
        if (R == 0) {
            check();
            return;
        }

        if (N == target) return;

        set[index] = target;
        combination(N, R - 1, index + 1, target + 1);
        combination(N, R, index, target + 1);
    }

    private static void check() {
        int sum = 0;
        // 집을 기준으로 하나씩 뽑아서 치킨집과의 거리인 치킨 거리를 구한다.
        // 치킨 거리 중 최소값을 구하여 도시의 치킨 거리를 구한다.
        for (Node house : houses) {
            int min = Integer.MAX_VALUE;
            for (int index : set) {
                Node chicken = chickens.get(index);
                int distance = Math.abs(chicken.x - house.x) + Math.abs(chicken.y - house.y);
                if (distance < min) min = distance; // 가장 가까운 치킨 거리를 찾는다.
            }

            // 치킨 거리를 모두 합해 도시의 치킨 거리를 구한다.
            sum += min;
        }

        // 도시의 치킨 거리 중 최소값을 찾는다.
        if (sum < result) result = sum;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
