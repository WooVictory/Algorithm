package programmers;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/03/30
 */
public class PGM17677Re {
    public static final int VALUE = 65536;

    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }

    public static int solution(String str1, String str2) {
        // 1. 대소문자를 구분하지 않기 때문에 두 문자열 모두 소문자로 변환한다.
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 2. 두 글자씩 끊어서 영문자 쌍을 이루는 문자열로 구성된 list 생성.
        ArrayList<String> list1 = split(str1);
        ArrayList<String> list2 = split(str2);

        ArrayList<String> unionList = getUnionList(list1, list2);
        ArrayList<String> intersectionList = getIntersectionList(list1, list2);

        if (unionList.size() == 0) return VALUE;
        else return (int) ((double) intersectionList.size() / unionList.size() * VALUE);
    }

    /**
     * 중복을 허용하는 다중집합의 합집합을 구한다.
     * list1, list2 의 원본을 복사해서 사용한다.
     * 그렇지 않으면 list 의 값이 바뀌기 때문에 정확한 결과를 도출할 수 없다.
     *
     * @param list1
     * @param list2 return
     */
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

    /**
     * 중복을 허용하는 다중집합의 교집합을 구한다.
     * list1, list2 의 원본을 복사해서 사용한다.
     * 그렇지 않으면 list 의 값이 바뀌어 정확한 결과를 얻을 수 없다.
     */
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

    public static ArrayList<String> split(String str) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb;
        for (int i = 0; i < str.length() - 1; i++) {
            char a = str.charAt(i), b = str.charAt(i + 1);
            if (isAlphabet(a) && isAlphabet(b)) {
                sb = new StringBuilder();
                list.add(sb.append(a).append(b).toString());
            }
        }
        return list;
    }

    public static boolean isAlphabet(char c) {
        return 97 <= c && c <= 122;
    }
}
