package programmers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class PGM42576_1 {
    // 마라톤 문제 해시를 이용
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String[] participant1 = {"leo", "kiki", "eden"};
        String[] participant2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] participant3 = {"mislav", "stanko", "mislav", "ana"};

        String[] completion1 = {"eden", "kiki"};
        String[] completion2 = {"josipa", "filipa", "marina", "nikola"};
        String[] completion3 = {"stanko", "ana", "mislav"};

        bw.write(solution(participant1, completion1));
        bw.flush();
        bw.close();
    }

    public static String solution(String[] participant, String[] completion) {
        String anwser = "";

        // Map을 구성하는데, 선수의 이름, 등장한 횟
        HashMap<String, Integer> map = new HashMap<>();

        for (String player : participant) {
            if (map.containsKey(player)) {
                map.replace(player, map.get(player) + 1);
            } else {
                map.put(player, 1);
            }
        }

        for (String person : completion) {
            if (map.get(person) > 1) {
                map.replace(person, map.get(person) - 1);
            } else {
                map.remove(person);
            }
        }

        for (String key : map.keySet()) {
            anwser = key;
            System.out.println(map.get(key));
        }

        return anwser;
    }
}
