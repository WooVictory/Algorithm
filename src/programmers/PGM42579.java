package programmers;

import java.util.*;

/**
 * created by victory_woo on 2020/04/02
 * 베스트 앨범.
 * 해시+정렬 개념
 * 다시 풀어보기!
 */
public class PGM42579 {
    public static void main(String[] args) {
        String[] s = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        solution(s, plays);
    }

    public static int[] solution(String[] genres, int[] plays) {
        // Genre, Index 저장을 위한 map.
        Map<String, Integer> map = new HashMap<>();
        List<Genre> genreList = new ArrayList<>();

        int gIndex = 0;
        for (int i = 0; i < genres.length; i++) {
            Genre genre;
            Integer index = map.get(genres[i]);

            if (index == null) {
                genre = new Genre();
                map.put(genres[i], gIndex++);
            } else {
                genre = genreList.get(index);
            }

            Song song = new Song(i, plays[i]);
            genre.addSong(song);
            genre.total += plays[i];

            if (index == null) genreList.add(genre);
        }

        Collections.sort(genreList);
        int size = 0;
        for (Genre genre : genreList) {
            Collections.sort(genre.getSongs());
            if (genre.getSongsSize() > 1) size += 2;
            else size += 1;
        }

        int[] answer = new int[size];
        int index = 0;
        for (Genre genre : genreList) {
            for (int i = 0; i < genre.getSongsSize(); i++) {
                if (i < 2) answer[index++] = genre.getSongs().get(i).id;
            }
        }

        for (int a : answer) System.out.print(a + " ");

        System.out.println();

        return answer;
    }

    // 해당 장르가 총 몇번 플레이 되었는지와 어떤 고유 번호의 노래들을 포함하는지 구분하는 클래스.
    static class Genre implements Comparable<Genre> {
        int total;
        List<Song> songs;

        Genre() {
            this.total = 0;
            this.songs = new ArrayList<>();
        }

        void addSong(Song song) {
            songs.add(song);
        }

        int getSongsSize() {
            return songs.size();
        }

        List<Song> getSongs() {
            return songs;
        }

        // 많이 재생된 장르를 먼저 정렬하므로 재생수(total) 기준으로 내림차순 정렬.
        @Override
        public int compareTo(Genre genre) {
            return Integer.compare(genre.total, this.total);
        }
    }

    // 같은 장르에서도 고유 번호별로 노래를 구분하기 위한 클래스.
    static class Song implements Comparable<Song> {
        int id;
        int play;

        Song(int id, int play) {
            this.id = id;
            this.play = play;
        }

        @Override
        public int compareTo(Song song) {
            if (this.play == song.play) {
                return Integer.compare(this.id, song.id);
            }
            return Integer.compare(song.play, this.play);
        }
    }
}
