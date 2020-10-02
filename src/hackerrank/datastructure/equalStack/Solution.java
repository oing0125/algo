package hackerrank.datastructure.equalStack;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the equalStacks function below.
     */
    static int equalStacks(int[] h1, int[] h2, int[] h3) {
        /*
         * Write your code here.
         */
        int sumH1 = Arrays.stream(h1).sum();
        int sumH2 = Arrays.stream(h2).sum();
        int sumH3 = Arrays.stream(h3).sum();
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> s3 = new Stack<>();
        for (int i = h1.length-1; i >= 0; i--){
            s1.add(h1[i]);
        }
        for (int i = h2.length-1; i >= 0; i--){
            s2.add(h2[i]);
        }
        for (int i = h3.length-1; i >= 0; i--){
            s3.add(h3[i]);
        }
        while (true){
            if(sumH1 == sumH2 && sumH2 == sumH3){
                return sumH1;
            }
            if(sumH1 >= sumH2 && sumH1 >= sumH3){
                sumH1 -= s1.pop();
            }else if(sumH2 >= sumH1 && sumH2 >= sumH3){
                sumH2 -= s2.pop();
            }else if(sumH3 >= sumH1 && sumH3 >= sumH2){
                sumH3 -= s3.pop();
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] n1N2N3 = scanner.nextLine().split(" ");

        int n1 = Integer.parseInt(n1N2N3[0].trim());

        int n2 = Integer.parseInt(n1N2N3[1].trim());

        int n3 = Integer.parseInt(n1N2N3[2].trim());

        int[] h1 = new int[n1];

        String[] h1Items = scanner.nextLine().split(" ");

        for (int h1Itr = 0; h1Itr < n1; h1Itr++) {
            int h1Item = Integer.parseInt(h1Items[h1Itr].trim());
            h1[h1Itr] = h1Item;
        }

        int[] h2 = new int[n2];

        String[] h2Items = scanner.nextLine().split(" ");

        for (int h2Itr = 0; h2Itr < n2; h2Itr++) {
            int h2Item = Integer.parseInt(h2Items[h2Itr].trim());
            h2[h2Itr] = h2Item;
        }

        int[] h3 = new int[n3];

        String[] h3Items = scanner.nextLine().split(" ");

        for (int h3Itr = 0; h3Itr < n3; h3Itr++) {
            int h3Item = Integer.parseInt(h3Items[h3Itr].trim());
            h3[h3Itr] = h3Item;
        }

        int result = equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
