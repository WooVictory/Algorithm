package 카카오18;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/04/30
 * 카카오 18 기출
 * [1차] 뉴스 클러스터링.
 */
public class Test4_re {
    public static void main(String[] args) {

    }

    private static final int VALUE = 65536;

    public static int solution(String str1, String str2) {

        // 문자열을 두 문자씩 잘라서 list 에 담는다.
        ArrayList<String> list1 = split(str1);
        ArrayList<String> list2 = split(str2);

        ArrayList<String> unionList = getUnionList(list1, list2);
        ArrayList<String> intersectionList = getIntersectionList(list1, list2);

        if (unionList.size() == 0 && intersectionList.size() == 0) return VALUE;

        return (int) (((double) intersectionList.size() / unionList.size()) * VALUE);
    }

    private static ArrayList<String> split(String str) {
        // 대소문자를 구분하지 않으므로 둘 다 소문자로 처리한다.
        str = str.toLowerCase();

        ArrayList<String> result = new ArrayList<>();
        StringBuilder sb;
        char now, next;
        for (int i = 0; i < str.length() - 1; i++) {
            sb = new StringBuilder();
            now = str.charAt(i);
            next = str.charAt(i + 1);
            if (Character.isAlphabetic(now) && Character.isAlphabetic(next)) {
                sb.append(now).append(next);
                result.add(sb.toString());
            }
        }

        return result;
    }

    // 합집합을 구한다.
    private static ArrayList<String> getUnionList(ArrayList<String> list1, ArrayList<String> list2) {
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

    // 교집합을 구한다.
    private static ArrayList<String> getIntersectionList(ArrayList<String> list1, ArrayList<String> list2) {
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
}
