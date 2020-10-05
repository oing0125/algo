package hackerrank.algo.candies;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the candies function below.
    static long candies(int n, int[] arr) {
        int[] scoreArr = new int[n];

        // 1) 다 1 입력하고 이전보다 클 경우에는 기존 값 + 1
        for (int i = 0; i < n; i++){
            scoreArr[i] = 1;

            if(i != 0 && arr[i-1] < arr[i]){
                scoreArr[i] = scoreArr[i-1] + 1;
            }
        }

        for (int i = n-2; i >= 0; i--){
            if(arr[i] > arr[i+1]){
                scoreArr[i] = Math.max(scoreArr[i], scoreArr[i+1]+1);
            }
        }

        // 3) 순회하면서 값 계산
        long answer = 0;
        for (int score : scoreArr) {
            answer += score;
        }

        // 5000050000
        return answer;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

