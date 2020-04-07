package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/04/07
 * 치킨 배달.
 * 조합 + 구현(완탐)
 */
public class sw15686Re {
    private static int N, M;
    private static ArrayList<Node> homes, chickens;
    private static int min = Integer.MAX_VALUE;
    private static int[] set;
    private static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        M = toInt(in[1]);

        // 집과 치킨집을 따로 리스트로 저장한다.
        homes = new ArrayList<>();
        chickens = new ArrayList<>();

        int countOfChicken = 0;
        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int value = toInt(in[j]);
                if (value == 1) homes.add(new Node(i, j));

                // 치킨집의 경우에는 치킨집이 몇개인지 알아야 한다.
                // 그래야 그 치킨집의 총 갯수 중 m개를 뽑는 조합을 만들 수 있다.
                if (value == 2) {
                    chickens.add(new Node(i, j));
                    countOfChicken++;
                }
            }
        }

        // 고른 치킨집을 저장하기 위해서 치킨집의 인덱스를 저장한다.
        set = new int[M];
        combination(countOfChicken, M, 0, 0);
        System.out.println(min);
    }

    // 조합. nCr
    // n개의 치킨 집 중에서 r개의 치킨집을 골라서 조합을 만들고, 치킨거리의 최소 거리를 구하고
    // 도시의 치킨 거리의 최소 거리를 구한다. 따라서 최소 거리를 구한다.
    private static void combination(int n, int r, int size, int index) {
        if (r == 0) {
            nodes = new Node[size];
            for (int i = 0; i < size; i++) {
                nodes[i] = chickens.get(set[i]);
            }
            check(nodes);
            return;
        }

        // index == n이면 return 한다.
        if (index == n) return;

        set[size] = index;
        combination(n, r - 1, size + 1, index + 1);
        combination(n, r, size, index + 1);
    }

    // 조합을 이용해서 고른 치킨집들과 집 사이의 거리를 구해 최소값을 찾는다.
    private static void check(Node[] nodes) {
        int sum = 0;
        for (Node home : homes) {
            int distanceMin = Integer.MAX_VALUE;
            for (Node chicken : nodes) {
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
