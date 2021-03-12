package exam03retake01;

import java.util.ArrayList;
import java.util.List;

public class CdvCheck {

    public boolean check(String number) {
        List<Integer> digits = new ArrayList<>();
        boolean b = false;

        if (number.length() < 10 || number.length() > 10) {
            throw new IllegalArgumentException("Not valid tax number");
        }
        int sum = getSum(number, digits);
        if (sum % 11 == digits.get(9)){
            b= true;
        }
        return b;
    }

    private int getSum(String number, List<Integer> digits) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            digits.add(Integer.parseInt(number.substring(i, i + 1)));
        }
        for (int j = 0; j < 9; j++) {
            sum += digits.get(j) * (j + 1);
        }
        return sum;
    }
}
