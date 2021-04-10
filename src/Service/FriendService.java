package Service;

import Bean.Friend;
import Bean.Mail;
import Dao.FriendMapper;
import Dao.MailMapper;
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
            sqlSession.getMapper(FriendMapper.class).insertFriend(friend);
        }catch(Exception e){
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    /**
     * 删除一个朋友关系
     * @param f_id
     * @return
     */
    public boolean removeFriend(int f_id){

        try {
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            Friend friend = sqlSession.getMapper(FriendMapper.class).selectFriend(f_id);
            sqlSession.getMapper(FriendMapper.class).deleteFriend(friend.getId(),friend.getFriend_id());
        }catch(Exception e){
            return false;
        }
        return true;
    }


}
