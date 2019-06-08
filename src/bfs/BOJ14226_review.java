package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 08/06/2019
 * bfs : 이모티콘 리뷰.
 */
public class BOJ14226_review {
    private static final int MAX = 10000;
    private static int S;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = parse(br.readLine());

        visited = new boolean[MAX + 1][MAX + 1];
        bfs();
    }

    private static void bfs() {
        LinkedList<Emoticon> q = new LinkedList<>();
        // 현재 화면에 이모티콘 1개가 보이고 클립보드에 저장된 것이 없는 상태에서 시작.
        q.add(new Emoticon(1, 0, 0));

        while (!q.isEmpty()) {
            Emoticon emoticon = q.remove();

            if (emoticon.value == S) {
                System.out.println(emoticon.count);
                break;
            }

            /*
             * 1. copy
             * 화면에 있는 이모티콘을 모두 클립보드에 복사한다.
             * */
            int copyValue = emoticon.value;
            if (!visited[copyValue][copyValue]) {
                q.add(new Emoticon(copyValue, copyValue, emoticon.count + 1));
                visited[copyValue][copyValue] = true;
            }

            /*
             * 2. paste
             * 클립보드에 있는 모든 이모티콘을 화면에 붙여넣는다.
             * */
            int addedValue = emoticon.value + emoticon.clipboard;
            if (!visited[addedValue][emoticon.clipboard] && addedValue < MAX) {
                q.add(new Emoticon(addedValue, emoticon.clipboard, emoticon.count + 1));
                visited[addedValue][emoticon.clipboard] = true;
            }

            /*
             * 3. delete
             * 화면에 있는 이모티콘 중 한개를 삭제한다.
             * */
            int deletedValue = emoticon.value - 1;
            if (0 <= deletedValue && !visited[deletedValue][emoticon.clipboard]) {
                q.add(new Emoticon(deletedValue, emoticon.clipboard, emoticon.count + 1));
                visited[deletedValue][emoticon.clipboard] = true;
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Emoticon {
        int value; // 화면에 있는 이모티콘 개수를 표현하는 변수.
        int clipboard; // 클립보드에 저장된 이모티콘의 개수를 표현하는 변수.
        int count; // 이모티콘을 화면에 만드는데 걸리는 시간을 표현하는 변수.

        Emoticon(int value, int clipboard, int count) {
            this.value = value;
            this.clipboard = clipboard;
            this.count = count;
        }
    }
}
