package code_test;

import java.io.*;
import java.util.*;

public class exam1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] movies = {"spy", "ray", "spy", "room", "once", "ray", "spy", "once"};


        String[] tmp = solution(movies);
        for(String m : tmp)
            bw.write(m+"\n");
        bw.write("\n");
        bw.flush();
        bw.close();
    }


    public static String[] solution(String[] str) {

        HashMap<String, Movie> hashMap = new HashMap<>();

        for (String movieInfo : str) {
            if (hashMap.containsKey(movieInfo)) {
                // replace 과정
                Movie movie = hashMap.get(movieInfo);
                movie.count = movie.count + 1;
            } else {
                hashMap.put(movieInfo, new Movie(movieInfo, 1));
            }
        }

        List<Movie> list = new ArrayList<>();

        for (String key : hashMap.keySet()){
            list.add(hashMap.get(key));
        }


        Collections.sort(list, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if(o1.count == o2.count){
                    // count가 같을 때 이름에 대해서 오름 차순
                    //
                    return o1.movieName.compareTo(o2.movieName);
                }else if(o1.count>o2.count){ // 예매 횟수에 대해서 내림차순
                    return -1;
                }else {
                    return 1;
                }
            }
        });

        String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).movieName;
        }


        return answer;


    }


}

class Movie {
    String movieName;
    int count;

    public Movie(String movieName, int count) {
        this.movieName = movieName;
        this.count = count;
    }
}
