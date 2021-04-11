package Test;

import Bean.Friend;
import Service.FriendService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {

    ApplicationContext context =
            new ClassPathXmlApplicationContext("Config/Spring-Config.xml");

    Friend friend = new Friend();

    @Test
    public void test(){
        FriendService friendService = context.getBean("friendService", FriendService.class);
        friend.setId(1);
        friend.setFriend_id(2);
        friendService.addFriend(friend);
    }

    @Test
    public void test2(){
        FriendService friendService = context.getBean("friendService", FriendService.class);
        friend.setId(1);
        friend.setFriend_id(2);
        friendService.removeFriend(1,2);
    }


}
