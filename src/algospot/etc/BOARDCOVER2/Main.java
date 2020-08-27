package algospot.etc.BOARDCOVER2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int H;
    private static int W;
    private static int R;
    private static int C;
    private static char[][] MAP;
    private static char[][] BLOCK_MAP;
    private static boolean[][] CACHED;
    private static int ANSWER;
    private static ArrayList<Block>[] BLOCK_LIST_ARR;
    private static int BLOCK_SIZE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCnt = Integer.parseInt(br.readLine().trim());
        for (int cs = 0; cs < caseCnt; cs++) {
            String[] inputArr = br.readLine().trim().split(" ");
            H = Integer.parseInt(inputArr[0]);
            W = Integer.parseInt(inputArr[1]);
            R = Integer.parseInt(inputArr[2]);
            C = Integer.parseInt(inputArr[3]);
            MAP = new char[H][W];
            BLOCK_MAP = new char[R][C];
            CACHED = new boolean[H][W];
            BLOCK_LIST_ARR = new ArrayList[4];
            ANSWER = -1;
            BLOCK_SIZE = 0;
            for (int i = 0; i < 4; i++) {
                BLOCK_LIST_ARR[i] = new ArrayList<Block>();
            }
            // 입력 처리1
            for (int i = 0; i < H; i++) {
                char[] inputCharArr = br.readLine().trim().toCharArray();
                for (int j = 0; j < W; j++) {
                    MAP[i][j] = inputCharArr[j];
                }
            }
            for (int i = 0; i < R; i++) {
                char[] inputCharArr = br.readLine().trim().toCharArray();
                for (int j = 0; j < C; j++) {
                    BLOCK_MAP[i][j] = inputCharArr[j];
                    if(BLOCK_MAP[i][j] == '#'){
                        BLOCK_SIZE++;
                    }
                }
            }
            // 가능한 블록 생성
            generateBlock();

            // search
            search(0, 0, 0);

            bw.write(ANSWER+"");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void generateBlock() {
        int baseI = -1;
        int baseJ = -1;

        for (int rotate = 0; rotate < 4; rotate++) {
            for (int i = 0; i < BLOCK_MAP.length; i++) {
                for (int j = 0; j < BLOCK_MAP[0].length; j++) {
                    if (BLOCK_MAP[i][j] == '#') {
                        if (baseI == -1) {
                            baseI = i;
                            baseJ = j;
                        }
                        BLOCK_LIST_ARR[rotate].add(new Block(i - baseI, j - baseJ));
                    }
                }
            }

            // 90도씩 돌려서 모든 케이스 확인(기준점도 회전한 것에 맞춰서 변경)
            baseI = baseJ;
            baseJ = BLOCK_MAP.length - baseI - 1;
            rotateBlock();
        }
    }

    public static void rotateBlock() {
        char[][] newBlockMap = new char[BLOCK_MAP[0].length][BLOCK_MAP.length];
        for (int i = 0; i < BLOCK_MAP.length; i++) {
            for (int j = 0; j < BLOCK_MAP[0].length; j++) {
                newBlockMap[j][BLOCK_MAP.length - i - 1] = BLOCK_MAP[i][j];
            }
        }
        BLOCK_MAP = newBlockMap;
    }

    public static int search(int nowI, int nowJ, int cnt) {
        // 기저사례1: 범위를 벗어날 경우
        if(nowI >= H || nowJ >= W){
            return cnt;
        }

        // 가치치기
        int leftEmpty = 0;
        for (int i = 0; i < H; i++){
            for (int j = 0; j < W; j++) {
                if(MAP[i][j] == '.' || !CACHED[i][j]){
                    leftEmpty++;
                }
            }
        }
        if(cnt + leftEmpty/BLOCK_SIZE <= ANSWER){
            return cnt;
        }

        // 탐색
        for (int i = nowI; i < H; i++){
            for (int j = ((i == nowI) ? nowJ : 0); j < W; j++){
                if(MAP[i][j] == '.'){
                    for (List<Block> blockList : BLOCK_LIST_ARR){
                        boolean isMatched = true;
                        for (Block block: blockList){
                            int tmpI = i + block.i;
                            int tmpJ = j + block.j;
                            if(tmpI < 0 || tmpI >= H || tmpJ < 0 || tmpJ >= W
                                    || CACHED[tmpI][tmpJ]
                                    || MAP[tmpI][tmpJ] != '.'){
                                isMatched = false;
                                break;
                            }
                        }
                        if(isMatched){
                            for (Block block: blockList){
                                CACHED[i+block.i][j+block.j] = true;
                            }
                            int nextI, nextJ;
                            if(j == W - 1){
                                nextI = i+1;
                                nextJ = j;
                            }else{
                                nextI = i;
                                nextJ = j+1;
                            }
                            int rslt = search(nextI, nextJ, cnt+1);
                            if(rslt > ANSWER){
                                ANSWER = rslt;
                            };
                            for (Block block: blockList){
                                CACHED[i+block.i][j+block.j] = false;
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }
}

class Block{
    int i;
    int j;

    public Block(int i, int j){
        this.i = i;
        this.j = j;
    }
}

/*

1
4 7 2 3
##.##..
#......
#....##
#..####
###
#..


2
4 7 2 3
##.##..
#......
#....##
#..####
###
#..
5 10 3 3
..........
..........
..........
..........
..........
.#.
###
..#

 */