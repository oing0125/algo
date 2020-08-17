package 분할정복_QUADTREE;

import java.io.*;

public class Main {
    static char[] input;
    static int current_pos;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseSu = Integer.parseInt(br.readLine().trim());
        for(int cs = 0; cs < caseSu; cs++){
            input = br.readLine().trim().toCharArray();
            current_pos = 0;
            bw.write(recurse());
            bw.newLine();
        }
        bw.flush();
    }

    static String recurse(){
        String str = "";
        char c = input[current_pos++];

        if(c == 'b' || c == 'w'){
            return c+"";
        }

        String upperLeft = recurse();
        String upperRight = recurse();
        String lowerLeft = recurse();
        String lowerRight = recurse();
        return "x" + lowerLeft + lowerRight + upperLeft + upperRight;

    }
}


/*

xbwwb

bwwb

xbwxwbbwb

xxwww bxwxw bbbww xxxww bbbww wwbb

1사분면
 */