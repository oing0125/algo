package hackerrank.java.visitorPattern;

import sun.misc.Perf;

import java.io.*;
import java.util.*;
interface PerformOperation {
    boolean check(int a);
}
class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }
    public PerformOperation isOdd(){
        return n -> (n%2) != 0;
    }
    public PerformOperation isPrime(){
        return n -> checkPrime(n);
    }
    public PerformOperation isPalindrome(){
        return n -> (n+"").equals(reverseStr(n+""));
    }

    public boolean checkPrime(int num){
        if(num == 1){
            return true;
        }
        for (int i = 2; i <= num / 2; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    public String reverseStr(String str){
        char[] oriArr = str.toCharArray();
        char[] newArr = new char[str.length()];
        for (int i=0; i<oriArr.length; i++){
            newArr[i] = oriArr[oriArr.length-1-i];
        }
        return new String(newArr);
    }

}
// Write your code here


public class Solution {

    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T--> 0) {
            String s = br.readLine().trim();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }
}
