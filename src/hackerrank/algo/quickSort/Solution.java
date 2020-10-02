package hackerrank.algo.quickSort;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the quickSort function below.
    static int[] quickSort(int[] arr) {
        doit(arr, 0, arr.length-1);
        return arr;
    }

    static void doit(int[] arr, int start, int end){
        int pivot = partition(arr, start, end);
        if(start < pivot - 1){
            doit(arr, start, pivot-1);
        }
        if(pivot < end){
           doit(arr, pivot, end);
        }
    }
    static int partition(int[] arr, int start, int end){
        int base = arr[(start+end)/2];
        while(start <= end){
            while(arr[start] < base){
                start++;
            }
            while(arr[end] > base){
                end--;
            }
            if(start <= end){
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
                start++;
                end--;
            }
        }
        return start;
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

        int[] result = quickSort(arr);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

