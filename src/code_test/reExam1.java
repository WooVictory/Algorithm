package code_test;

import java.io.IOException;
import java.util.*;

public class reExam1 {
    public static void main(String[] args) throws IOException {
     /*   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] movies = {"spy", "ray", "spy", "room", "once", "ray", "spy", "once"};
        String[] answer = solution(movies);
        System.out.println(answer.length);
        for(int i=0;i<answer.length;i++)
            bw.write(answer[i]+"\n");

        bw.flush();
        bw.close();*/
        Arrays.asList("tests.tests.".split(" "));

    }

    public static String[] solution(String[] str){
        HashMap<String, MovieData> map = new HashMap<>();

        for(String data : str){
            if(map.containsKey(data)){
                MovieData movieData = map.get(data);
                movieData.repeatCount = movieData.repeatCount+1;

            }else {
                map.put(data, new MovieData(data,1));
            }
        }

        ArrayList<MovieData> list = new ArrayList<>();

        for(String key : map.keySet()){
            list.add(map.get(key));
        }

        Collections.sort(list, new Comparator<MovieData>() {
            @Override
            public int compare(MovieData o1, MovieData o2) {
                if(o1.repeatCount == o2.repeatCount){
                    return o1.movie.compareTo(o2.movie);
                }else if(o1.repeatCount>o2.repeatCount){
                    return -1;
                }else {
                    return 1;
                }
            }
        });

        String[] ans = new String[list.size()];
        for(int i=0;i<ans.length;i++){
            ans[i] = list.get(i).movie;
        }
        return ans;
    }
}

class MovieData{
    String movie;
    int repeatCount;

    public MovieData(String movie, int repeatCount){
        this.movie = movie;
        this.repeatCount = repeatCount;
    }

}
