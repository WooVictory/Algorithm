package 카카오18;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/04/30
 * 카카오 18 기출
 * [1차] 뉴스 클러스터링.
 *
 */
public class Test4 {
    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }

    private static final int VALUE = 65536;

    public static int solution(String str1, String str2) {
        // 대소문자를 구분하지 않으므로.
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // split() 메소드를 통해 문자열을 자른다.
        ArrayList<String> first = split(str1);
        ArrayList<String> second = split(str2);

        ArrayList<String> unionList = getUnionList(first, second);
        ArrayList<String> intersectionList = getIntersectionList(first, second);

        if (unionList.size() == 0 && intersectionList.size() == 0) return VALUE;

        return (int) (((double) intersectionList.size() / unionList.size()) * VALUE);
    }

    /*
     * list1을 돌면서 단어를 하나씩 꺼낸다. 그리고 list2에 존재하는지 확인한다.
     * list2에 존재한다면 존재하는 단어를 list2에서 삭제한다. 그리고 unionList 에는 list1을 순회하면서 단어를 모두 집어넣는다.
     * 순회가 끝나면 list1과 중복되는 단어가 제거된 list2를 unionList 에 모두 합쳐준다.
     * 이를 통해서 두 집합의 합집합을 구할 수 있다.
     * */
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

    private static ArrayList<String> getIntersectionList(ArrayList<String> list1, ArrayList<String> list2) {
        list1 = (ArrayList<String>) list1.clone();
        list2 = (ArrayList<String>) list2.clone();

        ArrayList<String> intersecionList = new ArrayList<>();
        for (String s : list1) {
            if (list2.contains(s)) {
                intersecionList.add(s);
                list2.remove(s); // 이미 체크했기 때문에 다시 체크할 수도 있음.
            }
        }

        return intersecionList;
    }

    private static ArrayList<String> split(String str) {
        StringBuilder sb;
        ArrayList<String> result = new ArrayList<>();
        char now, next;
        for (int i = 0; i < str.length() - 1; i++) {
            sb = new StringBuilder();
            now = str.charAt(i);
            next = str.charAt(i + 1);
            if (isAlpahbet(now) && isAlpahbet(next)) {
                sb.append(now).append(next);
                result.add(sb.toString());
            }
        }
        return result;
    }


    private static boolean isAlpahbet(char c) {
        return 65 <= c && c <= 90 || 97 <= c && c <= 122;
    }
}
