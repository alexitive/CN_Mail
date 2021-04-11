package Test;

import Bean.Friend;
import Bean.Mail;
import Service.FriendService;
import Service.MailService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Test3 {

    ApplicationContext context =
            new ClassPathXmlApplicationContext("Config/Spring-Config.xml");

    Friend friend = new Friend();

    @Test
    public void test(){
        MailService mailService = context.getBean("mailService", MailService.class);

        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        mailService.deleteMailByManager(ids);

//        List<Mail> allMail = mailService.getAllMail();
//        System.out.println(allMail.toString());

    }


    @Test
    public void test2(){
        MailService mailService = context.getBean("mailService", MailService.class);

        List<String> usernames = new ArrayList<>();
        usernames.add("ding@xxkd.com");
        usernames.add("test2@xxkd.com");
        mailService.groupSendMail(usernames,"cnm");

//        List<Mail> allMail = mailService.getAllMail();
//        System.out.println(allMail.toString());

    }




}
