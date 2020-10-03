package hackerrank.algo.abbreviation;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    /*

    2
    daBcd
    ABC
    AbcDE
    AFDE

    // 종료조건
    1) idxA와 idxB가 모두 문자열을 벗어날 경우 => return true;
    2) idxA가 벗어날 경우 => return false;
    3) idxB가 벗어날 경우 => 남은 idxA를 모두 돌아 소문자이면 return false, 그렇지 않으면 return true;

    // 만약 소문자면?
    1) 삭제
     recurse(idxA + 1, idxB);
    2) 대문자로 변환후 매칭된다면?
     recurse(idxA + 1, idxB + 1);

    // 대문자면?
     1) 글자가 일치하면?
      recurse(idxA +1, idxB);
     2) 글자가 다르면?
      return false;
     */

    static char[] arrA;
    static char[] arrB;
    static int[][] dp;

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {

        // 1안) 재귀함수 => 타임아웃 발생
        arrA = a.toCharArray();
        arrB = b.toCharArray();

        // 2안) 메모이제션으로 타임아웃 해결
        dp = new int[arrA.length+1][arrB.length+1];

        if(recurse(0, 0)){
            return "YES";
        }else{
            return "NO";
        }
    }

    static boolean recurse(int idxA, int idxB){
        /*
        1) idxA와 idxB가 모두 문자열을 벗어날 경우 => return true;
        2) idxA가 벗어날 경우 => return false;
        3) idxB가 벗어날 경우 => 남은 idxA를 모두 돌아 소문자이면 return false, 그렇지 않으면 return true;
        */
        if(dp[idxA][idxB] != 0){
            return dp[idxA][idxB] == 1;
        }else if(idxA >= arrA.length && idxB >= arrB.length){
            return true;
        }else if(idxA >= arrA.length){
            return false;
        }else if(idxB >= arrB.length){
            for (int i = idxA; i < arrA.length; i++){
                //  대문자면 false return
                if(arrA[i] <= 'Z'){
                    return false;
                }
            }
            return true;
        }

        // 만약 소문자면?
        if(arrA[idxA] >= 'a'){
//        1) 삭제
            if(recurse(idxA + 1, idxB)){
                dp[idxA + 1][idxB] = 1;
                return true;
//        2) 대문자로 변환후 매칭된다면?
            }else if(arrB[idxB] == (char)(arrA[idxA] - 32)){
                if(recurse(idxA + 1, idxB + 1)){
                    dp[idxA + 1][idxB + 1] = 1;
                    return true;
                }else{
                    dp[idxA + 1][idxB + 1] = -1;
                    return false;
                }
            }else{
                dp[idxA][idxB] = -1;
                return false;
            }
        // 대문자면?
        }else{
            // 1) 글자가 일치하면?
            if(arrA[idxA] == arrB[idxB]){
                if(recurse(idxA + 1, idxB + 1)){
                    dp[idxA + 1][idxB + 1] = 1;
                    return true;
                }else{
                    dp[idxA + 1][idxB + 1] = -1;
                    return false;
                }
            // 2) 글자가 다르면?
            }else{
                dp[idxA][idxB] = -1;
                return false;
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

