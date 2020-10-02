package hackerrank.java.stackMaxElement;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Node> stack = new Stack<>();
        for (int i=0; i<n; i++){
            int type = sc.nextInt();
            switch (type) {
                case 1:
                    int value = sc.nextInt();
                    if(stack.empty()){
                        stack.push(new Node(value, value));
                    }else{
                        stack.push(new Node(value, Math.max(value, stack.peek().curMax)));
                    }
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    System.out.println(stack.peek().curMax);
                    break;
            }
        }
        sc.close();
    }
}

class Node{
    int value;
    int curMax;

    public Node(int value, int curMax){
        this.value = value;
        this.curMax = curMax;
    }
}