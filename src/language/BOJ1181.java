package language;

import java.io.*;
import java.util.*;

public class BOJ1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int N = Integer.parseInt(bf.readLine());
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String input = bf.readLine();
            set.add(input);

        }

        List<String> list = new ArrayList<>(set);

        // 일단 사전순으로 먼저 SelectSort
        Collections.sort(list);

        // 길이순으로 SelectSort
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length())
                    return 1;
                else if (o1.length() < o2.length())
                    return -1;
                else
                    return 0;
            }
        });


        // 출력
        for (int i = 0; i < list.size(); i++)
            bw.write(list.get(i) + "\n");

        bw.flush();
        bw.close();
        bf.close();


    }

}
