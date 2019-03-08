package code_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class replay_exam1 {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add("d");
        list.add("a");
        list.add("y");
        list.add("q");
        list.add("w");

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        for(int i=0;i< list.size();i++)
            System.out.println(list.get(i));
    }
}
