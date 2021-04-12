package Service;

import Dao.FriendMapper;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 邮件服务器服务POP3,SMTP启停的管理
 *      用一个property来进行管理
 */
@Service(value="mailServerService")
public class MailServerService {


    public boolean stopPop3(){
        try {
            String str= FriendMapper.class.getResource("/").getPath()+"Config/MailServer.properties";
            Properties props=new Properties();
            props.load(new FileInputStream(str));
            OutputStream fos = new FileOutputStream(str);
            props.setProperty("OpenPop3", "0");
            props.store(fos, "Update value");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("属性文件更新错误");
            return false;
        }
        return true;
    }
    public boolean startPop3(){
        try {
            String str= FriendMapper.class.getResource("/").getPath()+"Config/MailServer.properties";
            Properties props=new Properties();
            props.load(new FileInputStream(str));
            OutputStream fos = new FileOutputStream(str);
            props.setProperty("OpenPop3", "1");
            props.store(fos, "Update value");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("属性文件更新错误");
            return false;
        }
        return true;
    }
    public boolean stopSmtp(){
        try {
            String str= FriendMapper.class.getResource("/").getPath()+"Config/MailServer.properties";
            Properties props=new Properties();
            props.load(new FileInputStream(str));
            OutputStream fos = new FileOutputStream(str);
            props.setProperty("OpenStmp", "0");
            props.store(fos, "Update value");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("属性文件更新错误");
            return false;
        }
        return true;
    }

    public boolean startSmtp(){
        try {
            String str= FriendMapper.class.getResource("/").getPath()+"Config/MailServer.properties";
            Properties props=new Properties();
            props.load(new FileInputStream(str));
            OutputStream fos = new FileOutputStream(str);
            props.setProperty("OpenStmp", "1");
            props.store(fos, "Update value");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("属性文件更新错误");
            return false;
        }
        return true;
    }
    public int getSmtpOpen(){
        try {
            String str= FriendMapper.class.getResource("/").getPath()+"Config/MailServer.properties";
            System.out.println(str);
            Properties props=new Properties();
            props.load(new FileInputStream(str));
            return  Integer.valueOf(props.getProperty("OpenStmp"));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("属性文件更新错误");
            return 0;
        }
    }

    public int getPop3Open(){
        try {
            String str= FriendMapper.class.getResource("/").getPath()+"Config/MailServer.properties";
            Properties props=new Properties();
            props.load(new FileInputStream(str));
            return  Integer.valueOf(props.getProperty("OpenPop3"));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("属性文件更新错误");
            return 0;
        }
    }
}
