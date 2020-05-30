package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/30
 */
public class PGM17686_REVIEW {
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        //String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        solution(files);
    }

    public static String[] solution(String[] files) {

        Arrays.sort(files, (o1, o2) -> {

            String[] file1 = split(o1);
            String[] file2 = split(o2);

            int value = file1[0].compareTo(file2[0]);
            if (value == 0) {
                int a = toInt(file1[1]);
                int b = toInt(file2[1]);
                return a - b;
            } else {
                return value;
            }
        });

        for (String file : files) System.out.println(file);
        return files;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    // head, number, tail 분리.
    private static String[] split(String file) {
        StringBuilder head, number, tail;
        int index = 0;

        head = new StringBuilder();
        // head 찾기.
        // 숫자가 나오기 전까지 문제를 붙여서 head 를 구성한다.
        for (int i = 0; i < file.length(); i++) {
            char ch = file.charAt(i);
            if ('0' <= ch && ch <= '9') {
                index = i;
                break;
            }
            head.append(ch);
        }

        // number 찾기.
        // 숫자이면서 숫자의 길이는 최대 5자리이기 때문에 5자리 보다 작아야 한다.
        // 숫자가 아니라면 탈출.
        number = new StringBuilder();
        for (int i = index; i < file.length(); i++) {
            char ch = file.charAt(i);
            if (Character.isDigit(ch) && number.length() < 5) number.append(ch);
            else {
                index = i;
                break;
            }
        }

        tail = new StringBuilder();
        tail.append(file.substring(index));
        return new String[]{head.toString().toLowerCase(), number.toString(), tail.toString()};
    }
}
