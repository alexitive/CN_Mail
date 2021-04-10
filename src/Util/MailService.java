package Util;


import Util.Base64Utile_cc;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.PrintWriter;
        import java.net.Socket;


public class MailService {

    public static void main(String[] args) {
        /*
         *用户名和密码
         */
     //   String SendUser="djx1220614922@163.com";
      //  String SendPassword="QCMZIHOARQCJMQZE";
        String SendUser="test2@xxkd.com";
       String SendPassword="test2";
        String ReceiveUser="1220614922@xxkd.com";

        /*
         *对用户名和密码进行Base64编码
         */
        String UserBase64= Base64Utile_cc.EncodeBase64(SendUser.getBytes());
        String PasswordBase64=Base64Utile_cc.EncodeBase64(SendPassword.getBytes());

        try {
            /*
             *远程连接smtp.163.com服务器的25号端口
             *并定义输入流和输出流(输入流读取服务器返回的信息、输出流向服务器发送相应的信息)
             */
           // Socket socket=new Socket("smtp.163.com", 25);
            Socket socket=new Socket("127.0.0.1", 25);
            InputStream inputStream=socket.getInputStream();//读取服务器返回信息的流
            InputStreamReader isr=new InputStreamReader(inputStream);//字节解码为字符
            BufferedReader br=new BufferedReader(isr);//字符缓冲

            OutputStream outputStream=socket.getOutputStream();//向服务器发送相应信息
            PrintWriter pw=new PrintWriter(outputStream, true);//true代表自带flush
            System.out.println(br.readLine());

            /*
             *向服务器发送信息以及返回其相应结果
             */

            //helo
            pw.println("helo boy");
            System.out.println(br.readLine());


            //auth login
            pw.println("auth login");
            System.out.println(br.readLine());
            pw.println(UserBase64);
            System.out.println(br.readLine());
            pw.println(PasswordBase64);
            System.out.println(br.readLine());

            //Set "mail from" and  "rect to"
            //pw.println("mail from:<"+SendUser+">");

            pw.println("mail from:<"+SendUser+">");


            System.out.println(br.readLine());
            pw.println("rcpt to:<"+ReceiveUser+">");
            System.out.println(br.readLine());

            //Set "data"
            pw.println("data");
            System.out.println(br.readLine());

            //正文主体(包括标题,发送方,接收方,内容,点)

            //标题
            pw.println("subject:myxulinjie");
            pw.println("from:"+SendUser);
            pw.println("to:"+ReceiveUser);
            pw.println("Content-Type: text/plain;charset=\"utf-8\"");//设置编码格式可发送中文内容
            pw.println();
            //内容
            pw.println("1大家好，欢迎来到计算机网络小组");
            pw.println(".");
            pw.print("");
            System.out.println(br.readLine());

            /*
             *发送完毕,中断与服务器连接
             */
            pw.println("rset");
            System.out.println(br.readLine());
            pw.println("quit");
            System.out.println(br.readLine());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

