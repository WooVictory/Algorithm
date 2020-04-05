package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/04
 * 일곱 난쟁이.
 * 완탐.
 * flag 가 있어야 된다.
 * 가능한 정답이 여러 개인 경우에는 하나만 출력해야 된다. 처음에 이 조건을 그냥 return 으로만 고려했는데,
 * 92%까지 가다가 실패한다. 뭔가, return 처리를 해도 더 출력하는 반례가 존재하는 것 같다.
 * 따라서 flag 값을 두어 정답을 찾으면 flag=true 바꾸고 true 이면 다른 경우일지라도 무조건 return 한다.
 */
public class boj2309 {
    private static int[] a = new int[9];
    private static boolean[] visit = new boolean[9];
    private static LinkedList<Integer> result = new LinkedList<>();
    private static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            a[i] = toInt(br.readLine());
        }

        Arrays.sort(a);
        solve(0, 0, 0);
    }

    private static void solve(int index, int count, int total) {
        if (flag) return;

        if (count == 7) {
            if (total == 100) {
                print();
                flag = true;
            }
            return;
        }

        for (int i = index; i < 9; i++) {
            if (!visit[i]) {
                visit[i] = true;
                result.add(a[i]);
                solve(i + 1, count + 1, total + a[i]);

                visit[i] = false;
                result.removeLast();
            }
        }
    }

    private static void print() {
        for (int num : result) System.out.println(num);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
