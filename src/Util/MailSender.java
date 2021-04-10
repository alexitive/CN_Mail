/**
 * 这个程序实现了基于POP3协议的邮件接收功能
 * */
package Util;

import Bean.Mail;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

@Service
@Scope("prototype")
public class MailSender {

    private Socket socket = null;

    private boolean debug=true;

    /*
    public static void main(String[] args) throws UnknownHostException, IOException {

        String server="127.0.0.1";//POP3服务器地址

        String user="1220614922@xxkd.com";//用户名，填写自己的邮箱用户名

        String password="qq1220614922";//密码，填写自己的密码

        MailSender mailSender=new MailSender(server,110);

        mailSender.recieveMail(user,password);
    }
    */


    /*构造函数*/
    public MailSender(String server,int port) throws UnknownHostException, IOException{
        try{

            socket=new Socket(server,port);//在新建socket的时候就已经与服务器建立了连接

        }catch(Exception e){

            e.printStackTrace();

        }finally{

            System.out.println("建立连接！");
        }
    }


    //接收邮件程序
    public List<Mail> recieveMail(String user,String password){
        List<Mail> list = null;
        try {
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedWriter out=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            user(user,in,out);//输入用户名

            System.out.println("user 命令执行完毕！");

            pass(password,in,out);//输入密码

            System.out.println("pass 命令执行完毕！");

            int MailNum = stat(in,out);

            System.out.println("stat 命令执行完毕！");

          //  list(in,out);
          //  System.out.println("list 命令执行完毕！");

            //在此处，可以通过将邮件通过pop3取到缓存中后，对于每一封邮件，将其存储到mail数据库表中，并且向服务器发送dele命令删除此条邮件
            list = new ArrayList<>();

            for(int i = 1;i<=MailNum;i++)
            {
               // System.out.println("第"+i+"封");

                /*
                        subject:myxulinjie
                        from:test2@xxkd.com
                        to:ding@xxkd.com
                        Content-Type: text/plain;charset="utf-8"

                        1大家好，欢迎来到计算机网络小组
                 */
                String temp = retr(i,in,out);
                String subject = temp.substring( temp.indexOf("subject:")+8, temp.indexOf("from:")-1);
                String from = temp.substring( temp.indexOf("from:")+5, temp.indexOf("to:")-1);
                String to = temp.substring( temp.indexOf("to:")+3, temp.indexOf("Content-Type:")-1);
                String content = temp.substring( temp.indexOf("\"utf-8\"")+9);
                Mail mail = new Mail(0,from,to,new Date(),subject,content,0,0,0,content.length());
                list.add(mail);
                /*
                System.out.println("===============================");
                System.out.println("subject:"+subject);
                System.out.println("from:"+from);
                System.out.println("to:"+to);
                System.out.println("content:"+content);
                 */

            }
            System.out.println("retr 命令执行完毕！");

            for(int i = 1;i<=MailNum;i++ )
                sendServer("DELE "+i,in,out);


            quit(in,out);
            System.out.println("quit 命令执行完毕！");

        }catch(Exception e){

            e.printStackTrace();

            return list;
        }
        for(int i = 0;i<list.size();i++)
            System.out.println(list.get(i));
        return list;
    }

    //得到服务器返回的一行命令
    public String getReturn(BufferedReader in){

        String line="";

        try{
            line=in.readLine();

            if(debug){

                System.out.println("服务器返回状态:"+line);
            }
        }catch(Exception e){

            e.printStackTrace();
        }
        return line;
    }

    //从返回的命令中得到第一个字段,也就是服务器的返回状态码(+OK或者-ERR)
    public String getResult(String line){

        StringTokenizer st=new StringTokenizer(line," ");

        return st.nextToken();
    }

    //发送命令
    private String sendServer(String str,BufferedReader in,BufferedWriter out) throws IOException{

        out.write(str);//发送命令

        out.newLine();//发送空行

        out.flush();//清空缓冲区

        if(debug){

            System.out.println("已发送命令:"+str);
        }
        return getReturn(in);
    }

    //user命令

    public void user(String user,BufferedReader in,BufferedWriter out) throws IOException{

        String result = null;

        result=getResult(getReturn(in));//先检测连接服务器是否已经成功

        if(!"+OK".equals(result)){

            throw new IOException("连接服务器失败!");
        }

        result=getResult(sendServer("user "+user,in,out));//发送user命令

        if(!"+OK".equals(result)){

            throw new IOException("用户名错误!");
        }
    }

    //pass命令
    public void pass(String password,BufferedReader in,BufferedWriter out) throws IOException{

        String result = null;

        result = getResult(sendServer("pass "+password,in,out));

        if(!"+OK".equals(result)){

            throw new IOException("密码错误!");
        }
    }


    //stat命令

    public int stat(BufferedReader in,BufferedWriter out) throws IOException{

        String result = null;

        String line = null;

        int mailNum = 0;

        line=sendServer("stat",in,out);

        StringTokenizer st=new StringTokenizer(line," ");

        result=st.nextToken();

        if(st.hasMoreTokens())

            mailNum=Integer.parseInt(st.nextToken());

        else{

            mailNum=0;

        }

        if(!"+OK".equals(result)){

            throw new IOException("查看邮箱状态出错!");
        }

        System.out.println("共有邮件"+mailNum+"封");
        return mailNum;
    }

    //无参数list命令
    public void list(BufferedReader in,BufferedWriter out) throws IOException{

        String message = "";

        String line = null;

        line=sendServer("list",in,out);

        while(!".".equalsIgnoreCase(line)){

            message=message+line+"\n";

            line=in.readLine().toString();
        }

        System.out.println(message);
    }

    //带参数list命令
    public void list_one(int mailNumber ,BufferedReader in,BufferedWriter out) throws IOException{

        String result = null;

        result = getResult(sendServer("list "+mailNumber,in,out));

        if(!"+OK".equals(result)){

            throw new IOException("list错误!");
        }
    }

    //得到邮件详细信息

    public String getMessagedetail(BufferedReader in) throws UnsupportedEncodingException{

        String message = "";

        String line = null;

        try{
            line=in.readLine().toString();

            while(!".".equalsIgnoreCase(line)){

                message=message+line+"\n";

                line=in.readLine().toString();
            }
        }catch(Exception e){

            e.printStackTrace();
        }

        return message;
    }

    //retr命令
    public String retr(int mailNum,BufferedReader in,BufferedWriter out) throws IOException, InterruptedException{

        String result = null;

        result=getResult(sendServer("retr "+mailNum,in,out));

        if(!"+OK".equals(result)){

            throw new IOException("接收邮件出错!");
        }

       // System.out.println("第"+mailNum+"封");
        String r =getMessagedetail(in);
       // System.out.println(r);
        Thread.sleep(30);
        return r;

    }

    //退出
    public void quit(BufferedReader in,BufferedWriter out) throws IOException{

        String result;

        result=getResult(sendServer("QUIT",in,out));

        if(!"+OK".equals(result)){

            throw new IOException("未能正确退出");
        }
    }

}