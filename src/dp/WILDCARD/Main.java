package dp.WILDCARD;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static String WILDCARD;
    public static String WORD;
    public static boolean[][] CACHED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCnt = Integer.parseInt(br.readLine().trim());
        for (int cs = 0; cs < caseCnt; cs++){
            WILDCARD = br.readLine().trim();
            int wordCnt = Integer.parseInt(br.readLine().trim());
            List<String> matchedList = new ArrayList<String>();
            for (int i = 0; i < wordCnt; i++){
                WORD = br.readLine().trim();
                CACHED = new boolean[101][101];
                // 1. 메모이제이션 없이 단순 재귀
//                if(recurse1(WILDCARD, WORD)){
//                    matchedList.add(word);
//                }
                // 2. 메모이제이션
                if(recurse2(0, 0)){
                    matchedList.add(WORD);
                }
            }
            Collections.sort(matchedList);
            for (String matchedWord: matchedList) {
                bw.write(matchedWord);
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean recurse1(String wildCard, String word){
        int pos = 0;

        while(pos < word.length() && pos < wildCard.length() && (wildCard.charAt(pos) == word.charAt(pos)
            || wildCard.charAt(pos) == '?')){
            pos++;
        }

        if(pos == wildCard.length()){
            return pos == word.length();
        }

        if(wildCard.charAt(pos) == '*'){
            for (int skip = 0; pos + skip <= word.length(); skip++){
                if(recurse1(wildCard.substring(pos+1), word.substring(pos+skip))){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean recurse2(int posWildcard, int posWord){

        if(CACHED[posWildcard][posWord]){
            return true;
        }
        while(posWord < WORD.length() && posWildcard < WILDCARD.length() && (WILDCARD.charAt(posWildcard) == WORD.charAt(posWord)
                || WILDCARD.charAt(posWildcard) == '?')){
            posWildcard++;
            posWord++;
        }

        if(posWildcard == WILDCARD.length()){
            CACHED[posWildcard][posWord] = true;
            return posWord == WORD.length();
        }

        if(WILDCARD.charAt(posWildcard) == '*'){
            for (int skip = 0; posWord + skip <= WORD.length(); skip++){
                if(recurse2(posWildcard+1, posWord+skip)){
                    return true;
                }
            }
        }
        return false;
    }
}
