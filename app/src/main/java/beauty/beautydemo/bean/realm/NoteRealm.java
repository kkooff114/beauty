package beauty.beautydemo.bean.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by LJW on 15/6/27.
 */
public class NoteRealm extends RealmObject {
    private int id;
    private int type;
    private String label;
    private String content;
    private long lastOprTime;
    private RealmList<NoteOperateLogRealm> logs;

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

    public RealmList<NoteOperateLogRealm> getLogs() {
        return logs;
    }

    public void setLogs(RealmList<NoteOperateLogRealm> logs) {
        this.logs = logs;
    }
}
