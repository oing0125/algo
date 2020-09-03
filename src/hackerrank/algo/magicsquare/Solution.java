package hackerrank.algo.magicsquare;
import java.io.*;
import java.util.*;

public class Solution {
    /*
    1
    2
    3
    4
     */
    public static int[][] MAP = new int[3][3];
    public static Set<String> POSSIBLE_CASE_SET = new HashSet<String>();
    public static int[][][] POSSIBLE_CASE;
    public static int[] CACHED = new int[5];

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int[][] square = new int[3][3];
        square[1][1] = 5;
        generatePossibleCaseAsString(square);
        convertPossibleCaseToMap();
        return getMinCost(s);
    }

    public static void convertPossibleCaseToMap(){
        POSSIBLE_CASE = new int[POSSIBLE_CASE_SET.size()][][];
        int idx = 0;
        for (String possibleCaseStr: POSSIBLE_CASE_SET) {
            int[][] possibleCase = new int[3][3];
            int strIdx = 0;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    int now = strIdx++;
                    possibleCase[i][j] = Integer.parseInt(possibleCaseStr.substring(now, now + 1));
                }
            }
            POSSIBLE_CASE[idx++] = possibleCase;
        }
    }

    public static void generatePossibleCaseAsString(int[][] square){;
        boolean isLast = true;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(i == 1 && j == 1){
                    continue;
                }
                if(square[i][j] == 0){
                    isLast = false;
                    for (int num = 1; num < 5; num++){
                        if(CACHED[num] == 0) {
                            CACHED[num] = 1;
                            // 매핑되는 반대편 값 구하기
                            int nextI, nextJ;
                            List<int[]> nextList = new ArrayList<int[]>();
                            // 꼭지점일 경우
                            nextI = Math.abs(i - 2);
                            nextJ = Math.abs(j - 2);
                            square[i][j] = num;
                            square[nextI][nextJ] = 10 - num;
                            generatePossibleCaseAsString(square);
                            square[i][j] = 10 - num;
                            square[nextI][nextJ] = num;
                            generatePossibleCaseAsString(square);
                            square[i][j] = 0;
                            square[nextI][nextJ] = 0;
                            CACHED[num] = 0;
                        }
                    }
                }
            }
        }
        if(isLast){
            if(isMasicSquare(square)){
                String str = "";
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 3; j ++){
                        str += square[i][j]+"";
                    }
                }
                POSSIBLE_CASE_SET.add(str);
            }
        }
    }

    public static boolean isMasicSquare(int[][] square){
        // 가로/세로 비교
        for (int i = 0; i < 3; i++){
            int sum1 = 0;
            int sum2 = 0;
            for (int j = 0; j < 3; j++){
                sum1 += square[i][j];
                sum2 += square[j][i];
            }
            if(sum1 != 15 || sum2 != 15){
                return false;
            }
        }
        // 대각선
        int sum3 = 0;
        int sum4 = 0;
        for (int i = 0; i < 3; i ++){
            sum3 += square[i][i];
            sum4 += square[2-i][2-i];
        }
        if(sum3 != 15 || sum4 != 15){
            return false;
        }
        return true;
    }

    public static int getMinCost( int[][] square){
        int answer = Integer.MAX_VALUE;
        for (int[][] magicSquare : POSSIBLE_CASE) {
            int gap = 0;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    gap += Math.abs(magicSquare[i][j] - square[i][j]);
                }
            }
            answer = Math.min(gap, answer);
        }
        return answer;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
