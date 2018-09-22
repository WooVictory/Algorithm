package language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10867 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(bf.readLine());
        Set<Integer> set = new HashSet<>();

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0;i<test_case;i++){
            int value = Integer.parseInt(st.nextToken());
            set.add(value);
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
    }
}
