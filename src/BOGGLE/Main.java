package BOGGLE;

import java.io.*;

public class Main {

    private static char[][] arr;
    private static char[] word;
    private static int[] arrX = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    private static int[] arrY = new int[]{1, 1, 1, 0, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i<caseCnt; i++){
            arr = new char[5][5];

            for (int j=0; j<5;j++){
                arr[j] = br.readLine().toCharArray();
            }
            int wordCnt = Integer.parseInt(br.readLine());
            for (int j=0; j<wordCnt; j++){
                word = br.readLine().toCharArray();
                boolean flag = false;

                // 1. Recursion(시간초과 발생)
                P: for (int tmpI=0; tmpI<5; tmpI++){
                    for (int tmpJ=0; tmpJ<5; tmpJ++){
                        if(search(tmpI, tmpJ, 0)){
                            flag = true;
                            break P;
                        }
                    }
                }

                if(flag){
                    bw.write(new String(word)+" YES");
                    bw.newLine();
                }else{
                    bw.write(new String(word)+" NO");
                    bw.newLine();
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean search(int nowX, int nowY, int pos){

        // stopping rule
        if(nowX<0 || nowX>4 || nowY<0 || nowY>4){
            return false;
        }
        // 만약 현재 위치가 맞으면 다음단계로 진행
        if(arr[nowX][nowY] == word[pos]){
            if(pos == word.length-1){
                return true;
            }
            for (int i=0; i<arrX.length; i++){
                int nextX = nowX + arrX[i];
                int nextY = nowY + arrY[i];
                if(search(nextX, nextY, pos+1)){
                   return true;
                }
            }
        }
        return false;
    }
}


/*

1
URLPM
XPRET
GIAET
XTNZY
XOQRS
2
REPEAT
GIAZAPX
 */
