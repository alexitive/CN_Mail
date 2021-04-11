package Test;

import Bean.Friend;
import Bean.User;
import Service.FriendService;
import Service.MailService;
import Service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test4 {

    ApplicationContext context =
            new ClassPathXmlApplicationContext("Config/Spring-Config.xml");


    @Test
    public void test(){
        UserService userService = context.getBean("userService", UserService.class);

//        User user = new User("qc", "123", "5440", "qc", "11213", new Date(), 1, 1);
//
//        userService.registUser(user);
        User user = userService.queryUserByUsername("test3@xxkd.com");
        user.setUsername("qc");
        System.out.println(userService.changeUserInformation(user));
    }

    @Test
    public void test2(){
        UserService userService = context.getBean("userService", UserService.class);

        System.out.println(userService.queryAllUser().toString());

    }


    @Test
    public void test3(){
        FriendService friendService = context.getBean("friendService", FriendService.class);

        Friend friend = new Friend();
        friend.setId(3);
        friend.setFriend_id(4);
        System.out.println(friendService.addFriend(friend));

    }


}
