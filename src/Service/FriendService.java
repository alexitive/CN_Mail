package Service;

import Bean.Friend;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    /**
     * 对单个朋友的增加
     * @param friend
     * @return
     */
    public boolean addFriend(Friend friend){
        return true;
    }

    /**
     * 删除一个朋友关系
     * @param f_id
     * @return
     */
    public boolean removeFriend(int f_id){
        return true;
    }


}
