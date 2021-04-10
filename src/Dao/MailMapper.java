package Dao;

import Bean.Friend;
import Bean.Mail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailMapper {
    public List<Mail> selectAllMailById(int id);

    public Mail selectMail(int uid);

    public void insertMail(Mail mail);

    public void insertSomeMail(List<Mail> mail);

    public void deleteMail(int uid);

    //public void updateMail(Mail mail);

}
