package language;

        import java.io.*;
        import java.math.BigInteger;

public class BOJ10824 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = bf.readLine();
        String[] words = input.split(" ");

        String ab = words[0]+words[1];
        String cd = words[2]+words[3];

        BigInteger big1 = new BigInteger(ab);
        BigInteger big2= new BigInteger(cd);
        bw.write(big1.add(big2)+"\n");
        bw.flush();
        bw.close();
        bf.close();
    }
}
