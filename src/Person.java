public class Person {
    public String id;
    public String name;
    public String surname;
    public String country;
    public Person(String id,String name,String surname,String country){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.country=country;
    }

    public Person(String id) {
        this.id=id;
    }
}
