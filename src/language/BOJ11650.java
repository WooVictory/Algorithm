package language;

import java.io.*;
import java.util.*;

public class BOJ11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        List<Coordinates> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Coordinates(x, y));
        }


/*      Comparator를 이용한 방법
        list.sort(new Comparator<Coordinates>() {
            @Override
            public int compare(Coordinates o1, Coordinates o2) {
            // o1을 기준으로 잡는다.
                if (o1.x == o2.x) {
                    if (o1.y > o2.y) {
                        return 1;
                    } else {
                        return -1;
                    }
                }else if(o1.x>o2.x){
                    return 1;
                }else if(o1.x<o2.x){
                    return -1;
                }else {
                    return 0;
                }
            }
        });*/

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++)
            bw.write(list.get(i).x + " " + list.get(i).y + "\n");

        bw.flush();
        bw.close();
        bf.close();
    }
}

class Coordinates implements Comparable<Coordinates> {
    int x;
    int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coordinates o) {
        // 오름 차순일 경우
        // this를 기준으로 함
        if (this.x > o.x) {
            return 1;
        } else if (o.x == this.x) {
            if (this.y > o.y) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.x < o.x) {
            return -1;
        } else {
            return 0;
        }
    }
}