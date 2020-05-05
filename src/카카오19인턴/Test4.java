package 카카오19인턴;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/05/04
 * 카카오 19 인턴 기출.
 * 호텔 방 배정.
 */
public class Test4 {
    public static void main(String[] args) {
        solution2(10, new long[]{1, 3, 4, 1, 3, 1});
    }

    public static long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        boolean[] visit = new boolean[(int) k];
        long[] arr = new long[(int) k];
        for (int i = 0; i < arr.length; i++) arr[i] = (long) (i + 1);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int room = (int) room_number[i];
            if (!visit[room]) {
                visit[room] = true;
                list.add(room);
            } else {
                for (int j = 1; j < len; j++) {
                    int next = room + j;
                    if (!visit[next]) {
                        visit[next] = true;
                        list.add(next);
                        break;
                    }
                }
            }
        }

        long[] answer = new long[list.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = (long) list.get(i);
        System.out.println(list);

        return answer;
    }

    public static long[] solution2(long k, long[] room_number) {
        int len = room_number.length;
        boolean[] visit = new boolean[(int) k];
        long[] arr = new long[(int) k];
        for (int i = 0; i < arr.length; i++) arr[i] = (long) (i + 1);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int room = (int) room_number[i];

            int left = 0, right = arr.length - 1, mid;
            while (left <= right) {
                mid = (left + right) / 2;

                if (room == arr[mid]) {
                    if (!visit[room]) {
                        visit[room] = true;
                        list.add(room);
                        System.out.println(room);
                        break;
                    } else {
                        for (int j = 1; j < len; j++) {
                            int next = room + j;
                            if (!visit[next]) {
                                visit[next] = true;
                                list.add(next);
                                break;
                            }
                        }
                    }
                }

                if (room < arr[mid]) right = mid - 1;
                else left = mid + 1;
            }
        }

        long[] answer = new long[list.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = (long) list.get(i);
        System.out.println(list);

        return answer;
    }
}
