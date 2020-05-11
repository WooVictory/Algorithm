package programmers;

/**
 * created by victory_woo on 2020/05/11
 *
 */
public class PGM49993 {
    public static void main(String[] args) {
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        String skill = "CBD";


        System.out.println(solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        StringBuilder sb;
        for (String tree : skill_trees) {
            sb = new StringBuilder();
            for (int i = 0; i < tree.length(); i++) {
                // 1. skill 에 포함되는 skill_tree 가 있다면 등장하는 순서대로 문자열을 만든다.
                if (skill.contains(String.valueOf(tree.charAt(i)))) {
                    sb.append(tree.charAt(i));
                }
            }

            // 2. 길이가 더 짧은 값을 찾는다.
            String comp = sb.toString();
            int len = Math.min(skill.length(), comp.length());

            // 3. 만들어진 comp 는 등장하는 스킬 순서대로 구성된다.
            // 따라서 skill 과 비교했을 때, 값이 다르다는 것은 순서가 다름을 뜻한다. 따라서 카운트 하지 않는다.
            // 값이 같다면 순서가 동일한 것이고 카운트한다.
            boolean flag = true;
            for (int i = 0; i < len; i++) {
                if (skill.charAt(i) != comp.charAt(i)) {
                    flag = false;
                    break;
                }
            }

            if (flag) answer++;
        }
        return answer;
    }
}
