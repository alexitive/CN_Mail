package Service;

import Bean.Friend;
import Bean.Mail;
import Bean.User;
import Dao.FriendMapper;
import Dao.MailMapper;
import Dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/Spring-Config.xml");
    /**
     * 对单个朋友的增加
     * @param friend
     * @return
     */
    public boolean addFriend(Friend friend){

        try {
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            User user = sqlSession.getMapper(UserMapper.class).selectUserById(friend.getFriend_id());
            if(user == null) return false;
            FriendMapper friendMapper = sqlSession.getMapper(FriendMapper.class);
            Friend temp = friendMapper.selectFriendByIdandFriID(friend.getId(), friend.getFriend_id());
            if(temp != null) return false;
            friendMapper.insertFriend(friend);
        }catch(Exception e){
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    /**
     * 删除一个朋友关系
     * @param id 用户id
     * @param friend_id 朋友id
     * @return
     */
    public boolean removeFriend(int id,int friend_id){

        try {
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
//            Friend friend = sqlSession.getMapper(FriendMapper.class).selectFriend(f_id);
            sqlSession.getMapper(FriendMapper.class).deleteFriend(id,friend_id);
        }catch(Exception e){
            return false;
        }
        return true;
    }


}
