package language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.Buffer;
import java.util.*;

public class BOJ1076 {
    public static void main(String[] args) throws IOException {
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

        StringBuilder sb= new StringBuilder();

        String one = bf.readLine(), two = bf.readLine(), three = bf.readLine();

        int first = map.get(one)*10+map.get(two);
        System.out.println(first * (long)Math.pow(10, map.get(three).intValue()));


    }
}

class Data {
    private int value;
    private int multiple;

    public Data(int value, int multiple) {
        this.value = value;
        this.multiple = multiple;
    }

    public int getValue() {
        return value;
    }

    public int getMultiple() {
        return multiple;
    }
}
