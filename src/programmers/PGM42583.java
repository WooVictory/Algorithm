package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/29
 * 다리를 지나는 트럭.
 * 스택/큐를 사용하는 문제.
 * <p>
 * 기다리는 트럭들과 다리에 올라가서 지나가고 있는 트럭들을 구분하기 위해서
 * 2개의 큐를 사용한다.
 */
public class PGM42583 {
    public static void main(String[] args) {
        //System.out.println(solution(2, 10, new int[]{7, 4, 5, 6}));
        //System.out.println(solution(100, 100, new int[]{10}));
        System.out.println(solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
    }

    // bridge_length : 다리의 길이, weight : 다리가 견딜 수 있는 무게.
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        LinkedList<Truck> waiting = new LinkedList<>(); // 기다리는 트럭들.
        LinkedList<Truck> bridges = new LinkedList<>(); // 다리에 올라가 있는 트럭들.

        // 기다리는 큐에 넣어준다.
        for (int truck : truck_weights) {
            waiting.add(new Truck(truck, 0));
        }

        int totalWeight = 0;
        int time = 0;
        while (!waiting.isEmpty() || !bridges.isEmpty()) {
            // 매 반복마다 시간을 증가시킨다.
            time++;

            // 다리에 트럭이 올라와 있을 때.
            // 흐른 시간과 트럭의 진입 시간의 차이가 다리의 길이보다 크거나 같게되는 경우
            // 트럭은 다리를 다 지났기 때문에 큐에서 빼주고, total 값도 갱신한다.
            // 이유는 트럭이 1초에 1씩 움직이기 때문이다.
            if (!bridges.isEmpty()) {
                if (time - bridges.peek().start_time >= bridge_length) {
                    Truck truck = bridges.remove();
                    totalWeight -= truck.weight;
                }
            }

            // 기다리는 트럭의 큐에서 트럭 하나를 빼서 다리가 견딜 수 있는 무게 조건에 만족한다면
            // 큐에서 트럭을 빼고 totalWeight 에 추가한뒤, 다리에 올라가 있는 트럭을 관리하는 큐에 넣는다.
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

    // 무게와 트럭이 다리를 지날 때 진입 시간을 관리한다.
    static class Truck {
        int weight;
        int start_time;

        Truck(int weight, int start_time) {
            this.weight = weight;
            this.start_time = start_time;
        }
    }
}
