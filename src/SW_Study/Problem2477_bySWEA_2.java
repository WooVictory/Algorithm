package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * created by victory_woo on 2020/04/14
 * 차량 정비소.
 * 모의 SWEA 문제.
 * 1번 tc 종료 시간 : 11초.
 * 우선 순위 큐를 쓰지 않고, 일반 큐를 사용해도 된다. 왜냐하면 Comparable 구현을 했기 때문에!
 * 하지만, 우선 순위 큐를 쓰면서 Comparable 구현을 하지 않는다면 우선 순위 큐가 어떤 기준으로 우선 순위를 결정할 지 모르기 때문에 컴파일 오류 발생.
 */
public class Problem2477_bySWEA_2 {
    private static final String SPACE = " ";
    private static int n, m;
    private static Queue<Person> customers, waitingQueue, result;
    private static PriorityQueue<Person> repairQueue;
    private static int[] receptionTime, repairTime;
    private static Person[] receptionList, repairList;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());
        sb = new StringBuilder();

        for (int i = 1; i <= t; i++) {
            String[] in = br.readLine().split(SPACE);
            n = toInt(in[0]);
            m = toInt(in[1]);
            int k = toInt(in[2]);
            int a = toInt(in[3]);
            int b = toInt(in[4]);

            customers = new LinkedList<>();
            waitingQueue = new LinkedList<>();
            repairQueue = new PriorityQueue<>();
            result = new LinkedList<>();

            // 접수, 정비 창구에서 각 창구의 처리 시간을 담을 배열.
            receptionTime = new int[n + 1];
            repairTime = new int[m + 1];

            // 접수, 정비 창구를 사용하고, 각 요청이 처리되기를 기다리는 사람을 관리하는 배열.
            // 접수, 정비 창구를 의미한다고 보면 된다.
            receptionList = new Person[n + 1];
            repairList = new Person[m + 1];

            in = br.readLine().split(SPACE);
            for (int j = 1; j <= n; j++) receptionTime[j] = toInt(in[j - 1]);

            in = br.readLine().split(SPACE);
            for (int j = 1; j <= m; j++) repairTime[j] = toInt(in[j - 1]);

            in = br.readLine().split(SPACE);
            for (int j = 1; j <= k; j++) customers.add(new Person(j, toInt(in[j - 1])));

            solve();
            print(i, a, b);
        }

        System.out.println(sb.toString());
    }

    private static void solve() {
        int time = 0;
        while (true) {
            boolean isFinish = true;

            // 1. 접수 창구.
            for (int i = 1; i <= n; i++) {

                // 접수 창구를 사용하는 사람이 있는지 없는지 확인.
                if (receptionList[i] == null) {
                    // 고객이 있는지 확인.
                    if (customers.isEmpty()) continue;

                    // 고객의 도착 시간이 현재 시간보다 작거나 같으면 접수 가능.
                    if (customers.peek().getArriveTime() <= time) {
                        Person person = customers.remove();
                        person.registerToReception(i);
                        receptionList[i] = person;
                    }
                    isFinish = false;
                } else {
                    // 접수 창구를 사용하고 있으므로 기다리는 시간을 증가시켜 준다.
                    receptionList[i].increaseWaitTime();
                    isFinish = false;
                }

                // 접수가 처리되어 완료된 경우.
                // 접수 리스트에서 빼고, 대기열에 넣어준다. 그리고 해당 창구는 사용 가능하도록 표현한다.
                if (receptionList[i] != null && receptionList[i].isSame(receptionTime[i])) {
                    receptionList[i].setWaitTime(time);
                    waitingQueue.add(receptionList[i]);
                    receptionList[i] = null;
                }
            }

            // 2. 정비 창구.
            for (int i = 1; i <= m; i++) {
                if (repairList[i] == null) {
                    if (repairQueue.isEmpty()) continue;

                    // 정비 창구에 접수한다.
                    Person person = repairQueue.remove();
                    person.registerToRepair(i);
                    repairList[i] = person;
                    isFinish = false;
                } else {
                    // 정비 창구를 사용하고 있으므로 기다리는 시간을 증가시켜 준다.
                    repairList[i].increaseWaitTime();
                    isFinish = false;
                }

                // 정비 창구를 사용 중이면서 정비 창구를 사용하는 사람이 기다린 시간이 정비 창구의 처리 시간과 같아지면 정비가 끝났다.
                // result 에 넣고, 정비 창구를 이용 가능하게 만든다.
                if (repairList[i] != null && repairList[i].isSame(repairTime[i])) {
                    result.add(repairList[i]);
                    repairList[i] = null;
                }
            }

            // 종료 조건 : 고객이 창구를 모두 사용했고, 정비를 기다리는 사람도 없으면 isFinish 값도 true 이므로 종료한다.
            if (customers.isEmpty() && repairQueue.isEmpty() && isFinish) break;

            // 대기열에 있는 사람들을 정비를 기다리는 큐로 넣어준다.
            while (!waitingQueue.isEmpty()) repairQueue.add(waitingQueue.remove());

            // 시간을 증가시켜 준다.
            time++;
        }
    }

    // 결과 출력.
    private static void print(int t, int a, int b) {
        int sum = 0;
        while (!result.isEmpty()) {
            Person person = result.remove();
            if (person.getReceptionNum() == a && person.getRepairNum() == b) sum += person.getIndex();
        }

        if (sum == 0) sum = -1;

        sb.append('#').append(t).append(SPACE).append(sum).append("\n");
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}


/**
 * 사람을 관리하는 클래스.
 * <p>
 * index      : 고객 번호
 * arriveTime : 도착 시간.
 */
class Person implements Comparable<Person>{
    private int index; // 고객의 번호.
    private int arriveTime; // 도착 시간.
    private int receptionNum; // 이용한 접수 창구 번호.
    private int repairNum;
    private int waitTime;

    Person(int index, int arriveTime) {
        this.index = index;
        this.arriveTime = arriveTime;
        this.receptionNum = 0;
        this.repairNum = 0;
        this.waitTime = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    int getArriveTime() {
        return arriveTime;
    }

    int getReceptionNum() {
        return receptionNum;
    }

    int getRepairNum() {
        return repairNum;
    }

    void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    void registerToReception(int num) {
        setWaitTime(1);
        this.receptionNum = num;
    }

    void registerToRepair(int num) {
        setWaitTime(1);
        this.repairNum = num;
    }

    void increaseWaitTime() {
        this.waitTime++;
    }

    boolean isSame(int time) {
        return this.waitTime == time;
    }

    // 두 고객이 동시에 접수를 완료하고 정비 창구로 이동한 경우를 처리하기 위함.
    // 기다린 시간이 동일한 경우에는 접수 번호의 오름 차순으로 정렬!
    // 그렇지 않다면 기다린 시간에 대해 오름 차순으로 정렬.
    @Override
    public int compareTo(Person that) {
        if (this.waitTime != that.waitTime) return Integer.compare(this.waitTime, that.waitTime);
        return Integer.compare(this.receptionNum, that.receptionNum);
    }
}
