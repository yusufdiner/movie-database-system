public class FeatureFilm extends Film{
    public String releaseDate;
    public String budget;
    public String writers;
    public String genre;
    private int rate;
    public FeatureFilm(String filmID, String filmTitle, String language,String director, String runtime, String country,  String cast,String genre,String releaseDate,String writers,String budget){
        super(filmID, filmTitle, language, director, runtime, country, cast);
        this.budget=budget;
        this.genre=genre;
        this.releaseDate=releaseDate;
        this.writers=writers;
    }
}
