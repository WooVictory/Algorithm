package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * created by victory_woo on 2020/04/13
 */
public class Problem2477_bySWEA {
    private static int n, m;
    private static int[] receptionTime, repairTime;
    private static Person[] receptionsList, repairList;
    private static Queue<Person> customers, tempWait, endPerson;
    private static PriorityQueue<Person> repairWait;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());
        sb = new StringBuilder();

        for (int i = 1; i <= t; i++) {

            customers = new LinkedList<>();
            tempWait = new LinkedList<>();
            endPerson = new LinkedList<>();
            repairWait = new PriorityQueue<>();

            String[] in = br.readLine().split(" ");
            int k, a, b;
            n = toInt(in[0]);
            m = toInt(in[1]);
            k = toInt(in[2]); // 사용한 고객의 수.
            a = toInt(in[3]); // 지갑을 두고 간 고객이 사용한 접수 창구 번호.
            b = toInt(in[4]); // 지갑을 두고 간 고객이 사용한 정비 창구 번호.

            receptionTime = new int[n + 1]; // 접수 창구마다 걸리는 시간.
            receptionsList = new Person[n + 1]; // 접수한 사람의 리스트를 관리.

            repairTime = new int[m + 1]; // 정비 창구마다 걸리는 시간.
            repairList = new Person[m + 1]; // 정비를 접수한 사람의 리스트를 관리.

            String[] receptions = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) receptionTime[j] = toInt(receptions[j - 1]);

            String[] repairs = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) repairTime[j] = toInt(repairs[j - 1]);

            String[] customer = br.readLine().split(" ");
            for (int j = 1; j <= k; j++) {
                customers.add(new Person(j, toInt(customer[j - 1])));
            }

            solve();
            printResult(i, a, b);
        }

        System.out.println(sb.toString());
    }


    private static void reception() {

    }

    private static void solve() {
        int time = 0;
        while (true) {
            // 종료 조건은 접수, 정비 창구를 담는 큐가 비고, 대기 큐까지 비면 계속 true 로 유지된다.
            boolean isFinish = true;

            reception();
            // 접수 창구.
            for (int i = 1; i <= n; i++) {
                if (receptionsList[i] == null) {
                    if (customers.isEmpty()) continue;

                    if (customers.peek().arriveTime <= time) {
                        Person person = customers.remove();
                        person.receive(i);
                        receptionsList[i] = person;
                    }
                } else {
                    receptionsList[i].increaseWaitTime();
                }
                isFinish = false;

                // 시간이 다 된 경우.
                if (receptionsList[i] != null && receptionsList[i].isSame(receptionTime[i])) {
                    receptionsList[i].waitTime = time;
                    tempWait.add(receptionsList[i]);
                    receptionsList[i] = null;
                }
            }

            // 정비 창구.
            for (int i = 1; i <= m; i++) {
                if (repairList[i] == null) {
                    if (repairWait.isEmpty()) continue;

                    Person person = repairWait.remove();
                    person.receive(i);
                    repairList[i] = person;
                } else {
                    repairList[i].increaseWaitTime();
                }
                isFinish = false;

                if (repairList[i] != null && repairList[i].isSame(repairTime[i])) {
                    endPerson.add(repairList[i]);
                    repairList[i] = null;
                }
            }

            if (customers.isEmpty() && repairWait.isEmpty() && isFinish) break;

            while (!tempWait.isEmpty()) repairWait.add(tempWait.remove());

            time++;
        }

    }

    private static void printResult(int t, int A, int B) {
        int sum = 0;
        while (!endPerson.isEmpty()) {
            Person person = endPerson.remove();
            if (person.receptionNumber == A && person.repairNumber == B) sum += person.index;
        }

        if (sum == 0) sum = -1;
        sb.append("#").append(t).append(" ").append(sum).append("\n");
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Person implements Comparable<Person> {
        int index;
        int arriveTime;
        int waitTime;
        int receptionNumber;
        int repairNumber;
        boolean isAvailable;

        Person(int index, int arriveTime) {
            this.index = index;
            this.arriveTime = arriveTime;
            this.waitTime = 0;
            this.receptionNumber = 0;
            this.repairNumber = 0;
            this.isAvailable = false;
        }

        @Override
        public int compareTo(Person that) {
            if (this.waitTime != that.waitTime) {
                return Integer.compare(this.waitTime, that.waitTime);
            }
            return Integer.compare(this.receptionNumber, that.receptionNumber);
        }

        void receive(int number) {
            this.waitTime = 1;
            this.receptionNumber = number;
        }

        boolean isUsed() {
            return isAvailable;
        }

        void increaseWaitTime() {
            this.waitTime++;
        }

        boolean isSame(int time) {
            return this.waitTime == time;
        }
    }
}
