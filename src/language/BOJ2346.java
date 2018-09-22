package language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(bf.readLine()); // 테스트 케이스

        ArrayList<Ballons> ballons = new ArrayList<>();


        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0;i<test_case;i++){
            int value = Integer.parseInt(st.nextToken());
            ballons.add(new Ballons(i+1, value));
        }



       /* for(int i=0;i<test_case;i++){
            System.out.println(ballons.get(i).orderNumber+", "+ballons.get(i).valueNumber);
        }*/

        String str = process(ballons, test_case);
        System.out.println(str);


    }

    public static String process(ArrayList<Ballons> ballon_list, int num){

        int kill=0; // 터뜨릴 풍선의 인덱스
        int value=0; // 터뜨릴 풍선의 값
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<num;i++){
            // value 즉, 풍선에 적혀있는 값이 양수인지 음수인지에 따라
            // 오른쪽 혹은 왼쪽으로 이동하기 위해 검사
            if(value>0){
                for(int k=0;k<value-1;k++){
                    ++kill;
                    if(kill>=ballon_list.size()){
                        kill=0;
                    }
                }
            }else if(value<0){
                value = Math.abs(value); // 절대값 변환
                for(int j=0;j<value;j++){
                    --kill;
                    if (kill<0){
                        kill = ballon_list.size()-1;
                    }
                }

            }


            /*FIXME
            * 처음에는 0번째 즉, 첫 번째 풍선을 터뜨려야 하기 때문에
            * 이렇게 터뜨릴 풍선을 정하고
            * 그 풍선의 값(value)[즉, 적혀있는 값!]를 알아낸다.
            * 왜냐하면, 다음 풍선을 터뜨리기 위해 얼만큼 이동할지 알기 위해서
            * */
            Ballons ballon = ballon_list.get(kill);
            System.out.println("삭제가 될 풍선 : "+ballon.valueNumber+", "+(ballon.orderNumber)+", "+kill);
            value = ballon.valueNumber;

            sb.append(ballon.orderNumber+" ");
            ballon_list.remove(kill);
            if(kill == ballon_list.size()){
                System.out.println("몇번?");
                kill = 0;
            }


        }


        return sb.toString();
    }


}
class Ballons{
    int orderNumber; // 풍선의 순서
    int valueNumber; // 풍선 안에 적힌 값

    public Ballons(int orderNumber, int valueNumber){
        this.orderNumber = orderNumber;
        this.valueNumber = valueNumber;
    }
}