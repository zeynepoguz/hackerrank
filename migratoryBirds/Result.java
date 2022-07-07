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

class Result {

    /*
     * Complete the 'migratoryBirds' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int migratoryBirds(List<Integer> arr) {
    
        HashMap<Integer, Integer> birdMap = new HashMap<>();
        
        for(Integer birdId : arr){
            if(!birdMap.containsKey(birdId)){
                birdMap.put(birdId, 0);
            } else {
                Integer count = birdMap.get(birdId);
                count++;
                birdMap.put(birdId, count);
            }
        }
        
        int maxCount = Integer.MIN_VALUE;
        int maxCountBirdId = -1;
        
        for(Map.Entry<Integer,Integer> entry : birdMap.entrySet()){
            if(entry.getValue() > maxCount){
                maxCount = entry.getValue();
                maxCountBirdId = entry.getKey();
            } else if (entry.getValue() == maxCount && entry.getKey() < maxCountBirdId){
                maxCountBirdId = entry.getKey();
            }   
            
        }
        
        
        return maxCountBirdId;
    
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
