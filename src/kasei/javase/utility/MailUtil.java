package kasei;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtil {

    /** todo 发送一封邮件 */
    public static void sendOneMail() throws MessagingException, GeneralSecurityException {
        final String toAddress = "****@163.com";
        final String fromAdress = "****@qq.com";
        final String mailServer = "smtp.qq.com";  // QQ 邮件服务器
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // 设置邮件协议
        props.setProperty("mail.smtp.host", mailServer); // 设置系统邮件发送服务器
        props.setProperty("mail.smtp.auth", "true"); // 设置是否需要身份验证

        /** 如果邮件服务器需要 SSL 连接，则需要以下代码 */
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication passwordAuthentication = new PasswordAuthentication("****@qq.com", "yqni****da"); // 发送邮箱地址 和 授权码（不是密码）
                return passwordAuthentication;
            }
        });
        session.setDebug(true);

        /** 生成一个邮件实例 */
        MimeMessage mimeMessage = new MimeMessage(session);
        /** 配置发件人 */
        mimeMessage.setFrom(fromAdress);
        /** 配置收件人 */
        Address[] addresses = new Address[]{new InternetAddress(toAddress)};
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, addresses);
        /** 配置抄送人 */
        Address[] copyToAddresses = new Address[]{new InternetAddress(toAddress)};
        mimeMessage.setRecipients(MimeMessage.RecipientType.CC, copyToAddresses);
        /** 配置密送人 */
        Address[] secretToAddresses = new Address[]{new InternetAddress(toAddress)};
        mimeMessage.setRecipients(MimeMessage.RecipientType.BCC, secretToAddresses);
        /** 配置邮件标题 */
        mimeMessage.setSubject("邮件标题");
        /** 配置邮件正文 */
        mimeMessage.setContent("邮件正文", "text/html;charset=UTF-8");
        /** 发送 */
        Transport.send(mimeMessage);


    }

}
