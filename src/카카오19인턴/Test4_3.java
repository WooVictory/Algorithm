package 카카오19인턴;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * created by victory_woo on 2020/05/05
 * 카카오 19 인턴 기출.
 * 호텔 방 배정.
 */
public class Test4_3 {
    public static void main(String[] args) {
        solution(10, new long[]{1, 3, 4, 1, 3, 1});
        //solution(10, new long[]{1, 3, 1, 1});
    }

    private static HashMap<Long, Long> map = new HashMap<>();

    public static long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        long[] answer = new long[len];

        ArrayList<Long> list;
        for (int i = 0; i < len; i++) {
            long room = room_number[i];

            // 방이 비어있는 경우.
            // 해당 방을 고객에게 배정하고, map 에는 해당 방(room)이 자식이면서 Key
            // 다음 방 (room+1)이 부모가 되면서 Value 가 된다.
            if (!map.containsKey(room)) {
                answer[i] = room;
                map.put(room, room + 1);
            } else {
                // 방이 비어있지 않은 경우.
                list = new ArrayList<>();
                // 빈 방을 찾을 때까지 계속 반복한다.
                // 그러면서 빈 방을 찾기 위해서 들렀던 방(비어있지 않은 방)을 리스트에 저장한다.
                // 그리고 room 은 다음 방을 계속 가져오면서 업데이트 한다.
                while (map.containsKey(room)) {
                    list.add(room);
                    room = map.get(room);
                }

                // 배정해야 할 방을 저장한다.
                answer[i] = room;
                // 배정해야 할 방을 마지막으로 리스트에 저장한다.
                list.add(room);

                // 리스트에는 경로를 압축해야 할 방들이 들어있다.
                // (room+1)은 배정된 방의 다음 방인데, 빈 방을 찾기 위해 들렀던 방들의 다음 방을
                // 모두 배정된 방의 다음 방을 부모로 하도록 업데이트 한다.
                // 이를 통해서 다음 번에 이미 배정된 방을 방문하는 고객은 앞에서부터 차례로 탐색하는 것이 아닌
                // 연결된 부모를 따라서 빈 방을 찾도록 한다. 이를 통해서 경로를 압축하여 시간을 줄일 수 있다.
                for (long value : list) map.put(value, room + 1);
            }
        }

        for (long a : answer) System.out.print(a + " ");
        System.out.println();
        return answer;
    }
}
