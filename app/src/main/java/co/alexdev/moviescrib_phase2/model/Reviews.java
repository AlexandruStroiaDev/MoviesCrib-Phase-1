package co.alexdev.moviescrib_phase2.model;

import com.google.gson.annotations.SerializedName;

/*Reviews class for the movies*/
public class Reviews {

    @SerializedName("id")
    private final String id;
    @SerializedName("author")
    private final String author;
    @SerializedName("content")
    private final String content;
    @SerializedName("url")
    private final String url;

    public Reviews(String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
