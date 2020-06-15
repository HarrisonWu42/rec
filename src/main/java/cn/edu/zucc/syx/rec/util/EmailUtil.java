package cn.edu.zucc.syx.rec.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
   public String sendEmail(String addressee) throws EmailException {
       HtmlEmail email = new HtmlEmail();
       email.setHostName("smtp.qq.com");    // SMTP服务器
       email.setSSLOnConnect(true);
       email.setSslSmtpPort("465");
       email.setCharset("utf-8");           // 字符集
       email.addTo(addressee);              // 收件人
       email.setFrom("303235738@qq.com","COCO音乐");     //发送人的邮箱为自己的，用户名可以随便填
       email.setAuthentication("303235738@qq.com","lxcxrhzgmpxxbjha");  //设置发送人到的邮箱和用户名和授权码
       String code = Tool.getRandomString(6);
       String msg = "尊敬的用户：\n  您好！您正在注册COCO音乐，您的验证码是\n" + "\t\t\t" + code + "\n  希望您使用愉快！";
       email.setSubject("COCO音乐验证");      //发送主题
       email.setMsg(msg);           //发送内容
       email.send();

       return code;
   }

}
