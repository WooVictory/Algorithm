package 정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 26/03/2019
 * 정렬 : 좌표 정렬하기.
 * Comparable, Comparator 차이가 거의 없음.
 */
public class BOJ11650 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Point> list = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            list.add(new Point(x, y));
        }

     /*   list.sort((o1, o2) -> {
            if (o1.getX() > o2.getX()) {
                return 1;
            } else if (o1.getX() == o2.getX()) {
                if (o1.getY() > o2.getY()) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        });*/


        Collections.sort(list);

        for (int i = 0; i < N; i++) {
            bw.write(list.get(i).getX() + " " + list.get(i).getY() + "\n");
        }

        bw.close();

    }
}

class Point implements Comparable<Point> {
    private int x;
    private int y;

    // 생성자.
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getter
    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public int compareTo(Point o) {
        if (this.x > o.x) {
            return 1;
        } else if (this.x == o.x) {
            if (this.y > o.y) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
