
public class Film {
    public String filmID;
    public String filmTitle;
    public String language;
    public String runtime;
    public String country;
    public String director;
    public String cast;
    public int rating;

    public Film(String filmID, String filmTitle, String language,String director, String runtime, String country, String cast){
        this.filmID=filmID;
        this.filmTitle= filmTitle;
        this.language= language;
        this.runtime= runtime;
        this.country=country;
        this.director=director;
        this.cast=cast;
    }
    public Film(String filmID, int rating ){
        this.filmID = filmID;
        this.rating = rating;

    }

}
