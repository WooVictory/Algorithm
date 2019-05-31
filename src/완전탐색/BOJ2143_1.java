package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * created by victory_woo on 31/05/2019
 * 완탐 : 두 배열의 합
 * 다시 풀어보는 문제이다. - 조금은 다른 방식으로
 */
public class BOJ2143_1 {
    private static int[] A, B;
    private static ArrayList<Integer> aList, bList;
    private static int T, N, M;
    private static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = parse(br.readLine());
        N = parse(br.readLine());
        A = new int[N];
        String[] num = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = parse(num[i]);
        }

        M = parse(br.readLine());
        B = new int[M];
        num = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            B[i] = parse(num[i]);
        }

        aList = new ArrayList<>();
        bList = new ArrayList<>();

        // 부분 배열의 합을 구한다.
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += A[j];
                aList.add(sum);
            }
        }

        // 부분 배열의 합을 구한다.
        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = i; j < M; j++) {
                sum += B[j];
                bList.add(sum);
            }
        }

        // Map 을 통해서 aList 에 저장된 부분 배열 합 중에서
        // 등장하는 횟수를 기록한다.
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : aList) {
            if (map.containsKey(x)) {
                // replace 하는 과정.
                int temp = map.get(x);
                map.put(x, temp + 1);
            } else {
                map.put(x, 1);
            }
        }


        // T = x+number 이므로
        // T - number = x 가 된다.
        // T - number 에 해당하는 키 값으로 만족하는 값을 구한다.
        for (int number : bList) {
            if (map.containsKey(T - number)) {
                result += map.get(T - number);
            }
        }

        System.out.println(result);


 /*       Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        map.forEach((key, value) -> {
            System.out.print("key: " + key);
            System.out.println(", value: " + value);

        });


        for (int x : aList) {
            System.out.print(x + " ");
        }
        System.out.println();


        for (int x : bList) {
            System.out.print(x + " ");
        }
        System.out.println();*/
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
