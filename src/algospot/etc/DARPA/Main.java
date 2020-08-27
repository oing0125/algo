package algospot.etc.DARPA;

import java.io.*;

public class Main {

    private static int N, M;
    private static double[] LOCATIONS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseSu = Integer.parseInt(br.readLine().trim());

        while(--caseSu >= 0){
            String line = br.readLine().trim();
            String[] lineArr = line.split(" ");
            N = Integer.parseInt(lineArr[0]);
            M = Integer.parseInt(lineArr[1]);
            LOCATIONS = new double[M];
            lineArr = br.readLine().trim().split(" ");
            for (int i = 0; i < M; i++){
                LOCATIONS[i] = Double.parseDouble(lineArr[i]);
            }

            double answer = optimize(LOCATIONS, N);
            float dap = Math.round((int)(answer*1000));
            bw.write(String.format("%.2f", dap / 1000));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static double optimize(double[] location, int cameraCnt){
        double lo = 0, hi = 240;
        for (int i = 0; i < 100; i++){
            double mid = (lo + hi) / 2;
            if(decision(location, cameraCnt, mid)){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        return lo;
    }

    public static boolean decision(double[] location, int cameraCnt, double gap){
        double limit = -1;
        int installed = 0;
        for (int i = 0; i < location.length; i++){
            if(limit <= location[i]){
                installed++;
                limit = location[i] + gap;
            }
        }
        return installed >= cameraCnt;
    }
}
