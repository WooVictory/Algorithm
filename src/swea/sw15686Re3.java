package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/04/08
 * 치킨 배달.
 * 다시 푸는 중!
 * 풀이.
 * 1. 조합으로 m개의 치킨집만을 구한다.
 * 2. m개의 치킨집에 대해서 집과의 치킨 거리 중 최소를 구한다.
 * 3. 이 최소 거리로 이루어진 도시의 치킨 거리를 구한다.
 * 4. 도시의 치킨 거리 중 최소 값을 구한다.
 */
public class sw15686Re3 {
    private static int N, M, count, min = Integer.MAX_VALUE;
    private static ArrayList<Node> homes, chickens;
    private static int[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        M = toInt(in[1]);

        homes = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int value = toInt(in[j]);
                if (value == 1) homes.add(new Node(i, j));

                if (value == 2) {
                    chickens.add(new Node(i, j));
                    count++;
                }
            }
        }

        set = new int[M];
        combination(count, M, 0, 0);
        System.out.println(min);
    }

    // 조합.
    private static void combination(int n, int r, int size, int index) {
        // r개를 뽑아서 조합을 다 만든 경우.
        if (r == 0) {
            Node[] combinations = new Node[size];
            for (int i = 0; i < size; i++) {
                combinations[i] = chickens.get(set[i]);
            }
            check(combinations);
            return;
        }

        // index 가 n 에 도달했을 때는 return 해줘야 한다.
        // 이 경우는 두 번째 테스트 케이스 기준으로 5가 된다.
        // 5개 중에서 2개를 뽑아야 한다. 5개를 리스트에 넣으면 인덱스는 0,1,2,3,4가 된다.
        // 허나, index 가 n 즉, 5가 되면 IndexOutOfBounds 에러가 발생한다.
        // 따라서 index == n이 되면 return 한다.
        if (index == n) return;

        // 조합으로 고른 index 를 set 배열에 넣어줘야 한다.
        set[size] = index;
        combination(n, r - 1, size + 1, index + 1); // 뽑는 경우.
        combination(n, r, size, index + 1); // 뽑지 않는 경우.
    }

    // 조합으로 만들어진 m개의 치킨집과 집과의 치킨 거리를 계산한다.
    // 이 중에서 최소 치킨 거리를 sum 값에 더한다.
    // sum 값은 도시의 치킨 거리 중 최소 값을 담고 있다.
    // 이를 min 값과 비교하여 도시의 치킨 거리 중 최소값을 담도록 한다.
    private static void check(Node[] combinations) {
        int sum = 0;
        for (Node home : homes) {
            int distanceMin = Integer.MAX_VALUE;

            for (Node chicken : combinations) {
                int distance = getDistance(home, chicken);
                if (distance < distanceMin) distanceMin = distance;
            }
            sum += distanceMin;
        }

        if (sum < min) min = sum;
    }

    private static int getDistance(Node home, Node chicken) {
        return Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
