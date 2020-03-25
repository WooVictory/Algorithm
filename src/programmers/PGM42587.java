package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/25
 * 프린터.
 * 단순히 정렬을 한다고 되는 문제가 아니다.
 * 생각보다 까다로운 문제이다.
 * 지금까지 푼 문제 중 어려운 문제에 속한다.
 * 다시 풀어보기!
 */
public class PGM42587 {
    public static void main(String[] args) {
        //solution(new int[]{2, 1, 3, 2}, 2);
        solution(new int[]{1, 1, 9, 1, 1, 1}, 0);
    }

    public static int solution(int[] priorities, int location) {
        LinkedList<Document> list = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            list.add(new Document(i, priorities[i]));
        }

        // 인쇄 순번을 의미한다.
        int answer = 1;
        Document firstDocument = null;

        while (list.size() > 0) {
            // 1. 인쇄 대기목록에 있는 가장 앞에 있는 문서를 갖고 온다.
            firstDocument = list.getFirst();
            for (int i = 1; i < list.size(); i++) {
                // 2. 나머지 인쇄 대기목록을 돌면서 j보다 중요도가 높은 문서가 한개라도 존재한다면 j를 대기목록의
                // 가장 마지막으로 삽입한다.
                if (firstDocument.priority < list.get(i).priority) {
                    list.addLast(firstDocument);
                    list.removeFirst();
                    break;
                }

                // 리스트의 마지막에 도달했을 때, 더 중요한 문서가 없다면 앞에 있는 문서를 인쇄한다.
                // 이 과정을 반복하면서 location 과 일치하는 index 를 가진 문서가 인쇄될 때, answer 값을 출력하게 된다.
                if (i == list.size() - 1) {
                    if (firstDocument.index == location) return answer;
                    // 3. 인쇄한다. 인쇄를 함으로써 인쇄 순번을 증가시켜준다.
                    list.removeFirst();
                    answer++;
                }
            }
        }

        // list.size()가 1일 경우, 마지막 문서이므로 answer 값을 출력해주면 된다.
        return answer;
    }

    static class Document {
        int index;
        int priority;

        Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
