package algospot.etc.LECTURE;

import java.io.*;
import java.util.Arrays;

public class Main {
    static final boolean IS_DEBUG = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = null;
        int dataCnt = -1;
        if(IS_DEBUG) {
            System.out.println("######################### Start #########################");
        }
        dataCnt = Integer.parseInt(br.readLine());
        if(IS_DEBUG) {
            System.out.println("The number of data : " + dataCnt);
        }
        for(int i=0; i<dataCnt; i++){
            line = br.readLine();
//            execute1(line);
            execute2(line, bw);
        }
        if(IS_DEBUG) {
            System.out.println("######################### End #########################");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void execute1(String line){
        String[] arr = new String[line.length()/2];
        for (int i=0; i<line.length(); i+=2){
            arr[i/2] = line.substring(i,i+2);
        }
        Arrays.sort(arr);
        for (String str: arr) {
            System.out.print(str);
        }
        System.out.println();
    }

    public static void execute2(String line, BufferedWriter bw) throws IOException {
        char[] answer = new char[line.length()];
        char[] arr = line.toCharArray();
        for (int i=0; i<arr.length-1; i+=2){
            for (int j = i+2; j<arr.length; j+=2){
                // 첫 번째 자리부터 비교, 같으면 두 번째 자리 비교
                if(arr[i] > arr[j] || (arr[i] == arr[j] && arr[i+1] > arr[j+1])){
                    char tmp1 = arr[j];
                    char tmp2 = arr[j+1];
                    arr[j] = arr[i];
                    arr[j+1] = arr[i+1];
                    arr[i] = tmp1;
                    arr[i+1] = tmp2;
                }
            }
            answer[i] = arr[i];
            answer[i+1] = arr[i+1];
        }
        bw.write(new String(answer));
        bw.newLine();
    }
}
