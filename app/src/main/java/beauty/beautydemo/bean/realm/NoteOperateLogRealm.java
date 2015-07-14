package beauty.beautydemo.bean.realm;


import io.realm.RealmObject;

/**
 * Created by lgp on 2015/5/25.
 */
public class NoteOperateLogRealm extends RealmObject {
    private int id;
    private int type;
    private long time;
    private NoteRealm note;

    public NoteRealm getNote() {
        return note;
    }

    public void setNote(NoteRealm note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
