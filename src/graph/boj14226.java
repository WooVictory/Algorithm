package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/29
 * discovered[화면에 보이는 이모티콘의 수][클립보드에 저장된 이모티콘의 수]
 */
public class boj14226 {
    private static final int MAX = 10000;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = toInt(br.readLine());

        visit = new boolean[MAX + 1][MAX + 1];
        bfs(s);
    }

    private static void bfs(int s) {
        LinkedList<Emoticon> q = new LinkedList<>();
        q.add(new Emoticon(1, 0, 0)); // 화면에 1개를 만들었기 때문에 초기 값.

        while (!q.isEmpty()) {
            Emoticon now = q.remove();

            // 영선이가 화면에 그려야 하는 s 개와 화면에 보이는 이모티콘의 개수가 같으면 답을 구한 것이다.
            if (s == now.screen) {
                System.out.println(now.cost);
                break;
            }

            // 1. 화면 -> 클립보드 이모티콘 복사해서 저장.
            int copiedFromScreen = now.screen;
            if (!visit[copiedFromScreen][copiedFromScreen]) {
                q.add(new Emoticon(copiedFromScreen, copiedFromScreen, now.cost + 1));
            }

            // 2. 클립보드 -> 화면 이모티콘 복사 붙여넣기.
            // 기존에 화면에 보이는 이모티콘에 클립 보드에 있는 이모티콘을 추가하는 것이기 때문에 두 값을 더해준다.
            int copiedFromClipboard = now.screen + now.clipboard;
            if (!visit[copiedFromClipboard][now.clipboard] && copiedFromClipboard < MAX) {
                q.add(new Emoticon(copiedFromClipboard, now.clipboard, now.cost + 1));
                visit[copiedFromClipboard][now.clipboard] = true;
            }

            int deletedFromScreen = now.screen - 1;
            if (!visit[deletedFromScreen][now.clipboard] && 0 < deletedFromScreen) {
                q.add(new Emoticon(deletedFromScreen, now.clipboard, now.cost + 1));
                visit[deletedFromScreen][now.clipboard] = true;
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Emoticon {
        int screen; // 화면에 보이는 이모티콘의 갯수.
        int clipboard; // 클립보드에 저장된 이모티콘의 갯수.
        int cost; // 각 연산을 수행하는 데 걸리는 시간.

        Emoticon(int screen, int clipboard, int cost) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.cost = cost;
        }
    }
}