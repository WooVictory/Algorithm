package bfs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 14/06/2019
 * bfs : 열쇠
 * 탈옥 문제와 비슷함.
 */
public class BOJ9328 {
    private static int h, w, count = 0;
    private static boolean[] keys;
    private static char[][] map = new char[105][105];
    private static boolean[][] visited;
    private static HashMap<Integer, ArrayList<Node>> doors;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int t = parse(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");
            h = parse(in[0]) + 2;
            w = parse(in[1]) + 2;

            visited = new boolean[105][105];
            keys = new boolean[26];
            doors = new HashMap<>();
            count = 0;

            for (int i = 1; i < h - 1; i++) {
                String s = "." + br.readLine() + ".";
                for (int j = 0; j < w; j++) {
                    char ch = s.charAt(j);
                    map[i][j] = ch;
                }
            }

            for (int j = 0; j < w; j++) {
                map[0][j] = map[h - 1][j] = '.';
            }

            // key 값을 저장한다.
            String key = br.readLine(); // 소문자 열쇠
            if (!key.equals("0")) {
                for (int i = 0; i < key.length(); i++) {
                    char ch = key.charAt(i);
                    keys[ch - 'a'] = true;
                }
            }

            bfs();
            //System.out.println(count);
            bw.write(count+"\n");

        }
        bw.flush();
    }

    // 중복되는 조건이 많아서 겹치는 조건을 빼서 한번에 처리해야 한다.
    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.remove();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위에 벗어나면 continue 분기.
                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                    continue;

                char type = map[nx][ny];

                // 벽이거나 방문했으면 탐색할 필요가 없음.
                if (type == '*' || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;

                // 비어있는 곳이라면.
                if (type == '.') {
                    q.add(new Node(nx, ny));
                } else if (type == '$') {
                    q.add(new Node(nx, ny));
                    count++;
                } else {
                    // 대문자라면. 즉 문이라는 의미.
                    if (65 <= type && type <= 90) {
                        int index = type - 'A';
                        // 대문자 문에 대한 소문자 키가 존재하면.
                        // 문을 연다. 큐에 넣는다.
                        if (keys[index]) {
                            q.add(new Node(nx, ny));
                        } else { // 없으면.
                            // 해당 문을 저장해놔야 한다.
                            // 그래서 해당 대문자 문에 대한 소문자 키의 index 를 키로 하여
                            // 좌표를 doors map 에 저장한다.
                            ArrayList<Node> list = doors.get(index);
                            if (list == null) {
                                list = new ArrayList<>();
                            }
                            list.add(new Node(nx, ny));
                            doors.put(index, list);
                        }
                    } else if (97 <= type && type <= 122) {
                        // 소문자라면. 즉 키라면.
                        int index = type - 'a';
                        keys[index] = true; // 찾은 키를 배열에 저장한다.
                        q.add(new Node(nx, ny));
                        // 그리고 만약, doors 에 해당 index 에 해당하는 소문자 키로
                        // 열 수 있는 문이 존재하는지 확인한다.
                        if (doors.containsKey(index)) {
                            // 문을 열 수 있는 키가 존재하므로
                            // 문을 열고 그 문의 위치를 큐에 넣어준다.
                            // 이렇게 함으로써 다음에 방문할 때 문이 있는 위치에 대해서 키가 있으면
                            // 열 수 있도록 한다.
                            ArrayList<Node> list = doors.get(index);
                            for (Node node: list){
                                q.add(node);
                            }
                        }
                    }
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
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
