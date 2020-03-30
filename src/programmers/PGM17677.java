package programmers;

import java.util.ArrayList;
import java.util.List;

/**
 * created by victory_woo on 2020/03/30
 * 뉴스 클러스터링 - 2018 카카오 블라인드 채용 코딩 테스트 4번.
 */
public class PGM17677 {
    public static final int VALUE = 65536;

    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }

    public static int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        ArrayList<String> firstList = new ArrayList<>();
        ArrayList<String> secondList = new ArrayList<>();
        split(str1, firstList);
        split(str2, secondList);

        ArrayList<String> unionList = getUnionList(firstList, secondList);
        ArrayList<String> intersectionList = getIntersectionList(firstList, secondList);

        if (unionList.size() == 0) return VALUE;
        else return (int) ((double) intersectionList.size() / (double) unionList.size() * VALUE);
    }

    /*
     * 중복 허용 다중 집합의 합집합 구하기.
     * list1, list2 가 바뀌기 때문에 원본을 복사해서 사용해야 한다.
     * */
    public static ArrayList<String> getUnionList(ArrayList<String> list1, ArrayList<String> list2) {
        list1 = (ArrayList<String>) list1.clone();
        list2 = (ArrayList<String>) list2.clone();

        ArrayList<String> unionList = new ArrayList<>();
        for (String s : list1) {
            if (list2.contains(s)) {
                list2.remove(s);
            }
            unionList.add(s);
        }
        unionList.addAll(list2);
        return unionList;
    }

    /*
     * 중복 허용 다중 집합의 교집합 구하기.
     * list1, list2 가 바뀌기 때문에 원본을 복사해서 사용해야 한다.
     * */
    public static ArrayList<String> getIntersectionList(ArrayList<String> list1, ArrayList<String> list2) {
        list1 = (ArrayList<String>) list1.clone();
        list2 = (ArrayList<String>) list2.clone();

        ArrayList<String> intersectionList = new ArrayList<>();
        for (String s : list1) {
            if (list2.contains(s)) {
                intersectionList.add(s);
                list2.remove(s);
            }
        }
        return intersectionList;
    }

    public static void split(String str, List<String> list) {
        StringBuilder sb;
        for (int i = 0; i < str.length() - 1; i++) {
            char a = str.charAt(i);
            char b = str.charAt(i + 1);
            if (isPossible(a) && isPossible(b)) {
                sb = new StringBuilder();
                sb.append(a).append(b);
                list.add(sb.toString());
            }
        }
    }

    public static boolean isPossible(char c) {
        return 97 <= c && c <= 122;
    }
}
