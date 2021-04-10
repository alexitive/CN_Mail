package Dao;

import Bean.Friend;
import Bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendMapper {



    public Friend selectFriend(int f_id);

    public void insertFriend(Friend friend);

    public void deleteFriend(@Param("id")int id, @Param("friend_id")int friend_id);

    public void deleteFriendCause(int id);

   // public void updateFriend(Friend friend);
}
