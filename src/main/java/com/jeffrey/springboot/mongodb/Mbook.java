package com.jeffrey.springboot.mongodb;


import org.springframework.data.mongodb.core.mapping.Document;

@Document("book")
public class Mbook {

//    @Id
    private long myId;

    private String title;

    private String author;

    public Mbook() {
        super();
    }

    public Mbook(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    public long getMyId() {
        return myId;
    }

    public void setMyId(long myId) {
        this.myId = myId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + (int) (myId ^ (myId >>> 32));
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mbook other = (Mbook) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (myId != other.myId)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Book [id=" + myId + ", title=" + title + ", author=" + author + "]";
    }

}
