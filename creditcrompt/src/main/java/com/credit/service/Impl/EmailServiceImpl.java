package com.credit.service.Impl;

import com.credit.config.MailConfig;
import com.credit.service.EmailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.MimeUtility;
import javax.mail.search.*;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.List;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private MailConfig mailConfig;

    @Override
    public List<Message> receiveEmail() {


        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";//ssl加密,jdk1.8无法使用


        //有些参数可能不需要
        Properties props = new Properties();
        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.transport.protocol", mailConfig.getProtocol()); // 使用的协议
        props.setProperty("mail.imap.port", mailConfig.getPort());
        props.setProperty("mail.imap.socketFactory.port", mailConfig.getPort());

        // 获取连接
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);

        // 获取Store对象
        Store store = null;
        Folder folder = null;
        try {
            store = session.getStore(mailConfig.getProtocol());
            store.connect(mailConfig.getHost(), mailConfig.getUsername(), mailConfig.getPassword()); // 登陆认证
            // 通过imap协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
            folder = store.getFolder("INBOX");// 获得用户的邮件帐户
            folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限

            int n = folder.getUnreadMessageCount();// 得到未读数量
            System.out.println("unread" + n);

            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false); // false代表未读，true代表已读
            //SearchTerm subject = new SubjectTerm("ABC");//主题筛选
            //FromStringTerm fromStringTerm = new FromStringTerm("287052392@qq.com");
            //SearchTerm searchTerm = new AndTerm(ft,subject);
            //SearchTerm searchTerm = new AndTerm(comparisonAndTerm,ft);

            Message messages[] = folder.search(ft);

            for (Message message : messages) {
                //fdf
                if(message.getSubject().contains("电子账单")){// 获得邮件主题
                    Address from = (Address) message.getFrom()[0];// 获得发送者地址
                    System.out.println("邮件的主题为: " + message.getSubject());// 获得邮件主题
                    System.out.println("发件人地址为: " + decodeText((from.toString())));
                    System.out.println("日期:" + message.getSentDate());
                    //message.setFlag(Flags.Flag.SEEN, false);     //imap读取后邮件状态会变为已读,设为未读
                }
            }

            folder.close(false);// 关闭邮件夹对象
            store.close(); // 关闭连接对象
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected static String decodeText(String text) throws UnsupportedEncodingException {
        if (text == null)
            return null;
        if (text.startsWith("=?GB") || text.startsWith("=?gb"))
            text = MimeUtility.decodeText(text);
        else
            text = new String(text.getBytes("ISO8859_1"));
        return text;
    }
}
