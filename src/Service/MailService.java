package Service;

import Bean.Mail;
import Bean.User;
import Dao.MailMapper;
import Dao.UserMapper;
import Util.MailUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MailService {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/Spring-Config.xml");

    @Autowired
     MailServerService mailServerService = new MailServerService();
    /**
     * 发邮件的函数
     * @return
     */
    public boolean SendMail(String SendUser,String ReceiveUser,String subject,String text){

        try {
            MailUtil mailUtil = new MailUtil();

            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            User user = sqlSession.getMapper(UserMapper.class).selectUserByUsername(SendUser);
            //发送用户必须存在，且具有发送权限
            if (user != null && (user.getAuthor() == 1 || user.getAuthor() == 3) && mailServerService.getSmtpOpen()==1)
                mailUtil.send(user.getUsername(), user.getPassword(), ReceiveUser, subject, text);
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据某个用户的id，首先通过pop3查看是否存在新的邮件，然后将其放入自己的数据库中，最后从数据库中拿出所有的数据返回给客户端
     * @param username
     * @return
     */
    public List<Mail> getMail(String username){
        List<Mail> mails = null;
        try {
            MailUtil mailUtil = new MailUtil();

            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            User user = sqlSession.getMapper(UserMapper.class).selectUserByUsername(username);
            MailMapper mailMapper = sqlSession.getMapper(MailMapper.class);
            List<Mail> newMails = null ;
            //发送用户必须存在，且具有接受权限
            if (user != null && (user.getAuthor() >=2 ) && mailServerService.getPop3Open()==1) {
                newMails = mailUtil.receive(user.getUsername(), user.getPassword());
                if(newMails != null && newMails.size() > 0)
                    mailMapper.insertSomeMail(newMails);
            }
            mails = mailMapper.selectAllMailByUsername(user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return mails;
    }

    /**
     * 管理员获取所有的邮件，
     * @return
     */
    public List<Mail> getAllMail() {
        List<Mail> result = null;
        try {
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            result = sqlSession.getMapper(MailMapper.class).selectAllMail();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * 用户删除一条邮件，实际上是标记其为deleted=1
     * @return
     */
    public boolean deleteOneMailByUser(int uid){

        try {
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            MailMapper mailMapper = sqlSession.getMapper(MailMapper.class);
            Mail mail = mailMapper.selectMail(uid);
            //设置已经删除
            mail.setDeleted(1);
            mailMapper.updateMail(mail);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * 用户已经查看一条邮件，实际上是标记其为seen =1
     * @return
     */
    public boolean seenOneMailByUser(int uid){

        try {
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            MailMapper mailMapper = sqlSession.getMapper(MailMapper.class);
            Mail mail = mailMapper.selectMail(uid);
            //设置已经查看
            mail.setSeen(1);
            mailMapper.updateMail(mail);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * 用户标记一条邮件，实际上是标记其为isFlag=1
     * @return
     */
    public boolean flagOneMailByUser(int uid){

        try {
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            MailMapper mailMapper = sqlSession.getMapper(MailMapper.class);
            Mail mail = mailMapper.selectMail(uid);
            //设置已经查看
            mail.setIsFlag(1);
            mailMapper.updateMail(mail);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * 管理员批量进行删除
     * @return
     */
    public boolean deleteMailByManager(List<Integer> ids){

        try{
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            sqlSession.getMapper(MailMapper.class).deleteSomeMail(ids);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 群发
     * @param usernames
     * @param content
     */
    public void groupSendMail(List<String> usernames,String content){
        try{

            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


            List<Mail> needIn = new ArrayList<>();
            for(int i = 0;i<usernames.size();i++){
                User user = userMapper.selectUserByUsername(usernames.get(i));
                needIn.add(new Mail(0,"Manager@xxkd.com",user.getUsername(),new Date(),"Notice",content,0,0,0,content.length()));
            }
            sqlSession.getMapper(MailMapper.class).insertSomeMail(needIn);

        }catch(Exception e){
            e.printStackTrace();
        }
    }






}
