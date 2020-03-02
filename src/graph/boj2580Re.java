package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/02/28
 * 스도쿠
 * dfs, 백트래킹
 * 다시 풀어보기!
 */
public class boj2580Re {
    private static int n = 9;
    private static int[][] map;
    private static ArrayList<Node> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) list.add(new Node(i, j));
            }
        }
        dfs(0);
    }

    private static void dfs(int index) {
        if (index == list.size()) {
            print();
            System.exit(0);
        } else {
            for (int i = 1; i <= 9; i++) {
                int x = list.get(index).x;
                int y = list.get(index).y;

                if (isSafe(i, index)) {
                    map[x][y] = i;
                    dfs(index + 1);
                }

                if (i == 9) map[x][y] = 0;
            }
        }
    }

    private static boolean isSafe(int value, int index) {
        return checkRow(value, index) && checkCol(value, index) && checkBox(value, index);
    }

    private static boolean checkRow(int value, int index) {
        int x = list.get(index).x;
        int y = list.get(index).y;

        for (int i = 0; i < n; i++) {
            if (y == i) continue;

            if (map[x][i] == value) return false;
        }
        return true;
    }

    private static boolean checkCol(int value, int index) {
        int x = list.get(index).x;
        int y = list.get(index).y;

        for (int i = 0; i < n; i++) {
            if (x == i) continue;

            if (map[i][y] == value) return false;
        }
        return true;
    }

    private static boolean checkBox(int value, int index) {
        int a = list.get(index).x / 3;
        int b = list.get(index).y / 3;
        a = a * 3;
        b = b * 3;

        for (int i = a; i < a + 3; i++) {
            for (int j = b; j < b + 3; j++) {
                if (i == a && b == j) continue;

                if (map[i][j] == value) return false;
            }
        }
        return true;
    }

    private static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
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