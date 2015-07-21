package beauty.beautydemo.entity;

import beauty.beautydemo.bean.realm.NoteRealm;

/**
 * Created by LJW on 15/7/18.
 */
public class NoteEntity{
    public String label;
    public String content;
    public long lastOprTime;
    public String image;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLastOprTime() {
        return lastOprTime;
    }

    public void setLastOprTime(long lastOprTime) {
        this.lastOprTime = lastOprTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
