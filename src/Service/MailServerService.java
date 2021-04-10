package Service;

import org.springframework.stereotype.Service;

/**
 * 邮件服务器服务POP3,SMTP启停的管理
 *      用一个property来进行管理
 */
@Service
public class MailServerService {


    public boolean stopPop3(){

        return true;
    }
    public boolean startPop3(){
        return true;
    }
    public boolean stopSmtp(){
        return true;
    }

    public boolean startSmtp(){
        return true;
    }
}
