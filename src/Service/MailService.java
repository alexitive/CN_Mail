package Service;

import Bean.Mail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    /**
     * 发邮件的函数
     * @return
     */
    public boolean SendMail(){

        return true;
    }

    /**
     * 根据某个用户的id，首先通过pop3查看是否存在新的邮件，然后将其放入自己的数据库中，最后从数据库中拿出所有的数据返回给客户端
     * @param id
     * @return
     */
    public List<Mail> getMail(int id){

        return null;
    }

    /**
     * 管理员获取所有的邮件，
     * @return
     */
    public List<Mail> getAllMail() {

        return null;
    }

    /**
     * 用户删除一条邮件，实际上是标记其为deleted
     * @return
     */
    public boolean deleteOneMailByUser(){
        return true;
    }

    /**
     * 管理员批量进行删除
     * @return
     */
    public boolean deleteMailByManager(){

        return true;
    }

    /**
     * 群发
     * @param ids
     */
    public void groupSendMail(List<Integer> ids){

    }






}
