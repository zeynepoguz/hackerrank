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
     * Complete the 'birthday' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY s
     *  2. INTEGER d
     *  3. INTEGER m
     */

    private static int sum(List<Integer> list){
        int sum = 0;
        for(Integer num : list){
            sum += num;
        }
        return sum;
    }

    // brute force , O(n^2)
    /*public static int birthday(List<Integer> s, int d, int m) {
   
        int numOfWays = 0;
        
        for(int i=0; i<s.size() && i+m <= s.size() ; i++){
            List<Integer> subList = s.subList(i, i+m);
            
            if(d == sum(subList)){
                numOfWays++;
            }
        }

        return numOfWays;
    }*/

    // optimized solution
    public static int birthday(List<Integer> s, int d, int m) {
        int numOfWays = 0;
        int currIndex;
        int remain;
        
        for(int startIndex = 0, endIndex = 0; startIndex < s.size() && endIndex < s.size(); startIndex++){
            currIndex = startIndex;
            endIndex = startIndex + m; 
            remain = d;  
             
            while(currIndex < endIndex){
                remain -= s.get(currIndex);
                currIndex++;
                
                if(remain == 0 && currIndex - startIndex == m){
                    remain = d;
                    numOfWays++;
                    break;
                }
            }
        }

        return numOfWays;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int d = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int result = Result.birthday(s, d, m);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
