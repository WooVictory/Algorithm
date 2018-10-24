package language;

import java.io.*;
import java.util.*;

public class BOJ11651 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        List<Point> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Point(x, y));
        }

        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.y == o2.y){
                    if(o1.x>o2.x){
                        return 1;
                    }else if(o1.x<o2.x){
                        return -1;
                    }else {
                        return 0;
                    }
                }else if(o1.y>o2.y){
                    return 1;
                }else if(o1.y<o2.y){
                    return -1;
                }else {
                    return 0;
                }
            }
        });


        for(int i=0;i<list.size();i++)
            bw.write(list.get(i).x+" "+list.get(i).y+"\n");

        bw.flush();
        bw.close();
        bf.close();

    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}