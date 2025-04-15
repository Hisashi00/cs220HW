

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class TelephoneBook {

    private List<TelephoneEntry> telephoneBook;

    public TelephoneBook() {
        this.telephoneBook = new ArrayList<>();
    }

    public void addEntry(TelephoneEntry e){
        telephoneBook.add(e);
        System.out.println("successfully added "+e);
    }

    public List<String> returnName(int phoneNumber){
        List<String> nameList = new ArrayList<>();
        for (TelephoneEntry entry : telephoneBook) {
            if (entry.getNumber() == phoneNumber) {
                nameList.add(entry.getName());
            }
        }
        return nameList;
    }

    public List<Integer> returnNumber(String name){
        List<Integer> numberList = new ArrayList<>();
        for (TelephoneEntry entry : telephoneBook) {
            if (entry.getName() == name) {
                numberList.add(entry.getNumber());
            }
        }
        return numberList;
    }

    public List<TelephoneEntry> sortByNumber() {
        List<TelephoneEntry> sorted = new ArrayList<>(telephoneBook);
        Collections.sort(sorted, Comparator.comparing(TelephoneEntry::getNumber));
        return sorted;
    }

    public List<TelephoneEntry> sortByName() {
        List<TelephoneEntry> sorted = new ArrayList<>(telephoneBook);
        Collections.sort(sorted, Comparator.comparing(TelephoneEntry::getName));
        return sorted;
    }
}
