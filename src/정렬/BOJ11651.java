package 정렬;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * created by victory_woo on 26/03/2019
 * 정렬 : 좌표 정렬하기2.
 * 2차원 배열로 푸는 게 더 빠름.
 * 배열과 ArrayList 중 배열이 조금 더 빠르다.
 */
public class BOJ11651 {
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(SPACE);
            arr[i][0] = Integer.parseInt(input[0]); // x좌표.
            arr[i][1] = Integer.parseInt(input[1]); // y좌표.
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return Integer.compare(o1[0], o2[0]);
                } else {
                    return Integer.compare(o1[1], o2[1]);
                }
            }
        });

     /*   Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });*/

        for (int i = 0; i < n; i++) {
            sb.append(arr[i][0]).append(SPACE).append(arr[i][1]).append(System.lineSeparator());
        }

        System.out.println(sb.toString());

    }
}



