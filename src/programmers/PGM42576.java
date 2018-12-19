package programmers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PGM42576 {
    // 마라톤
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] participant1 = {"leo", "kiki", "eden"};
        String[] participant2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] participant3 = {"mislav", "stanko", "mislav", "ana"};

        String[] completion1 = {"eden", "kiki"};
        String[] completion2 = {"josipa", "filipa", "marina", "nikola"};
        String[] completion3 = {"stanko", "ana", "mislav"};

        bw.write(solution(participant1,completion1));
        bw.flush();
        bw.close();


    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        String tmp = "";

        // 정렬
        Arrays.sort(participant);
        Arrays.sort(completion);

        for(int i=0;i<completion.length;i++){

            // 정렬된 두 배열이 같은지 다른지 판단
            // 다르다면, 다른 참가자는 완주하지 못했다는 의미로 tmp 변수에 담음
            if(!completion[i].equals(participant[i])){
                tmp = participant[i];
                break;
            }else
                continue;

        }


        // tmp가 빈 문자열이 아니라는 것은
        // tmp에 완주하지 못한 참가자가 담겼다는 의미
        // tmp가 빈 문자열이라는 것은
        // 정렬된 배열을 비교했을 때, 완주한 참가자 수 만큼의 참여자들은 완주했음
        // 정렬된 배열이기 때문에 같음
        // 그래서 마지막 참가자가 완주하지 못함을 인지
        if(!tmp.equals(""))
            answer = tmp;
        else
            answer = participant[participant.length-1];


        return answer;
    }
}
