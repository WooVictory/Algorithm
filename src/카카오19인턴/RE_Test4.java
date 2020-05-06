package 카카오19인턴;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 19 인턴 기출.
 * 다시 푸는 중.
 * 호텔 방 배정.
 */
public class RE_Test4 {
    public static void main(String[] args) {
        solution(10, new long[]{1, 3, 4, 1, 3, 1});
        solution(10, new long[]{1, 3, 1, 1, 1});
    }

    public static long[] solution(long k, long[] room_number) {
        Map<Long, Long> map = new HashMap<>();
        int len = room_number.length;
        long[] answer = new long[len];

        ArrayList<Long> list;
        // 1. room_number 를 돌면서 고객이 배정받고 싶은 방 번호를 하나씩 뽑는다.
        for (int i = 0; i < len; i++) {
            long room = room_number[i];
            // 2. 해당 room 이 고객에게 배정되어 있지 않다면, 해당 방을 배정하고
            // map 에 room 이 다음 방을 가리키도록 넣는다.
            if (!map.containsKey(room)) {
                map.put(room, room + 1);
                answer[i] = room;
            } else {
                // 3. 이미 room 이 고객에게 배정되어 있다면
                // 배정되지 않은 방을 찾을 때까지 room 을 탐색하고 탐색한 방들은 list 에 저장한다.
                // room 을 다음 방으로 업데이트하면서 찾는다.
                list = new ArrayList<>();
                while (map.containsKey(room)) {
                    list.add(room);
                    room = map.get(room);
                }

                // 빈 방을 찾았으면 리스트에 넣고
                // 해당 방을 배정한다.
                list.add(room);
                answer[i] = room;

                // 그리고 지나온 방들은 모두 room+1 방을 가리키도록 업데이트 한다.
                // 이를 통해서 경로를 압축한다. 시간을 단축하는 핵심!
                for (long value : list) map.put(value, room + 1);
            }
        }

        for (long a : answer) System.out.print(a + " ");
        System.out.println();

        return answer;
    }
}
