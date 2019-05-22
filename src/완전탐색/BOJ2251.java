package 완전탐색;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 21/05/2019
 * 완탐 : 물통
 * 음,, 물통은 좀 어려웠다...;
 */
public class BOJ2251 {
    private static boolean[][] visited = new boolean[201][201];
    private static boolean[] answer = new boolean[201];

    // 아래의 from, to 배열은 물을 담을 수 있는 6가지 경우의 수를 배열로 관리하기 위함이다.
    private static int[] from = {0, 0, 1, 1, 2, 2};
    private static int[] to = {1, 2, 2, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int[] capacityOfWater = new int[3]; // 각 용기의 부피를 담는 배열.
        for (int i = 0; i < 3; i++) {
            capacityOfWater[i] = parse(input[i]);
        }

        bfs(capacityOfWater);
        for (int i = 0; i <= capacityOfWater[2]; i++) {
            if (answer[i]) {
                bw.write(i + " ");
            }
        }
        bw.flush();
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    private static void bfs(int[] capacityOfWater) {
        LinkedList<Water> q = new LinkedList<>();
        q.add(new Water(0, 0));
        visited[0][0] = true;
        answer[capacityOfWater[2]] = true;

        while (!q.isEmpty()) {
            Water water = q.remove();
            // 물의 용량을 관리하는 변수.
            int a = water.a;
            int b = water.b;
            int c = capacityOfWater[2] - a - b;

            // 물을 담는 6가지 경우를 배열과 for 문을 이용해서 편하게 관리한다.
            for (int k = 0; k < 6; k++) {
                int[] next = {a, b, c}; // 물의 용량을 관리.
                next[to[k]] = next[to[k]] + next[from[k]]; // from -> to 로 물을 담는다.
                next[from[k]] = 0; // 물을 옮겼으니 비워준다.

                // 물의 양이 용량보다 많다면.
                if (next[to[k]] >= capacityOfWater[to[k]]) {
                    // 다시 from 쪽으로 넘치는 물을 돌려준다.
                    next[from[k]] = next[to[k]] - capacityOfWater[to[k]];
                    next[to[k]] = capacityOfWater[to[k]];
                }

                if (!visited[next[0]][next[1]]) { // 방문한 적이 없다면.
                    visited[next[0]][next[1]] = true;
                    q.add(new Water(next[0], next[1]));

                    // A 물통이 비어있는지 확인하고 비어있다면 C에 들어있는 물의 용량을 나중에 확인하기 위해서
                    // true 체크하는 과정.
                    if (next[0] == 0) {
                        answer[next[2]] = true;
                    }
                }
            }
        }
    }
}

class Water {
    int a;
    int b;

    public Water(int a, int b) {
        this.a = a;
        this.b = b;
    }
}