package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/10
 * 열쇠.
 * bfs.
 * 다시 풀어보기!
 */
public class boj9328 {
    private static int h, w, count;
    private static char[][] a;
    private static boolean[][] visit;
    private static boolean[] keys;
    private static HashMap<Integer, ArrayList<Node>> doors;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");
            h = toInt(in[0]) + 2;
            w = toInt(in[1]) + 2;

            doors = new HashMap<>(); // 알파벳으로 열 수 있는 문을 저장함.(하지만, 아직 열지는 못함.)
            a = new char[105][105];
            visit = new boolean[105][105];
            keys = new boolean[26]; // 알파벳의 저장 유무를 확인하기 위한 배열.
            count = 0;

            for (int i = 1; i < h - 1; i++) {
                String num = "." + br.readLine() + "."; // 양 옆 영역을 확장하기 위함.
                for (int j = 0; j < w; j++) a[i][j] = num.charAt(j);
            }

            // 나머지 영역을 확장한다.
            for (int i = 0; i < w; i++) a[0][i] = a[h - 1][i] = '.';

            String key = br.readLine();
            if (!key.equals("0")) {
                for (int i = 0; i < key.length(); i++) {
                    keys[key.charAt(i) - 'a'] = true;
                }
            }

            bfs();
            System.out.println(count);

        }
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visit[0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.remove();
            int x = node.x, y = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;

                if (visit[nx][ny] || a[nx][ny] == '*') continue;

                char type = a[nx][ny];
                visit[nx][ny] = true;

                if (type == '.') {
                    q.add(new Node(nx, ny));
                } else if (type == '$') {
                    q.add(new Node(nx, ny));
                    count++;
                } else {
                    // 대문자일 경우. (문)
                    if (65 <= type && type <= 90) {
                        int index = type - 'A';
                        // 대문자인 문을 열 수 있는 키가 존재하는지 확인.
                        // 존재하면 큐에 넣는다.
                        if (keys[index]) {
                            q.add(new Node(nx, ny));
                        } else {
                            // 존재하지 않을 경우, 해당 문을 저장해야 한다.
                            ArrayList<Node> door = doors.get(index);
                            if (door == null) {
                                door = new ArrayList<>();
                            }
                            door.add(new Node(nx, ny)); // 문의 좌표를 저장한 list 인 door
                            doors.put(index, door); // 알파벳에 해당하는 index 로 door 즉, 문의 좌표를 갖고 있는 리스트를 저장한다.
                        }
                    } else if (97 <= type && type <= 122) {
                        // 소문자일 경우. (키)
                        int index = type - 'a';
                        keys[index] = true;
                        q.add(new Node(nx, ny));

                        // doors 에 저장된 문 중에서 지금 찾은 key 로 열 수 있는 문이 존재하는지 확인한다.
                        if (doors.containsKey(index)) {
                            ArrayList<Node> list = doors.get(index);
                            for (Node item : list) q.add(item);
                        }
                    }
                }
            }
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
