package exam03retake01;

import java.util.ArrayList;
import java.util.List;

public class MailBox {

    private List<Mail> mails = new ArrayList<>();

    public List<Mail> getMails() {
        return mails;
    }

    public void addMail(Mail m){
        mails.add(m);
    }

    public List<Mail> findByCriteria(String criteria) {
        List<Mail> results = new ArrayList<>();
        if (criteria.contains(":")) {
            String[] arr = criteria.split(":");
            String direction = arr[0];
            String person = arr[1];
            checkFromTo(results,direction,person);
        }else{
            checkSubject(criteria, results);

        }
        return results;
    }



    private void checkSubject(String criteria, List<Mail> results) {
        for (Mail item : mails) {
            if (item.getMessage().contains(criteria) || item.getSubject().contains(criteria)) {
                results.add(item);
            }
        }
    }

    private void checkFromTo(List<Mail> results, String direction,String person) {
        for (Mail item : mails) {
            if (direction.equals("from")) {
                if (item.getFrom().getEmail().equals(person) || item.getFrom().getName().equals(person)) {
                    results.add(item);
                }
            }
            if (direction.equals("to")) {
                for(Contact i : item.getTo())
                    if (i.getEmail().equals(person) || i.getName().equals(person)) {
                        results.add(item);
                    }
            }
        }
    }
}
