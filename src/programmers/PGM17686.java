package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * created by victory_woo on 2020/05/29
 */
public class PGM17686 {
    public static void main(String[] args) {
        //String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        solution(files);
    }

    public static String[] solution(String[] files) {
        String[] answer;
        ArrayList<File> list = new ArrayList<>();

        for (String file : files) {
            int index = 0;

            // head 부분을 찾는다.
            for (int i = 0; i < file.length(); i++) {
                if (Character.isDigit(file.charAt(i))) {
                    index = i;
                    break;
                }
            }
            String head = file.substring(0, index).toLowerCase();

            // number 부분을 찾는다.
            int numberIndex = 0;
            for (int i = index; i < file.length(); i++) {
                if (!Character.isDigit(file.charAt(i))) {
                    numberIndex = i;
                    break;
                }
            }

            String number = file.substring(index, numberIndex);
            String tail = file.substring(numberIndex);
            list.add(new File(head, toInt(number), tail, file));

        }

        list.sort(numberCmp);
        list.sort(fileCmp);
        for (File file : list) System.out.println(file);

        answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = list.get(i).original;

        return answer;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    private static Comparator<File> fileCmp = new Comparator<File>() {
        @Override
        public int compare(File a, File b) {
            return a.head.compareTo(b.head);
        }
    };

    private static Comparator<File> numberCmp = new Comparator<File>() {
        @Override
        public int compare(File a, File b) {
            return a.number - b.number;
        }
    };

    static class File implements Comparable<File> {
        String head;
        int number;
        String tail;
        String original;

        File(String head, int number, String tail, String original) {
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.original = original;
        }

        @Override
        public String toString() {
            return "File{" +
                    "head='" + head + '\'' +
                    ", number=" + number +
                    ", tail='" + tail + '\'' +
                    ", original='" + original + '\'' +
                    '}';
        }

        @Override
        public int compareTo(File that) {
            return this.head.compareTo(that.head);
        }
    }
}
