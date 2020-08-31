package hackerrank.spartArrays;

import com.sun.org.apache.xpath.internal.objects.XObject;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the matchingStrings function below.
    static int[] matchingStrings(String[] strings, String[] queries) {
        int[] dap = new int[queries.length];
        String[] originalQueries = queries.clone();
        Map<String, Integer> answers = new HashMap<String, Integer>();
        // 1. 정렬
        Arrays.sort(queries);
        Arrays.sort(strings);

        // 순서대로 돌면서 값 비교
        String cachedTxt = "";
        int cnt = 0;
        int queryIdx = 0, stringIdx = 0;
        while(queryIdx < queries.length){
            // 이전 단어와 똑같을 경우
            if(cachedTxt.equals(queries[queryIdx])){
                dap[queryIdx] = cnt;
            }else{
                cnt = 0;
                for (int i = stringIdx; i < strings.length; i++){
                    if(strings[i].equals(queries[queryIdx])){
                        cnt++;
                    }else if(cnt != 0){
                        stringIdx = i;
                        cachedTxt = queries[queryIdx];
                        break;
                    }
                }
                answers.put(queries[queryIdx], cnt);
            }
            queryIdx++;
        }

        // 기존 배열 순서대로 dap 입력
        for (int i = 0; i < originalQueries.length; i++){
            dap[i] = (answers.containsKey(originalQueries[i])) ? answers.get(originalQueries[i]) : 0;
        }
        return dap;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int stringsCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] strings = new String[stringsCount];

        for (int i = 0; i < stringsCount; i++) {
            String stringsItem = scanner.nextLine();
            strings[i] = stringsItem;
        }

        int queriesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] queries = new String[queriesCount];

        for (int i = 0; i < queriesCount; i++) {
            String queriesItem = scanner.nextLine();
            queries[i] = queriesItem;
        }

        int[] res = matchingStrings(strings, queries);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
