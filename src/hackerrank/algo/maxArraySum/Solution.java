package hackerrank.algo.maxArraySum;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    /*
        (dp[0] 과 dp[1]은 미리 채워 놓자)
        dp[i] = max(dp[i-2] + arr[i], arr[i], max(dp[i-2], dp[i-1]))
        dp[0] = 3
        dp[1] = 7
      ex) dp[2] = max(dp[0] + arr[2], arr[2], max(dp[0], dp[1])) = max(3+4, 4, 7) = 7
          dp[3] = max(dp[1] + arr[3], arr[3], max(dp[1], dp[2])) = max(7 + 6, 6, 7) = 13
          dp[4] = max(dp[2] + arr[4], arr[4], max(dp[2], dp[1])) = max(7 + 5, 5, 7) = 12

          return max(dp[n-1], dp[n-2])
     */

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int answer = 0;
        int[] dp = new int[arr.length];
        // 2 이하일 경우 dp 필요 없이 바로 계산
        if(arr.length < 2){
            int tmpSum = 0;
            for (int i = 0; i < arr.length; i ++){
                if(arr[i] > 0){
                    tmpSum += arr[i];
                }
            }
            answer = tmpSum;
        }else{
            dp[0] = Math.max(0, arr[0]);
            dp[1] = Math.max(Math.max(0, arr[1]), dp[0]);
            for (int i = 2; i < arr.length; i++){
                dp[i] = Math.max(Math.max(Math.max(dp[i-2] + arr[i], arr[i]), dp[i-2]), dp[i-1]);
            }

            answer = Math.max(dp[arr.length-1], dp[arr.length-2]);
        }
        return answer;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
