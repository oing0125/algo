package algospot.etc.ARCTIC_FAIL;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static double[][] POSITIONS;
    private static double[][] DISTANCES;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCnt = Integer.parseInt(br.readLine().trim());
        while(--caseCnt >= 0){
            N = Integer.parseInt(br.readLine().trim());
            POSITIONS = new double[N][2];
            DISTANCES = new double[N][N];
            String[] lineArr;
            for (int i = 0; i < N; i ++){
                lineArr = br.readLine().trim().split(" ");
                POSITIONS[i][0] = Double.parseDouble(lineArr[0]);
                POSITIONS[i][1] = Double.parseDouble(lineArr[1]);
            }
            // 최대거리 계산
            for (int i = 0; i < N-1; i++){
                for (int j = i+1; j < N; j++){
                    DISTANCES[i][j] = DISTANCES[j][i] = getDistance(POSITIONS[i], POSITIONS[j]);
                }
            }

            // 최대거리를 반으로 줄여가면서 확인
            double answer = optimize(1416.00);
            int dap = (int) Math.round(answer * 1000);
            bw.write(String.format("%.2f", ((float)dap) / 1000));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static double getDistance(double[] a, double[] b){
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    public static double optimize(double maxDistance){
        double lo = 0, hi = maxDistance;
        for (int i = 0; i < 200; i++){
            double mid = (lo + hi) / 2;
            if(decision(mid)){
                hi = mid;
            }else{
                lo = mid;
            }
        }
        return lo;
    }

    public static boolean decision(double power){
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        // 항상 시작은 원점부터
        queue.add(0);
        visited[0] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int now = queue.poll();
            cnt++;
            for (int next = 0; next < N; next++){
                if(!visited[next] && DISTANCES[now][next] <= power){
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return cnt == N;
    }
}

/*
2
5
0 0
1 0
1 1
1 2
0 2
6
1.0 1.0
30.91 8
4.0 7.64
21.12 6.0
11.39 3.0
5.31 11.0


1
6
1.0 1.0
30.91 8
4.0 7.64
21.12 6.0
11.39 3.0
5.31 11.0
 */