package code_test;

import java.util.Arrays;
import java.util.Collections;

public class reExam2 {
    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 3, 0, 1};
        int[] arr2 = {7, 5, 8, 10, 6, 9, 5};
        int[] arr3 = {3, 0, 3, 0, 3, 0};

        System.out.println(solution(arr2));
    }

    public static int solution(int[] intArr) {

        // 오름차순 정렬
        // 하지만 나한테 필요한건 내림차순 정렬
        Arrays.sort(intArr);
        // 내림차순 정렬
        for (int i = 0; i < intArr.length / 2; i++) {
            int temp = intArr[i];
            intArr[i] = intArr[intArr.length - i - 1];
            intArr[intArr.length - i - 1] = temp;
        }

        int g = 0;
        int max = 0;
        int total = 0;

        for (int i = 0; i < intArr.length; i++) {
            // g가 상위 몇개를 체크하기 위한 변수이니까
            // 하나씩 늘려가면서 체크하도록 해야함
            g = i + 1;
            total += intArr[i];

            if (total >= g * g) {
                max = g;
            }
        }

        return max;
    }
}
