public class Writer extends Artist {
    public String writingStyle;
    public Writer(String id, String name, String surname, String country, String writingStyle){
        super(id, name, surname, country);
        this.writingStyle=writingStyle;
    }
}
