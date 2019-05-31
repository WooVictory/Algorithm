package 완전탐색;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 31/05/2019
 * 완탐 : 피자 판매
 * 다시 풀어보기!
 */
public class BOJ2632_2 {
    private static final String SPACE = " ";
    private static int[] mArr, nArr;
    private static int m, n, S;
    private static ArrayList<Integer> mList, nList;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        S = parse(br.readLine()); // 손님이 원하는 피자 크기.
        String[] input = br.readLine().split(SPACE);
        m = parse(input[0]);
        n = parse(input[1]);

        mArr = new int[m];
        nArr = new int[n];

        mList = new ArrayList<>();
        nList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            mArr[i] = parse(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            nArr[i] = parse(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            visited = new boolean[m]; // visited 배열 초기화.
            visited[i] = true; // 첫 번째 조각을 담음.
            go(mArr[i], i, i + 1, mList, mArr);
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n]; // visited 배열 초기화.
            visited[i] = true; // 첫 번째 조각을 담음.
            go(nArr[i], i, i + 1, nList, nArr);
        }

        // 각각 전혀 사용되지 않을 경우도 있음므로 추가해준다.
        mList.add(0);
        nList.add(0);

        // 정렬을 해준다.
        Collections.sort(mList);
        Collections.sort(nList);

        int left = 0, right = nList.size() - 1;
        int result = 0;

        while (left < mList.size() && right >= 0) {
            int left_value = mList.get(left);
            int right_value = nList.get(right);

            if (left_value + right_value == S) { // 합을 찾은 경우.
                int left_count = 0;
                int right_count = 0;

                while (left < mList.size() && mList.get(left) == left_value) {
                    left_count++;
                    left++;
                }

                while (right >= 0 && nList.get(right) == right_value) {
                    right_count++;
                    right--;
                }

                result += (left_count * right_count);

            }

            if (left_value + right_value > S) { // 합보다 큰 경우.
                right--;
            }

            if (left_value + right_value < S) { // 합보다 작은 경우.
                left++;
            }
        }


        bw.write(String.valueOf(result));
        bw.flush();

    }

    /*
    * sum : 현재까지 피자 면적의 합
    * startIndex : 시작 인덱스로(startIndex 부터 피자를 담기 시작한다.)
    * index : 현재 포함할 피자.
    * list : 연속된 피자 크기의 합을 저장하는 리스트.
    * arr : 피자의 크기가 담긴 배열.
    * */
    private static void go(int sum, int startIndex, int index, ArrayList<Integer> list, int[] arr) {
        // 원판 피자이기 때문에 끝까지 담았으면 첫 번째 조각으로 돌아간다.
        // 순환 큐 구현과 비슷함.
        if (index == arr.length) {
            index = 0;
        }

        list.add(sum);

        // 아직 담지 않은 피자 조각에 대해 && 순열의 합이 S 를 넘어서면 계산하지 않음. && 마지막 인덱스 값을 계속 저장하지 않음.
        if (!visited[index] && sum <= S && index != startIndex - 1) {
            visited[index] = true;
            go(sum + arr[index], startIndex, index + 1, list, arr);
        } else {
            return;
        }
    }


    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
