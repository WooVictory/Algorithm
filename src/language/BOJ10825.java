package language;

import java.io.*;
import java.util.*;

public class BOJ10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            String person = st.nextToken();
            int korean_grade = Integer.parseInt(st.nextToken());
            int english_grade = Integer.parseInt(st.nextToken());
            int math_grade = Integer.parseInt(st.nextToken());
            list.add(new Student(person, korean_grade, english_grade, math_grade));

        }

        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {

                if (o1.korean == o2.korean) {
                    if (o1.english == o2.english) {
                        if (o1.math == o2.math) {
                            // 이름에 대해서는 오름차순 정렬으로!
                            
                            return o1.name.compareTo(o2.name);
                        } else if (o1.math > o2.math) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else if (o1.english > o2.english) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (o1.korean > o2.korean) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        for (int i = 0; i < list.size(); i++)
            bw.write(list.get(i).name + "\n");

        bw.flush();
        bw.close();
        bf.close();

    }
}

class Student {
    String name;
    int korean;
    int english;
    int math;

    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }
}