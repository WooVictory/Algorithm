package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 29/03/2019
 * 정렬 : 버블 정렬
 * 버블 정렬을 이용해서 문제를 풀면 시간 초과가 뜬다.
 * 다른 접근 방식으로 해야 한다.
 * 버블 정렬의 과정을 이용해서 풀면 된다.
 * 다시 한 번 풀어보기!!!
 */
public class BOJ1377 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Pair> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Pair(i, Integer.parseInt(br.readLine())));
        }

        // 값을 기준으로 오름차순 정렬을 수행한다.
        list.sort((o1, o2) -> {
            if (o1.value > o2.value) {
                return 1;
            } else if (o1.value == o2.value) {
                return 0;
            } else {
                return -1;
            }
        });

        int answer = 0;

        /*
         * 버블 정렬이 한 단계씩 수행할 때마다 정렬되어야 하는 수는 1칸씩 이동한다.
         * 즉, 1회 실핼할수록 1칸씩 앞으로 땡겨지는 것이다. -> 정렬된 배열을 통해서 어느 값이 제일 많이 이동했는지
         * 찾아내면 그 값이 총 버블 정렬의 실행 횟수가 된다.
         * 이를 구하기 위해서 정렬 전과 정렬 후의 데이터들의 인덱스 차이를 비교한다.
         * 비교하여 인덱스 차이가 가장 큰 값 + 1이 답이 된다.
         * */
        for (int i = 0; i < n; i++) {
            //System.out.println("index : " + list.get(i).index + ", " + "value : " + list.get(i).value + ", " + "i : " + i);
            int diff = list.get(i).index - i;
            if (answer < diff) {
                answer = diff;
            }
        }

        System.out.println(answer + 1);
    }

    static class Pair {
        int index;
        int value;

        Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }


}
