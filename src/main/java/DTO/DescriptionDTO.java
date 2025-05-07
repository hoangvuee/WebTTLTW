package DTO;

public class DescriptionDTO {
    private Long id;

    private String title;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public DescriptionDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public DescriptionDTO() {
    }
}
