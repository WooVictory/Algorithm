package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/25
 * 프린터.
 * 확실히 좀 까다로운 문제이긴 하다.
 */
public class PGM42587Re {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    public static int solution(int[] priorities, int location) {
        int answer = 1;
        LinkedList<Document> list = new LinkedList<>();
        // 인덱스와 우선순위를 함께 객체로 하여 list 에 추가한다.
        for (int i = 0; i < priorities.length; i++) list.add(new Document(i, priorities[i]));

        Document firstDocument;
        // list 의 사이즈가 1보다 큰 동안 아래의 과정을 반복한다.
        // list 의 사이즈가 1인 경우에는 마지막 문서이기 때문에 answer 를 출력한다.
        while (list.size() > 1) {
            // 1. 인쇄 대기목록의 가장 앞의 문서를 꺼낸다.
            firstDocument = list.getFirst();
            // 2. 나머지 인쇄 대기목록에서 firstDocument 보다 우선순위가 높은 문서가 있는지 확인한다.
            // 우선순위가 높은 문서가 있다면 firstDocument 문서를 대기목록의 가장 마지막에 넣는다.
            for (int i = 1; i < list.size(); i++) {
                if (firstDocument.priority < list.get(i).priority) {
                    list.addLast(firstDocument);
                    list.removeFirst();
                    break;
                }

                // 3. 인쇄하는 과정이다.
                // 위의 과정을 반복해서 i가 list 의 마지막에 되면 우선순위가 높은 문서가 제일 앞에 위치하게 된다.
                // 우선순위가 가장 높은 문서를 인쇄한다. 그리고 인쇄 순번을 증가시킨다.
                // 만약, 여기서 location 값과 일치하는 index 값을 가진 문서를 찾은 경우 answer 값을 출력한다.
                if (i == list.size() - 1) {
                    if (location == firstDocument.index) return answer;
                    list.removeFirst();
                    answer++;
                }
            }
        }

        // list.size() == 1인 경우에는 남아 있는 문서가 1개이므로 마지막 문서에 해당한다.
        // 이때는 answer 값을 출력한다.
        return answer;
    }

    // 문서의 인덱스와 우선순위를 저장하는 Document 클래스.
    static class Document {
        int index;
        int priority;

        Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
