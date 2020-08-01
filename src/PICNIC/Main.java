package PICNIC;

import java.io.*;

public class Main {
    public static int[][] coupleMap;
    public static int[] visited;
    public static int cnt, stuSu;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCnt = Integer.parseInt(br.readLine().trim());
        for (int cs = 0; cs<caseCnt; cs++) {
            String[] line = br.readLine().trim().split(" ");
            stuSu = Integer.parseInt(line[0]);
            int coupleSu = Integer.parseInt(line[1]);
            coupleMap = new int[stuSu][stuSu];
            visited = new int[stuSu];
            cnt = 0;
            String[] coupleCase = br.readLine().trim().split(" ");
            int a, b;
            for (int i=0;i<coupleSu*2;i+=2){
                a = Integer.parseInt(coupleCase[i]);
                b = Integer.parseInt(coupleCase[i+1]);
                coupleMap[a][b] = 1;
                coupleMap[b][a] = 1;
            }
            recurse();
            bw.write(cnt+"");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void recurse(){
        int first = -1;
        for (int i = 0; i<visited.length; i++) {
            if(visited[i] == 0){
                first = i;
                break;
            }
        }
        if(first == -1){
            cnt++;
            return;
        }

        for (int i=first+1; i<stuSu; i++){
            if(visited[i]==0 && coupleMap[first][i] == 1){
                visited[i] = 1;
                visited[first] = 1;
                recurse();
                visited[i] = 0;
                visited[first] = 0;
            }
        }
    }
}
