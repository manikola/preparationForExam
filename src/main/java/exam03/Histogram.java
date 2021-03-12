package exam03;

import java.io.BufferedReader;
import java.io.IOException;

public class Histogram {

    public String createHistogram(BufferedReader br){
        StringBuilder sb = new StringBuilder();
        try  {
            String line;
            while((line = br.readLine()) != null){
                int number = Integer.parseInt(line);
                sb.append("*".repeat(number));
                sb.append("\n");

            }
            return sb.toString();

        } catch (IOException e) {
            throw new IllegalStateException("Can not read file",e);
        }
    }



}

