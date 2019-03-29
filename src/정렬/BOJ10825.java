package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * created by victory_woo on 26/03/2019
 * 정렬 : 국영수
 */
public class BOJ10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        List<Student> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            String name = input[0];
            int kor = Integer.parseInt(input[1]);
            int eng = Integer.parseInt(input[2]);
            int math = Integer.parseInt(input[3]);

            list.add(new Student(name, kor, eng, math));
        }

        list.sort((o1, o2) -> {
            if (o1.kor == o2.kor) { // 국어 점수가 같은 경우
                if (o1.eng == o2.eng) { // 영어 점수가 같은 경우
                    if (o1.math == o2.math) { // 수학 점수가 같은 경우

                        // 모든 점수가 같은 경우 이름을 기준으로 사전순 정렬.
                        return o1.name.compareTo(o2.name);
                    } else {
                        return Integer.compare(o2.math, o1.math);
                    }
                } else {
                    // 영어 점수가 증가하는 순서
                    return Integer.compare(o1.eng, o2.eng);
                }
            } else if (o1.kor > o2.kor) {
                return Integer.compare(o2.kor, o1.kor); // 감소하는 순서
            }else {
                // 증가하는 순서일 경우!
                return 1;
            }
        });

        for (Student student : list) {
            sb.append(student.name + "\n");
        }

        System.out.println(sb.toString());

    }
}


class Student {
    String name;
    int kor;
    int eng;
    int math;

    public Student(String name, int kor, int eng, int math) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
}