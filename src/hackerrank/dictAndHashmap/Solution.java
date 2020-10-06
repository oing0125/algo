package hackerrank.dictAndHashmap;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    static Map<Integer, Integer> baseMap;
    static Map<Integer, Integer> cntMap;

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> outputList = new ArrayList<Integer>();
        baseMap = new HashMap<Integer, Integer>();
        cntMap = new HashMap<Integer, Integer>();
        for (List<Integer> input : queries){
            int typ = input.get(0);
            int num = input.get(1);
            if(typ == 1){
                plusOneBaseMap(num);
            }else if(typ == 2){
                minusOneBaseMap(num);
            }else if(typ == 3){
                outputList.add((cntMap.containsKey(num)) ? 1 : 0);
            }
        }
        return outputList;
    }

    static void plusOneBaseMap(int key){
        int cnt = 1;
        if(baseMap.containsKey(key)){
            int beforeCnt = baseMap.get(key);
            minusOneCntMap(beforeCnt);
            cnt = beforeCnt + 1;
        }
        baseMap.put(key, cnt);
        plusOneCntMap(cnt);
    }

    static void minusOneBaseMap(int key){
        if(baseMap.containsKey(key)){
            int beforeCnt = baseMap.get(key);
            int cnt = beforeCnt - 1;
            minusOneCntMap(beforeCnt);
            if(cnt == 0){
                baseMap.remove(key);
            }else{
                baseMap.put(key, cnt);
                plusOneCntMap(cnt);
            }
        }
    }

    static void plusOneCntMap(int key){
        int cnt = (cntMap.containsKey(key)) ? cntMap.get(key) + 1 : 1;
        cntMap.put(key, cnt);
    }

    static void minusOneCntMap(int key){
        if(cntMap.containsKey(key)){
            int cnt = cntMap.get(key) - 1;
            if(cnt == 0){
                cntMap.remove(key);
            }else{
                cntMap.put(key, cnt);
            }
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
