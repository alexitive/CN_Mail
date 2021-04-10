package Bean;

import java.util.Date;

public class Mail {
    int uid;
    int id;
    String fromEmail;
    String toEmail;
    Date sendTime;
    String subjectTitle;
    String textContent;
    int isFlag;
    int deleted;
    int seen;
    int mailSize;

    @Override
    public String toString() {
        return "Mail{" +
                "uid=" + uid +
                ", id=" + id +
                ", fromEmail='" + fromEmail + '\'' +
                ", toEmail='" + toEmail + '\'' +
                ", sendTime=" + sendTime +
                ", subjectTitle='" + subjectTitle + '\'' +
                ", textContext='" + textContent + '\'' +
                ", isFlag=" + isFlag +
                ", deleted=" + deleted +
                ", seen=" + seen +
                ", mailSize=" + mailSize +
                '}';
    }

    public Mail() {
    }

    public Mail(int id, String fromEmail, String toEmail, Date sendTime, String subjectTitle, String textContent, int isFlag, int deleted, int seen, int mailSize) {
        this.id = id;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.sendTime = sendTime;
        this.subjectTitle = subjectTitle;
        this.textContent = textContent;
        this.isFlag = isFlag;
        this.deleted = deleted;
        this.seen = seen;
        this.mailSize = mailSize;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }



    public int getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(int isFlag) {
        this.isFlag = isFlag;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getMailSize() {
        return mailSize;
    }

    public void setMailSize(int mailSize) {
        this.mailSize = mailSize;
    }
}
