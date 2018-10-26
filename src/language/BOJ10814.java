package language;

import java.io.*;
import java.util.*;

public class BOJ10814 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        List<Member> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Member(age, name, (i+1)));
        }

        Collections.sort(list, new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                if(o1.age>o2.age){
                    return 1;
                }else if(o1.age == o2.age){
                    if(o1.order>o2.order){
                        return 1;
                    }else {
                        return -1;
                    }
                }else {
                    return -1;
                }
            }
        });

        for(int i=0;i<list.size();i++)
            bw.write(list.get(i).age+" "+list.get(i).name+"\n");

        bw.flush();
        bw.close();
        bf.close();

    }
}
class Member{
    int age;
    String name;
    int order;

    public Member(int age, String name, int order){
        this.age = age;
        this.name = name;
        this.order = order;
    }
}