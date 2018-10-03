package language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ1076 {
    public static void main(String[] args) throws IOException {
        // 저항 문제는 Map 자료구조를 사용해서 풀 수 있다.
        // 문제에서 입력은 세 개이므로 for문을 돌려서 받지 않고 그냥 한번에 받는 것이 더 간단.

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();

        map.put("black", 0);
        map.put("brown", 1);
        map.put("red", 2);
        map.put("orange", 3);
        map.put("yellow", 4);
        map.put("green", 5);
        map.put("blue", 6);
        map.put("violet", 7);
        map.put("grey", 8);
        map.put("white", 9);

        String one = bf.readLine(), two = bf.readLine(), three = bf.readLine();


        int firstResult = map.get(one) * 10 + map.get(two);
        long secondResult = (long) Math.pow(10, map.get(three));
        System.out.println(firstResult * secondResult);


    }
}


