package dp.TRIANGLEPATH;

import java.io.*;
import java.util.Arrays;

public class Main {

    private static int[][] CACHED;
    private static int[][] MAP;
    private static int SIZE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCnt = Integer.parseInt(br.readLine().trim());
        for (int cs = 0; cs < caseCnt; cs++){
            SIZE = Integer.parseInt(br.readLine().trim());
            CACHED = new int[SIZE][SIZE];
            MAP = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++){
                for (int j = 0; j < SIZE; j++){
                    CACHED[i][j] = -1;
                }
            }
            for (int i = 0; i < SIZE; i++){
                String[] items = br.readLine().trim().split(" ");
                for (int j = 0; j < items.length; j++){
                    MAP[i][j] = Integer.parseInt(items[j]);
                }
            }
            // recurse1용 출력
//            recurse1(0, 0, 0);
//            Arrays.sort(CACHED[SIZE-1]);
//            bw.write(CACHED[SIZE-1][SIZE-1]+"");
            bw.write(recurse2(0,0)+"");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void recurse1(int i, int j, int prevSum){
        // 기저 사례1. (범위를 벗어날 경우)
        if(i >= SIZE || j >= SIZE){
            return;
        }
        // 기저 사례1. 메모이제이션된 값이 더 클 경우 종료
        if(CACHED[i][j] > prevSum + MAP[i][j]){
            return;
        }
        int nowSum = prevSum + MAP[i][j];
        CACHED[i][j] = nowSum;
        recurse1(i+1, j, nowSum);
        recurse1(i+1, j+1, nowSum);
    }

    public static int recurse2(int i, int j){
        // 기저 사례1. (범위를 벗어날 경우)
        if(i >= SIZE || j >= SIZE){
            return 0;
        }
        // 기저 사례1. 메모이제이션된 값이 더 클 경우 종료
        if(CACHED[i][j] != -1){
            return CACHED[i][j];
        }
        CACHED[i][j] = Math.max(recurse2(i+1, j+1), recurse2(i+1, j)) + MAP[i][j];
        return CACHED[i][j];
    }
}

/*
2
5
6
1 2
3 7 4
9 4 1 7
2 7 5 9 4
5
1
2 4
8 16 8
32 64 32 64
128 256 128 256 128
 */