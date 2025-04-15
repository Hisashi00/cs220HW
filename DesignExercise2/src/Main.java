import java.util.List;

public class Main {
    public static void main(String[] args) {
        TelephoneBook book = new TelephoneBook();

        //add entry
        book.addEntry(new TelephoneEntry(99999, "Spacco"));
        book.addEntry(new TelephoneEntry(11111, "Leahy"));

        //return name
        List<String> nameList = book.returnName(11111);
        System.out.println(nameList);

        //return mutiple names
        book.addEntry(new TelephoneEntry(11111, "Bram"));
        List<String> nameList2 = book.returnName(11111);
        System.out.println(nameList2);

        //return number
        List<Integer> NumberList = book.returnNumber("Leahy");
        System.out.println(NumberList);

        //return mutiple number
        book.addEntry(new TelephoneEntry(99999, "Leahy"));
        List<Integer> NumberList2 = book.returnNumber("Leahy");
        System.out.println(NumberList2);


        //sort by name
        List<TelephoneEntry> sortedBookByName = book.sortByName();
        System.out.println(sortedBookByName);

        //sort by number
        List<TelephoneEntry> sortedBookByNumber = book.sortByName();
        System.out.println(sortedBookByNumber);
    }
}