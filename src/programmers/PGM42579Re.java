package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * created by victory_woo on 2020/04/03
 * 베스트 앨범.
 * 해시.
 * 다시 푸는 중!
 */
public class PGM42579Re {
    public static void main(String[] args) {
        String[] s = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        solution(s, plays);
    }

    public static int[] solution(String[] genres, int[] plays) {
        // key : 장르, value : list 에 접근할 index 가 된다.
        HashMap<String, Integer> map = new HashMap<>();
        List<Genre> list = new ArrayList<>();
        int genreIndex = 0;

        for (int i = 0; i < genres.length; i++) {
            Genre genre;
            Integer index = map.get(genres[i]);

            // 장르를 key 값으로 저장된 값이 없다면 null 을 반환하며
            // 이때는 새로운 장르를 추가해준다. value : 장르에 대한 index 값.
            if (index == null) {
                genre = new Genre();
                map.put(genres[i], genreIndex++);
            } else {
                // 널이 아닌 경우, 장르에 대한 인덱스를 반환하게 되고
                // 이를 이용해서 genreList 에서 해당 장르를 꺼낸다.
                genre = list.get(index);
            }

            // Song 객체를 만들고,
            // 동일한 장르에 대해서 리스트인 songs 에 노래를 추가하고 총 재생횟수를 추가한다.
            Song song = new Song(i, plays[i]);
            genre.addSong(song);
            genre.addPlays(plays[i]);

            // 인덱스가 null 이었던 경우에는 새롭게 장르를 list 에 추가해줘야 한다.
            // 이 문장이 위의 if 문에 존재해도 되지만, 가독성이 안좋은 것 같아 아래로 빼준다.
            if (index == null) list.add(genre);
        }

        // 장르별로 가장 많이 수록한 노래가 앞으로 오도록 정렬 기준을 설정했으므로 그 방식으로 정렬된다.
        Collections.sort(list);

        // 베스트 앨범에는 가장 많이 수록된 장르별로 2개씩 뽑아야 하기 때문에 장르를 뽑아서
        // 해당 장르별 노래의 사이즈를 계산해서 베스트 앨범에 대한 배열 길이를 정한다.
        int size = 0;
        for (Genre genre : list) {
            if (genre.getSongSize() > 1) size += 2;
            else size += 1;
        }

        // 배열을 할당한다.
        int[] answer = new int[size];
        int answerIndex = 0;
        for (Genre genre : list) {
            // 같은 장르로 묶인 노래에 대해서는 적용한 기준으로 정렬된다.
            Collections.sort(genre.getList());

            // 동일 장르의 노래에서는 2개만 뽑아야 하기 때문에 index 가 2보다 작은 경우에만 추가하도록 한다.
            for (int i = 0; i < genre.getSongSize(); i++) {
                if (i < 2) answer[answerIndex++] = genre.getList().get(i).getId();
            }
        }

        // 잘 출력되는지 확인하기 위한 문장.
        for (int a : answer) System.out.print(a + " ");

        return answer;
    }

    // 장르별로 가장 많이 재생된 장르를 먼저 수록한다.
    // 따라서 장르별 내림차순 정렬.
    static class Genre implements Comparable<Genre> {
        private int total;
        private List<Song> list;

        Genre() {
            total = 0;
            list = new ArrayList<>();
        }

        public int getTotal() {
            return total;
        }

        public List<Song> getList() {
            return list;
        }

        void addSong(Song song) {
            list.add(song);
        }

        void addPlays(int plays) {
            total += plays;
        }

        int getSongSize() {
            return list.size();
        }

        // 내림차순 정렬.
        @Override
        public int compareTo(Genre that) {
            return Integer.compare(that.getTotal(), this.getTotal());
        }
    }

    // 같은 장르에 대해서는 각 노래가 많이 재생된 만큼 앞에 수록한다. -> 따라서 같은 장르의 노래에 대해서는 재생횟수 기준으로 내림차순 정렬.
    // 재생횟수가 같다면 id를 기준으로 오름차순 정렬.
    static class Song implements Comparable<Song> {
        private int id;
        private int plays;

        Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }

        int getId() {
            return id;
        }

        int getPlays() {
            return plays;
        }

        @Override
        public int compareTo(Song that) {
            if (this.plays == that.plays) {
                return Integer.compare(this.getId(), that.getId());
            }
            return Integer.compare(that.getPlays(), this.getPlays());
        }
    }
}
