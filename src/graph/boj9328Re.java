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
 * <p>
 * keys : 알파벳을 인덱스화 시켜서 알파벳에 해당하는 키를 가지고 있는지 여부를 저장하는 boolean 타입의 배열이다.
 * a,b,c ...,z는 1,2,3 ... 으로 표현된다.
 * <p>
 * doorMap : key - 알파벳을 인덱스화 시킨 값, value - 알파벳 키 값으로 열 수 있는 문들(아직 열지 못함)
 * </p>
 */
public class boj9328Re {
    private static int h, w, count;
    private static char[][] a;
    private static boolean[][] visit;
    private static boolean[] keys;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    private static HashMap<Integer, ArrayList<Node>> doorMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");
            h = toInt(in[0]) + 2;
            w = toInt(in[1]) + 2;

            count = 0;
            a = new char[105][105];
            visit = new boolean[105][105];
            keys = new boolean[26];
            doorMap = new HashMap<>();

            // 영역을 확장한다. 테두리에 '.'을 추가한다.
            for (int i = 1; i < h - 1; i++) {
                String s = "." + br.readLine() + ".";
                for (int j = 0; j < w; j++) {
                    a[i][j] = s.charAt(j);
                }
            }

            // 첫 번째 행과 마지막 행에 '.'을 추가한다.
            for (int i = 0; i < w; i++) a[0][i] = a[h - 1][i] = '.';

            // 상근이가 key 가 없을 경우에는 0이다.
            // 따라서 키가 없을 때는 저장하지 않아도 된다.
            String key = br.readLine();
            if (!key.equals("0")) {
                for (int i = 0; i < key.length(); i++) keys[key.charAt(i) - 'a'] = true;
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
            Node now = q.remove();
            int x = now.x, y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                // 범위에 벗어나는지 조건 검사.
                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;

                // 방문한 적이 있거나 벽이라면 건너뛴다.
                if (visit[nx][ny] || a[nx][ny] == '*') continue;

                char type = a[nx][ny];
                visit[nx][ny] = true;

                if (type == '.') {
                    // 빈 공간이라면 큐에 넣는다.
                    q.add(new Node(nx, ny));
                } else if (type == '$') {
                    // 상근이가 찾는 문서라면 큐에 넣고 count 를 증가시킨다.
                    q.add(new Node(nx, ny));
                    count++;
                } else {
                    // 대문자인 경우(문인 경우)
                    if (65 <= type && type <= 90) {

                        // 문에 해당하는 알파벳의 키가 존재하는지 확인하고 키가 있다면 큐에 넣는다.
                        int index = type - 'A';
                        if (keys[index]) {
                            q.add(new Node(nx, ny));
                        } else {
                            // 문을 열 수 있는 키가 없을 경우.
                            ArrayList<Node> doors = doorMap.get(index);
                            if (doors == null) doors = new ArrayList<>();

                            doors.add(new Node(nx, ny));
                            doorMap.put(index, doors);
                            // 문을 열 수 있는 index 를 key 값으로 하여 doors(문의 리스트)를 저장한다.
                            // 하지만, 문을 연 것은 아니고 열지 못한 문들의 리스트를 저장하는 것이다.
                        }
                    } else if (97 <= type && type <= 122) {
                        // 소문자인 경우(키인 경우)
                        // 문에 해당하는 알파벳의 키를 찾았고, 키를 가지고 있음을 true 로 저장함으로써 키의 존재 여부를 표시한다.
                        // 그리고 해당 정점을 큐에 넣는다.
                        int index = type - 'a';
                        keys[index] = true;
                        q.add(new Node(nx, ny));

                        // 알파벳을 인덱스화 시킨 값은 index 가 된다. 그리고 알파벳에 해당하는 문을 열 수 있는 키를 나타낸다.
                        // 이 index 로 열 수 있는 문이 존재하는지 확인한다.
                        // index : 알파벳을 인덱스화 시켰으며, keys[index] == true - index 번째의 알파벳에 대한 키를 가지고 있음을 뜻한다.
                        if (doorMap.containsKey(index)) {
                            ArrayList<Node> doors = doorMap.get(index);
                            q.addAll(doors);
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
