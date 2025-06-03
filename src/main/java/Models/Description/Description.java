package Models.Description;

public class Description {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Description{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    // getters & setters
}
