package rmi;

import java.io.Serializable;

public class Message implements Serializable {
    private int groupId;
    private String message;

    public Message(int groupId, String message) {
        this.groupId = groupId;
        this.message = message;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
