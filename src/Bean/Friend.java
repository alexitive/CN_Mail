package Bean;

public class Friend {
    int f_id;
    int id;
    int friend_id;

    @Override
    public String toString() {
        return "Friend{" +
                "f_id=" + f_id +
                ", id=" + id +
                ", friend_id=" + friend_id +
                '}';
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }
}
