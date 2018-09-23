package language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine()); // 상근이가 가지고 있는 카드의 갯수
        Set<Integer> set = new HashSet<>(); // 상근이가 가지고 있는 카드를 담을 Set 자료구조
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0;i<N;i++){
            set.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine(), " ");

        /*FIXME
        * 여기서는 어차피 기존에 상근이가 가지고 있는 카드와
        * 입력받은 카드를 비교해서 상근이가 가지고 있는지 가지고 있지 않은지
        * 판단하므로 굳이 Set을 통해서 값을 저장하지 않아도 됨.
        * 입력 받은 그대로 비교하면 된다.
        * */
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            if(set.contains(Integer.parseInt(st2.nextToken()))){
                sb.append(1+" ");
            }else {
                sb.append(0+" ");
            }
        }
        System.out.println(sb.toString());




    }
}
