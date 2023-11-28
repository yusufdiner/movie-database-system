

public class Documentary extends Film {
    public String releaseDate;

    public Documentary(String filmID, String filmTitle, String language, String director, String runtime, String country, String cast, String releaseDate) {
        super(filmID, filmTitle, language, director, runtime, country, cast);
        this.releaseDate = releaseDate;
    }
    public Documentary(String filmID, int rating) {
        super(filmID, rating);
    }
}
