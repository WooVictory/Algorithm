package code_test;

import java.util.Arrays;

public class exam2 {
    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 3, 0, 1};
        int[] arr2 = {7, 5, 8, 10, 6, 9, 5};
        int[] arr3 = {3, 0, 3, 0, 3, 0};

        //System.out.println(solution(arr2));
solution(arr2
);
    }

    public static int solution(int[] paper) {
        Arrays.sort(paper);
        for (int i = 0; i < paper.length / 2; i++) {
            int temp = paper[i];
            paper[i] = paper[paper.length - i - 1];
            paper[paper.length - i - 1] = temp;
        }

        for (int i=0;i<paper.length;i++){
            System.out.println(paper[i]);
        }

        int max = 0;
        int sum = 0;
        for (int i = 0; i < paper.length; i++) {
            int g = i + 1;
            sum += paper[i];
            if (sum >= g * g) {
                max = g;
            }
        }

        return max;


    }
}
