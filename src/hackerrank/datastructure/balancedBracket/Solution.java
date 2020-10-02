package hackerrank.datastructure.balancedBracket;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++){
            if(arr[i] == '{' || arr[i] == '(' || arr[i] == '['){
                stack.add(arr[i]);
            }else{
                if(stack.isEmpty() || !isMatchedBracket(stack.pop(), arr[i])){
                    return "NO";
                }
            }
        }
        if(!stack.isEmpty()){
            return "NO";
        }else{
            return "YES";
        }
    }

    static boolean isMatchedBracket(char start, char end){
        char[] starts = new char[]{'{', '(', '['};
        char[] ends = new char[]{'}', ')', ']'};
        for (int i = 0; i < starts.length; i++){
            if(start == starts[i]){
                return end == ends[i];
            }
        }
        return false;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
