package algospot.greedy.MATCHORDER;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static int SIZE;
    public static int[] RUSSIA;
    public static int[] KOREA;
    public static int ANSWER;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCnt = Integer.parseInt(br.readLine().trim());
        for (int cs = 0; cs < caseCnt; cs++){
            SIZE = Integer.parseInt(br.readLine().trim());
            RUSSIA = new int[SIZE];
            KOREA = new int[SIZE];
            ANSWER = 0;
            String[] tmpArr = br.readLine().trim().split(" ");
            for (int i = 0; i < SIZE; i++){
                RUSSIA[i] = Integer.parseInt(tmpArr[i]);
            }
            tmpArr = br.readLine().trim().split(" ");
            for (int i = 0; i < SIZE; i++){
                KOREA[i] = Integer.parseInt(tmpArr[i]);
            }
            Arrays.sort(RUSSIA);
            Arrays.sort(KOREA);
            int posRussia = 0;
            int posKorea = 0;
            while(posRussia < SIZE){
                boolean hasWon = false;
                for (int pos = posKorea; pos < SIZE; pos++){
                    if(RUSSIA[posRussia] <= KOREA[pos]){
                        hasWon = true;
                        ANSWER++;
                        posRussia++;
                        posKorea = pos+1;
                    }
                }
                if(!hasWon){
                    break;
                }
            }
            bw.write(ANSWER + "");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
