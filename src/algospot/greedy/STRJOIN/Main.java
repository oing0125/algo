package algospot.greedy.STRJOIN;

import java.io.*;
import java.util.PriorityQueue;

public class Main {
    private static PriorityQueue<Integer> PRI_QUEUE;
    private static int SIZE;
    private static int[] DATA_ARR;
    private static int ANSWER;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCnt = Integer.parseInt(br.readLine().trim());
        for (int cs = 0; cs < caseCnt; cs++){
            SIZE = Integer.parseInt(br.readLine().trim());
            PRI_QUEUE = new PriorityQueue<Integer>();
            DATA_ARR = new int[SIZE];
            ANSWER = 0;
            String[] tmpArr = br.readLine().trim().split(" ");
            for (int i = 0; i < SIZE; i++){
                PRI_QUEUE.add(Integer.parseInt(tmpArr[i]));
            }
            while (PRI_QUEUE.size() > 1){
                int sum = PRI_QUEUE.poll() + PRI_QUEUE.poll();
                ANSWER += sum;
                PRI_QUEUE.add(sum);
            }
            bw.write(ANSWER + "");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

/*
3
3
2 2 4
5
3 1 3 4 1
8
1 1 1 1 1 1 1 2
 */