package programmers;

/**
 * created by victory_woo on 2020/03/22
 * 핸드폰 번호 가리기.
 * 입력받은 핸드폰 번호의 뒷 4자리를 제외하고 모두 *로 가리는 문제이다.
 * 그래서 20 이하의 문자열에서 뒷 4자리를 제외해야 하기 때문에
 * 앞에서부터 len - 4까지 *로 바꾸고 len-4 ~ len 까지의 4자리 번호를 그대로 붙여주어 string 으로 변환해
 * return 한다.
 *
 * 혹은 string.toCharArray()로 변환해서 len-4까지 char[i]='*'를 넣고
 * String.valueOf(ch)로 return 한다.
 */
public class PGM12948 {
    public static void main(String[] args) {
        System.out.println(solution2("01033334444"));
        System.out.println(solution("010333344441231231231111"));
        System.out.println(solution("027778888"));
    }

    public static String solution(String phone_number) {
        int len = phone_number.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (len - 4); i++) sb.append('*');

        sb.append(phone_number, len - 4, len);
        return sb.toString();
    }

    public static String solution2(String phone_number) {
        char[] ch = phone_number.toCharArray();
        for (int i = 0; i < ch.length - 4; i++) {
            ch[i] = '*';
        }

        return String.valueOf(ch);
    }
}
