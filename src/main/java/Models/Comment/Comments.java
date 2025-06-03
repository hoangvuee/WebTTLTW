package Models.Comment;

import java.util.ArrayList;
import java.util.List;

public class Comments {
    public List<Comment> items;

    public Comments() {
        this.items = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        this.items.add(comment);
    }

    public List<Comment> getItems() {
        return items;
    }

    public void setItems(List<Comment> items) {
        this.items = items;
    }
}
