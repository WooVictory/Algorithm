package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * created by victory_woo on 26/03/2019
 * 정렬 : 나이순 정렬.
 */
public class BOJ10814 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            list.add(new Person(Integer.parseInt(input[0]), input[1], i));
        }

        list.sort((o1, o2) -> {
            if (o1.age == o2.age) {
                return Integer.compare(o1.order, o2.order);
            } else {
                return Integer.compare(o1.age, o2.age);
            }
        });

        for (int i = 0; i < n; i++) {
            sb.append(list.get(i).age).append(" ").append(list.get(i).name).append("\n");
        }

        System.out.println(sb.toString());
    }
}

class Person {
    int age;
    String name;
    int order;

    Person(int age, String name, int order) {
        this.age = age;
        this.name = name;
        this.order = order;
    }

}