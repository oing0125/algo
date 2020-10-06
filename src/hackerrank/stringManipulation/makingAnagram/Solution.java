package hackerrank.stringManipulation.makingAnagram;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        int answer = 0;
        int[] arrA = parsingToCharArr(a);
        int[] arrB = parsingToCharArr(b);

        for (int i = 0; i < arrA.length; i++){
            answer += Math.abs(arrA[i] - arrB[i]);
        }
        return answer;
    }

    static int[] parsingToCharArr(String a){
        int[] arr = new int[26];
        char[] chArr = a.toCharArray();
        for(char ch : chArr){
            arr[ch - 'a']++;
        }
        return arr;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
