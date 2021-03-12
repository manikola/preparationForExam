package exam03retake01;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OwlCounter {

    private Map<String, Integer> birds = new HashMap<>();

    public void readFromFile(BufferedReader br){
        try  {
            String line;
            while((line = br.readLine()) != null){
                String[] arr = line.split("=");
                birds.put(arr[0], Integer.parseInt(arr[1]));

            }

        } catch (IOException e) {
            throw new IllegalStateException("Can not read file",e);
        }
    }

    public int getNumberOfOwls(String county){
        return birds.get(county);

}
}
