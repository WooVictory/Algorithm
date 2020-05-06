package 카카오18;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 18 기출.
 * 다시 푸는 중.
 * 뉴스 클러스터링.
 */
public class RE_Test4 {
    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }

    private static final int VALUE = 65536;

    public static int solution(String str1, String str2) {
        // 1. 대소문자를 무시하기 때문에 두 문자열을 소문자로 변환한다.
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 2. 두 글자씩 끊어서 리스트를 만든다.
        ArrayList<String> list1 = split(str1);
        ArrayList<String> list2 = split(str2);

        ArrayList<String> intersectionList = getIntersectionList(list1, list2);
        ArrayList<String> unionList = getUnionList(list1, list2);

        if (intersectionList.size() == 0 && unionList.size() == 0) return VALUE;

        return (int) (((double) intersectionList.size() / unionList.size()) * VALUE);
    }

    // 두 글자씩 끊어서 리스트를 만든다.
    // 영문자로 된 글자쌍만 유효하다. 따라서 공백, 숫자, 특수문자인 경우는 안된다.
    // Character.isAlphabetic()를 사용해 영문자로만 구성된 두 글자 리스트를 만든다.
    private static ArrayList<String> split(String str) {
        StringBuilder sb;
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < str.length() - 1; i++) {
            sb = new StringBuilder();
            char a = str.charAt(i);
            char b = str.charAt(i + 1);
            if (Character.isAlphabetic(a) && Character.isAlphabetic(b)) {
                sb.append(str.charAt(i)).append(str.charAt(i + 1));
                result.add(sb.toString());
            }
        }

        System.out.println(result);
        return result;
    }

    // 교집합을 구한다.
    // list1과 list2에서 함께 포함되어 있는 원소를 담는다.
    // 담고 list2에서는 그 문자열을 삭제해줘야 한다.
    // 하나씩만 매칭되어야 하기 때문에 매칭을 확인한 문자열을 리스트에서 삭제해준다.
    // list1 = [fr, ra, an, nc, ce, fr] , list2 = [fr, re, en, nc, ch]라고 가정하자.
    // list1의 fr은 list2의 fr과 매칭된다. list2에서 fr은 지우지 않는다면
    // list1의 마지막 fr은 list2의 fr과 다시 매칭된다. 이는 교집합의 의미가 아니므로 매칭에 사용해서 교집합인 list2의 원소는 삭제해줘야 한다.
    private static ArrayList<String> getIntersectionList(ArrayList<String> list1, ArrayList<String> list2) {
        list1 = (ArrayList<String>) list1.clone();
        list2 = (ArrayList<String>) list2.clone();

        ArrayList<String> result = new ArrayList<>();
        for (String s : list1) {
            if (list2.contains(s)) {
                result.add(s);
                list2.remove(s);
            }
        }
        return result;
    }

    // 합집합을 구한다. list1을 union 에 모두 담고, list2는 list1과 교집합인 원소를 제외해준다.
    // 그리고 union 에 list2의 남은 부분을 다 합쳐주면 합집합을 구할 수 있다.
    // 즉, 합집합 = A + B - (A와 B의 교집합)
    private static ArrayList<String> getUnionList(ArrayList<String> list1, ArrayList<String> list2) {
        list1 = (ArrayList<String>) list1.clone();
        list2 = (ArrayList<String>) list2.clone();

        ArrayList<String> result = new ArrayList<>();
        for (String s : list1) {
            list2.remove(s);
            result.add(s);
        }
        result.addAll(list2);
        return result;
    }
}
