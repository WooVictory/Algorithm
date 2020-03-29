package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/29
 * 다리를 지나는 트럭.
 * 스택, 큐를 사용하는 문제.
 */
public class PGM42583Re {
    public static void main(String[] args) {
        System.out.println(solution(2, 10, new int[]{7, 4, 5, 6}));
    }

    /*
    * waiting : 기다리는 트럭을 관리하는 큐.
    * bridges : 다리에 올라간 트럭을 관리하는 큐.
    * */
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        LinkedList<Truck> waiting = new LinkedList<>();
        LinkedList<Truck> bridges = new LinkedList<>();

        for (int truck : truck_weights) waiting.add(new Truck(truck, 0));

        int time = 0;
        int totalWeight = 0;
        while (!waiting.isEmpty() || !bridges.isEmpty()) {
            // 매 반복마다 time 값을 증가시킨다. 시간이 흐름을 의미한다.
            time++;

            // 다리에 올라간 트럭의 큐에서 제일 앞에 있는 트럭의 진입 시간과 time 의 차이가 bridge_length 보다 크거나 같으면
            // 큐에서 빼고 total 값을 갱신한다.
            // 트럭은 1초마다 1씩 움직이기 때문에 time - bridges.peek().start_time 는 트럭의 길이를 지났는지 확인하는 문장이다.
            // 트럭이 다리를 다 지났으면 bridges 에서 빼주는 게 맞다.
            if (!bridges.isEmpty()) {
                if (time - bridges.peek().start_time >= bridge_length) {
                    Truck truck = bridges.remove();
                    totalWeight -= truck.weight;
                }
            }

            // 기다리는 큐에서 제일 앞 줄에 있는 트럭의 무게와 total 의 합이 weight 보다 작거나 같으면 다리에 올라갈 수 있다.
            // waiting 큐에서 뺀 뒤, total 값을 갱신시키고 bridges 큐에 해당 트럭을 추가한다.
            if (!waiting.isEmpty()) {
                if (totalWeight + waiting.peek().weight <= weight) {
                    Truck truck = waiting.remove();
                    totalWeight += truck.weight;
                    bridges.add(new Truck(truck.weight, time));
                }
            }
        }
        return time;
    }

    static class Truck {
        int weight;
        int start_time;

        Truck(int weight, int start_time) {
            this.weight = weight;
            this.start_time = start_time;
        }
    }
}
