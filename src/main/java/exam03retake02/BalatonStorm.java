package exam03retake02;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.Collator;
import java.util.*;

public class BalatonStorm {



    public List<String> getStationsInStorm(BufferedReader br) {
        List<String> resultList = new ArrayList<>();
        String station = null;
        try {
            String line;
            while ((line = br.readLine()) != null) {
                if( line.contains("allomas")) {
                    String[] arr = line.split(": ");
                     station = arr[1].substring(1, arr[1].length() - 2);
                }
                    if (line.contains("level\": 3")) {
                        resultList.add(station);
                    }


            } resultList.sort(Collator.getInstance(new Locale("hu", "HU")));

        } catch (IOException e) {
            throw new IllegalStateException("Can not read file", e);
        }
        return resultList;



    }

}
