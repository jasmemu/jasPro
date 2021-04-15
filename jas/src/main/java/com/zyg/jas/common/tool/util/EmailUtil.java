package com.zyg.jas.common.tool.util;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender sender;

    /**
     *
     * @param emialFrom 发送方的邮箱
     * @param pwd  发送方的邮箱认证码（不是邮箱密码,到qq邮箱获取
     * @param emailTo 接收方的邮箱
     * @param title 邮件标题
     * @param content 邮件内容
     * @throws Exception
     */

    public static void sendMail(String emialFrom,String pwd,String emailTo,String title, String content) throws Exception{

        Properties props = new Properties();
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        // 设置环境信息
        Session session = Session.getInstance(props);
        // 创建邮件对象
        Message msg = new MimeMessage(session);
        msg.setSubject(title);
        // 设置邮件内容
        msg.setText(content);
        // 设置发件人 的名字
        msg.setFrom(new InternetAddress(emialFrom));
        Transport transport = session.getTransport();
        // 连接邮件服务器
        transport.connect(emialFrom, pwd);
        // 发送邮件  。设置收件人
        InternetAddress to = new InternetAddress(emailTo);
        InternetAddress [] to1 = {to};
        transport.sendMessage(msg,to1);
        // 关闭连接
        transport.close();
    }


    public static void main(String[] args){
        try {
            sendMail("2585196949@qq.com","jpcwlepltenyebgh","1944955501@qq.com","jas验证吗", "111555");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

