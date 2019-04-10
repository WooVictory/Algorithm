package 완전탐색;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 10/04/2019
 * 완탐 : 집합
 * 내 풀이 : 중복을 허용하지 않는 집합이기 때문에 Set 자료구조를 이용해서 푼다.
 *
 */
public class BOJ11723 {
    private static final String ADD = "add";
    private static final String REMOVE = "remove";
    private static final String CHECK = "check";
    private static final String TOGGLE = "toggle";
    private static final String ALL = "all";
    private static final String EMPTY = "empty";
    private static final String NEW_LINE = "\n";
    private static HashSet<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());
        set = new HashSet<>();

        while (m-- > 0) {
            //String[] input = br.readLine().split(" ");
            //String command = input[0];
            //int x = Integer.parseInt(input[1]);
            StringTokenizer st=new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x;

            switch (command) {
                case ADD:
                    x = Integer.parseInt(st.nextToken());
                    set.add(x);
                    break;
                case REMOVE:
                    x = Integer.parseInt(st.nextToken());
                    if (set.contains(x)) {
                        set.remove(x);
                    }
                    break;
                case CHECK:
                    x = Integer.parseInt(st.nextToken());
                    if (set.contains(x)) {
                        bw.write(1 + NEW_LINE);
                    } else {
                        bw.write(0 + NEW_LINE);
                    }
                    break;
                case TOGGLE:
                    x = Integer.parseInt(st.nextToken());
                    if(set.contains(x)){
                        set.remove(x);
                    }else {
                        set.add(x);
                    }
                    break;
                case ALL:
                    all();
                    break;
                case EMPTY:
                    empty();
                    break;
            }
        }

        bw.flush();
    }
    private static void all(){
        empty();

        for (int i=1;i<=20;i++){
            set.add(i);
        }
    }

    private static void empty(){
        set.removeAll(set);
    }
}



/*
*
1
1
0
1
0
1
0
1
0
1
1
0
0
0
1
0
* */