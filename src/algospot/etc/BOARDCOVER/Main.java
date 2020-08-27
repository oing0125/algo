package algospot.etc.BOARDCOVER;

import java.io.*;

public class Main {
    public static int H, W;
    public static char[][] board;
    public static boolean[][] covered;
    public static int[][][] ways = new int[][][]{
            {{1,0}, {0,1}} // 아래, 오른쪽
            , {{1,0}, {1, -1}} // 아래, 아왼쪽
            , {{1,0}, {1, 1}} // 아래, 아래오른
            , {{0,1}, {1, 1}} // 오른, 아래오른오른쪽
    };
    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseSu = Integer.parseInt(br.readLine().trim());
        for (int cs = 0; cs < caseSu; cs++) {
            String[] tmpInput = br.readLine().split(" ");
            H = Integer.parseInt(tmpInput[0].trim());
            W = Integer.parseInt(tmpInput[1].trim());
            board = new char[H][W];
            covered = new boolean[H][W];
            answer = 0;
            int wCnt = 0;
            for (int i = 0; i < H; i++) {
                String tmpStr = br.readLine().trim();
                for (int j = 0; j < W; j++) {
                    board[i][j] = tmpStr.charAt(j);
                    if (board[i][j] == '.') {
                        wCnt++;
                    }
                }
            }
            // 3의 배수가 아니면 종료
            if(wCnt % 3 == 0){
                recurse();
            }
            bw.write(answer+"");
            bw.newLine();
        }
        bw.flush();
    }

    public static void recurse(){
        int startY = -1, startX = -1;
        P: for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] == '.' && covered[i][j] == false) {
                    startY = i;
                    startX = j;
                    break P;
                }
            }
        }
        if(startX == -1 && startY == -1){
            answer++;
            return;
        }
        for (int k = 0; k < ways.length; k++) {
            int candidateH1 = startY + ways[k][0][0];
            int candidateW1 = startX + ways[k][0][1];
            int candidateH2 = startY + ways[k][1][0];
            int candidateW2 = startX + ways[k][1][1];
            if (candidateH1 >= 0 && candidateH1 < H
                    && candidateH2 >= 0 && candidateH2 < H
                    && candidateW1 >= 0 && candidateW1 < W
                    && candidateW2 >= 0 && candidateW2 < W
                    && board[candidateH1][candidateW1] == '.'
                    && board[candidateH2][candidateW2] == '.'
                    && board[startY][startX] == '.') {
                covered[startY][startX] = covered[candidateH1][candidateW1] = covered[candidateH2][candidateW2] = true;
                recurse();
                covered[startY][startX] = covered[candidateH1][candidateW1] = covered[candidateH2][candidateW2] = false;
            }
        }
    }
}
