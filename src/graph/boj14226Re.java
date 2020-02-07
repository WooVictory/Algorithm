package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/07
 * 이모티콘.
 * visit[화면에 있는 이모티콘][클립보드에 있는 이모티콘]
 */
public class boj14226Re {
    private static final int MAX = 10000;
    private static boolean[][] visit = new boolean[MAX + 1][MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = toInt(br.readLine());

        bfs(s);
    }

    private static void bfs(int start) {
        LinkedList<Emoticon> q = new LinkedList<>();
        q.add(new Emoticon(1, 0, 0));

        while (!q.isEmpty()) {
            Emoticon emoticon = q.remove();

            if (emoticon.screen == start) {
                System.out.println(emoticon.cost);
                break;
            }

            // 1. 화면에서 클립보드로 이모티콘을 복사.
            int clipboardFromScreen = emoticon.screen;
            if (!visit[clipboardFromScreen][clipboardFromScreen]) {
                q.add(new Emoticon(clipboardFromScreen, clipboardFromScreen, emoticon.cost + 1));
                visit[clipboardFromScreen][clipboardFromScreen] = true;
            }

            // 2. 클립보드에 있는 이모티콘을 화면에 복사. 클립 보드 + 화면.
            int clipboardToScreen = emoticon.clipboard + emoticon.screen;
            if ( clipboardToScreen < MAX && !visit[clipboardToScreen][emoticon.clipboard]) {
                q.add(new Emoticon(clipboardToScreen, emoticon.clipboard, emoticon.cost + 1));
                visit[clipboardToScreen][emoticon.clipboard] = true;
            }

            // 3. 화면에서 이모티콘 한 개를 삭제.
            int deleteFromScreen = emoticon.screen - 1;
            if (0 <= deleteFromScreen && !visit[deleteFromScreen][emoticon.clipboard]) {
                q.add(new Emoticon(deleteFromScreen, emoticon.clipboard, emoticon.cost + 1));
                visit[deleteFromScreen][emoticon.clipboard] = true;
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Emoticon {
        int screen; // 스크린에 있는 이모티콘의 개수.
        int clipboard; // 클립보드에 저장된 이모티콘의 개수.
        int cost; // 이모티콘을 만들기 위해 걸리는 시간.

        Emoticon(int screen, int clipboard, int cost) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.cost = cost;
        }
    }
}
