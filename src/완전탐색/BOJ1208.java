package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 30/05/2019
 * 완탐 : 부분순열의 합2
 */
public class BOJ1208 {
    private static final String SPACE = " ";
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(SPACE);
        int N = parse(input[0]);
        int S = parse(input[1]);

        arr = new int[N];
        String[] num = br.readLine().split(SPACE);
        for (int i = 0; i < N; i++) {
            arr[i] = parse(num[i]);
        }

        // 두 분할로 나누기 위해 리스트 2개를 만든다.
        ArrayList<Integer> first_list = new ArrayList<>();
        ArrayList<Integer> second_list = new ArrayList<>();

        // 반으로 나누어 각각의 부분 집하을 구한다.
        makeHalfList(0, N / 2, 0, first_list);
        makeHalfList(N / 2, arr.length, 0, second_list);

        // 정렬을 해준다.
        Collections.sort(first_list);
        Collections.sort(second_list);

        int left = 0, right = second_list.size() - 1;
        long result = 0;

        while (left < first_list.size() && right >= 0) {
            long left_value = first_list.get(left);
            long right_value = second_list.get(right);

            // 합을 찾은 경우.
            // 각 분할 집합에서 해당 합인 S를 지니는 원소의 개수를 센다.
            if (left_value + right_value == S) {
                long left_count = 0;

                while (left < first_list.size() && first_list.get(left) == left_value) {
                    left_count++;
                    left++;
                }

                long right_count = 0;
                while (right >= 0 && second_list.get(right) == right_value) {
                    right_count++;
                    right--;
                }

                result = result + (left_count * right_count);
            }

            // 해당 합(S)보다 큰 경우, right 를 감소시켜 땡긴다.
            if (left_value + right_value > S) {
                right--;
            }
            // 해당 합(S)보다 작은 경우, left 를 증가시켜 밀어낸다.
            if (left_value + right_value < S) {
                left++;
            }
        }

        // 공집합 예외 처리.
        if (S == 0) {
            result = result - 1;
        }

        System.out.println(result);

    }

    private static void makeHalfList(int index, int end, int sum, ArrayList<Integer> arrayList) {
        // 범위를 벗어나는 경우는 다 찾은 것을 의미한다.
        // 즉, 끝까지 찾은 경우이다.
        if (index >= end) {
            arrayList.add(sum); // 추가한다.
            return;
        }

        // 현재 인덱스를 포함하는 경우.
        makeHalfList(index + 1, end, sum + arr[index], arrayList);
        // 현재 인덱스를 포함하지 않는 경우.
        makeHalfList(index + 1, end, sum, arrayList);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

}
