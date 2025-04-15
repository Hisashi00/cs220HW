

public class TelephoneEntry {
    private int phoneNumber;
    private String name;

    public TelephoneEntry(int phoneNumber, String name){
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    @Override
    public String toString(){
        String text = this.name + ": " + String.valueOf(this.phoneNumber);
        return text;
    }

    public int getNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }
}
