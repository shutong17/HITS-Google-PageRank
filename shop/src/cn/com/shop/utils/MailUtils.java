package cn.com.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	public static void sendMail(String to, String code){
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props,new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("service@shop.com", "111");
			}
		});
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject("来自购物天堂的官方激活邮件");
			message.setContent("<h1>购物天堂官方激活的邮件！点击下面链接完成激活操作！</h1>"
					+ "<h3><a href='http://192.168.1.20:8080/shop/user_active.action?code="+code+"'>http://192.168.1.20:8080/shop/user_active.action?code="+code+"</a></h3>","text/html;charset=UTF-8");
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	public static void main(String[] args){
		sendMail("aaa@shop.com","11111111");
	}
}
