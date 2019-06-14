package bfs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 14/06/2019
 * bfs : 열쇠
 */
public class BOJ9328_review {
    private static int h, w, count;
    private static char[][] map;
    private static boolean[][] visited;
    private static boolean[] keys;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    private static HashMap<Integer, ArrayList<Node>> doors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = parse(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");

            // 상근이가 빌딩 밖을 자유롭게 돌아다닐 수 있기 때문에
            // 외부를 빈 공간으로 채워서 돌아다닐 수 있도록 영역을 확장한다.
            h = parse(in[0]) + 2;
            w = parse(in[1]) + 2;

            // 초기화.
            map = new char[h][w];
            keys = new boolean[26];
            visited = new boolean[h][w];
            doors = new HashMap<>();
            count = 0;

            // 입력을 받는다.
            for (int i = 1; i < h - 1; i++) {
                // 좌우를 확장한다.
                String s = "." + br.readLine() + ".";
                for (int j = 0; j < w; j++) {
                    char ch = s.charAt(j);
                    map[i][j] = ch;
                }
            }

            // 맨 윗 부분과 아랫 부분의 영역을 확장한다.
            for (int j = 0; j < w; j++) {
                map[0][j] = map[h - 1][j] = '.';
            }

            // 상근이가 가지고 있는 key 를 입력받는다.
            String key = br.readLine();
            if (!key.equals("0")) {
                for (int i = 0; i < key.length(); i++) {
                    // key 문자열에서 문자 하나씩 꺼낸다.
                    // 그리고 소문자가 열쇠를 나타내므로 'a'를 빼줌으로써
                    // 인덱스를 통해 keys 배열을 통해 키를 저장하고 관리한다.
                    char ch = key.charAt(i);
                    keys[ch - 'a'] = true;
                }
            }

            bfs(0, 0);

            bw.write(count + "\n");
        }
        bw.flush();
    }

    private static void bfs(int row, int col) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(row, col));
        visited[row][col] = true;

        while (!q.isEmpty()) {
            Node now = q.remove();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                    continue;

                char type = map[nx][ny];

                if (type == '*' || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;

                // 빈 곳을 만나면 그냥 큐에 넣고 끝난다.
                if (map[nx][ny] == '.') {
                    q.add(new Node(nx, ny));
                } else if (map[nx][ny] == '$') {
                    // 문서를 만나면 문서를 찾았으니 count 를 증가시키고 큐에 넣고 끝난다.
                    q.add(new Node(nx, ny));
                    count++;
                } else {
                    // 대문자 : 문을 만났다면.
                    if (65 <= type && type <= 90) {
                        // 문을 열 수 있는 열쇠가 존재하는지 확인한다.
                        int index = type - 'A';
                        if (keys[index]) {
                            // 존재한다면 큐에 넣고 끝난다.
                            // 다음 탐색에서 문서를 찾아야 하니까!
                            q.add(new Node(nx, ny));
                        } else {
                            // 문을 열 수 있는 열쇠가 존재하지 않는다면
                            // 열쇠가 존재하지 않으니까 처음 doors.get(index)는 null 을 반환할 수 밖에 없다.
                            ArrayList<Node> list = doors.get(index);
                            if (list == null) {
                                list = new ArrayList<>();
                            }
                            // 열 수 있는 열쇠가 없어서 열지 못한 문의 좌표를 저장한다.
                            list.add(new Node(nx, ny));
                            // 열 수 있는 열쇠와 그에 해당하는 문의 좌표를 함께 저장한다.
                            doors.put(index, list);
                        }
                    } else if (97 <= type && type <= 122) { // 소문자 : 열쇠를 만났다면.
                        int index = type - 'a';
                        keys[index] = true; // 키를 저장한다.
                        q.add(new Node(nx, ny));

                        // 얻은 열쇠를 사용해서 열지 못한 문들을 확인하고
                        // 열 수 있는 문이 있는지 조회한다.
                        if (doors.containsKey(index)) {
                            // 열 수 있는 문이 존재함으로 열 수 있는 문의 좌표 리스트를 뽑는다.
                            // 그리고 큐에 넣어준다.
                            // 그러면 다음 탐색에서 저장한 열쇠로 문을 열고 탐색을 진행할 것이다.
                            ArrayList<Node> list = doors.get(index);
                            for (Node node : list) {
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
