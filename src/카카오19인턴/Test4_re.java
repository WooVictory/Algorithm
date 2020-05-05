package 카카오19인턴;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * created by victory_woo on 2020/05/05
 * 카카오 19 인턴 기출.
 * 호텔 방 배정.
 */
public class Test4_re {
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
            if (!map.containsKey(room)) {
                answer[i] = room;
                map.put(room, room + 1);
            } else {
                list = new ArrayList<>();
                while (map.containsKey(room)) {
                    list.add(room);
                    room = map.get(room);
                }
                answer[i] = room;
                list.add(room);
                for (long value : list) map.put(value, room + 1);
            }
        }

        for (long a : answer) System.out.print(a + " ");
        System.out.println();
        return answer;
    }
}
