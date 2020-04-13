package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;

/**
 * created by victory_woo on 2020/04/13
 */
public class Problem2477_bySWEA {
    private static int[] reception;
    private static int[] repair;
    private static int[] customer;
    private static Person[] receptionsList, repairList;
    private static Queue<Person> 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");
            int n, m, k, a, b;
            n = toInt(in[0]);
            m = toInt(in[1]);
            k = toInt(in[2]); // 사용한 고객의 수.
            a = toInt(in[3]); // 지갑을 두고 간 고객이 사용한 접수 창구 번호.
            b = toInt(in[4]); // 지갑을 두고 간 고객이 사용한 정비 창구 번호.

            reception = new int[n + 1]; // 접수 창구.
            receptionsList = new Person[n + 1];

            repair = new int[m + 1]; // 정비 창구.
            repairList = new Person[m + 1];
            customer = new int[k + 1];

            String[] receptions = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) reception[i] = toInt(receptions[i - 1]);

            String[] repairs = br.readLine().split(" ");
            for (int i = 1; i <= m; i++) repair[i] = toInt(repairs[i - 1]);

            String[] customers = br.readLine().split(" ");
            for (int i = 1; i <= k; i++) customer[i] = toInt(customers[i - 1]);

        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Person {
        int index;
        int arriveTime;
        int waitTime;
        int receptionNumber;
        int repairNumber;

        public Person(int index, int arriveTime, int waitTime, int receptionNumber, int repairNumber) {
            this.index = index;
            this.arriveTime = arriveTime;
            this.waitTime = waitTime;
            this.receptionNumber = receptionNumber;
            this.repairNumber = repairNumber;
        }
    }
}
