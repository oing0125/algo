package DP_JUMPGAME;

import java.io.*;

public class Main {

    public static int SIZE;
    public static int[][] MAP;
    public static int[][] CACHED;
    public static int[] MOVE_X = new int[]{1, 0};
    public static int[] MOVE_Y = new int[]{0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseSu = Integer.parseInt(br.readLine().trim());
        for (int cs = 0; cs < caseSu; cs++){
            SIZE = Integer.parseInt(br.readLine().trim());
            MAP = new int[SIZE][SIZE];
            CACHED = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++){
                String[] tmpStrArr = br.readLine().split(" ");
                for (int j = 0; j < SIZE; j++){
                    MAP[i][j] = Integer.parseInt(tmpStrArr[j]);
                }
            }
            recurse(0,0);
            if(CACHED[SIZE-1][SIZE-1] == 1){
                bw.write("YES");
            }else{
                bw.write("NO");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void  recurse(int y, int x){

        // 기저 사례(범위를 벗어날 경우)
        CACHED[y][x] = 1;

        int tmpY, tmpX;
        for (int i = 0; i < 2; i++){
            tmpY = y + MOVE_Y[i] * MAP[y][x];
            tmpX = x + MOVE_X[i] * MAP[y][x];
            if(tmpX < 0 || tmpX >= SIZE || tmpY < 0 || tmpY >= SIZE){
                continue;
            }
            if(CACHED[tmpY][tmpX] == 0){
                recurse(tmpY, tmpX);
            }
        }
    }
}


/*
7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 3
3 3 1 2 3 4 1
1 5 2 9 4 7 0



 */