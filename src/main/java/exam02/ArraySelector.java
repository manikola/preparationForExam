package exam02;

public class ArraySelector {

    public String selectEvens(int[] numbers){
        StringBuilder sb = new StringBuilder();
        if(numbers.length ==0){
            return "";
        }
        sb.append("[");
        for(int i=0; i< numbers.length;i+=2){
            sb.append(numbers[i]);
            sb.append(", ");
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append("]");
        return sb.toString();

    }
}
