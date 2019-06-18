package com.hong.tools.SE;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
 
/**
 * 项目名称：springmvc_hibernate 
 * 类名称：EmailUtil 
 * 类描述：发送邮件工具类 
 * 创建时间：2016年4月23日 上午9:48:21
 * @version 1.0
 */
public class EmailUtil {
 
    // properties配置文件地址
    //private static final String PROPERTIES_PATH = "standard_data.properties";
 
    /*private static Session session;
    private static Properties props = new Properties();
    private static final String HOST = "smtp.qq.com";
    private static int PORT = 587;
    private static final String isAUTH = "true";
    private static final String FROM = "785427346@qq.com";
 
    private static final String USERNAME = "785427346@qq.com";
    private static final String PASSWORD = "xxxxxxxxxxxxxxxx";
 
    private static final String TIMEOUT = "25000";
    private static final String DEBUG = "true";*/
    private static Session session;
    private static Properties props = new Properties();
    private static final String HOST = "mail.suneee.com";
    private static int PORT = 25;
    private static final String isAUTH = "true";
    private static final String FROM = "nnyr";
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    
    private static final String TIMEOUT = "5000";
    private static final String DEBUG = "true";
    
   
 
    // 初始化session
    static {
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", isAUTH);
        props.put("fromer", FROM);
        props.put("username", USERNAME);
        props.put("password", PASSWORD);
        props.put("mail.smtp.timeout", TIMEOUT);
        props.put("mail.debug", DEBUG);
 
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }
 
    public static void main(String[] args) {
        try {
            String html = "<html><head>"+
                    "</head><body>"+
                    "<span style='font-size: 20px;'>尊敬的用户:</span></br>"+
                    "<span style='font-size:15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢。</span>"+
                    "<span style='font-size:15px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果有任何疑问，请拨打客服电话10086</span>"+
                    "</body></html>";
            
            //sendEmail("1107@qq.com", "yeah", html, true);
 
            //sendFileEmail("1107@qq.com", "pic", html, new File("D:/照片.png"));
            //sendFileEmail("1107@qq.com", "pic", html, new File("D:/照片.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
 
    /**
     * 
     * @Title sendEmail
     * @Description 通过isHtml判断发送的邮件的内容
     * @param to 邮件接收者
     * @param content 邮件内容
     * @param isHtml 是否发送html
     * @throws MessagingException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws EmailException
     */
    public static void sendEmail(String to, String title, String content, boolean isHtml)
            throws FileNotFoundException, IOException, MessagingException {
        String fromer = props.getProperty("fromer");
        if (isHtml) {
            sendHtmlEmail(fromer, to, title, content);
        } else {
            sendTextEmail(fromer, to, title, content);
        }
    }
 
    // 发送纯文字邮件
    public static void sendTextEmail(String from, String to, String subject, String content)
            throws FileNotFoundException, IOException, MessagingException {
 
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(content);
        message.setSentDate(new Date());
        Transport.send(message);
    }
 
    // 发送有HTML格式邮件
    public static void sendHtmlEmail(String from, String to, String subject, String htmlConent)
            throws FileNotFoundException, IOException, MessagingException {
 
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
 
        Multipart multi = new MimeMultipart();
        BodyPart html = new MimeBodyPart();
        html.setContent(htmlConent, "text/html; charset=utf-8");
        multi.addBodyPart(html);
        message.setContent(multi);
        Transport.send(message);
    }
 
    // 发送带附件的邮件
    //htmlConent不能为null
    public static void sendFileEmail(String to, String subject, String htmlConent, File attachment,String nick)
            throws FileNotFoundException, IOException, MessagingException {
 
        Message message = new MimeMessage(session);
        String fromer = props.getProperty("fromer");
        try { 
        	nick = javax.mail.internet.MimeUtility.encodeText(nick);
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } 
        message.setFrom(new InternetAddress(nick+" <"+fromer+">"));
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(htmlConent, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        // 添加附件的内容
        if (attachment != null) {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
 
            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            // messageBodyPart.setFileName("=?GBK?B?" +
            // enc.encode(attachment.getName().getBytes()) + "?=");
            // MimeUtility.encodeWord可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }
 
        message.setContent(multipart);
        Transport.send(message);
    }
 
 
}