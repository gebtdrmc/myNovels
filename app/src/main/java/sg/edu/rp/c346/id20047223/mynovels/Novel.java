package sg.edu.rp.c346.id20047223.mynovels;

import java.io.Serializable;

public class Novel implements Serializable {

    private int id;
    private String title;
    private String author;
    private String genre;
    private String synopsis;
    private int rating;
    private String status;

    public Novel(int id, String title, String author, String genre, String synopsis, int rating, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.synopsis = synopsis;
        this.rating = rating;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  title + "\n" +
                 author + "\n" +
                genre + "\n" + synopsis + "\n" +
                 rating + "\n" + status;
    }
}
