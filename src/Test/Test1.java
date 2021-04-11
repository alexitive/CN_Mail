package Test;

import Bean.Mail;
import Dao.FriendMapper;
import Dao.MailMapper;
import Dao.UserMapper;
import Util.MailUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class Test1 {

    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/Spring-Config.xml");
    @org.junit.Test
    public void  test(){

     //   User u = new User();
     //   u.setUser_id(6);
     //   u.setUsername("zn");
     //   u.setUstatus("huijia");

        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
        sqlSession.getMapper(UserMapper.class).deleteUser(6);
     //   User user =  sqlSession.selectOne("Dao.UserMapper.getUserById",1);
     //  System.out.println(user);


    }

    @Test
    public void testUserMapper(){
        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");


     //   User u = new User();
     //   u.setId(5);
      //  u.setUsername("test123");

       // u.setPassword("password123");
        //u.setId("test123@qq.com");



        sqlSession.getMapper(UserMapper.class).deleteUser(1);

    }
    @Test
    public void testFriend(){
        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
        sqlSession.getMapper(FriendMapper.class).deleteFriendCause(1);
    }

    @Test
    public void testMail(){

        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
        Mail m1=new Mail();
        Mail m2=new Mail(); Mail m3=new Mail();
        m1.setId(1);
        m1.setFromEmail("ding@xxkd");
        m1.setToEmail("ding@xxkd");
        m1.setSendTime(new Date());
        m1.setSubjectTitle("hellohahahahahahaha");
        m1.setTextContent("contexthahahahahaha");
        m1.setIsFlag(1);
        m1.setDeleted(1);
        m1.setSeen(1);
        m1.setMailSize(213);
        List<Mail> l = new ArrayList<>();
        l.add(m1);
        l.add(m1);
        sqlSession.getMapper(MailMapper.class).deleteMail(5);
        //System.out.println(l);


    }
    @Test
    public void testReceive(){
        MailUtil mailUtil = new MailUtil();
        mailUtil.receive("ding@xxkd.com","qq1220614922");
    }

    @Test
    public void testSend(){
        MailUtil mailUtil = new MailUtil();
        mailUtil.send("ding@xxkd.com","qq1220614922","21313@qq.com","hello world","sohai");
    }


}
