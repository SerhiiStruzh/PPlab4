package logger.mail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailMessage {
	private static String host = "";
	private static String username = "";
	private static String password = "";
	private static String toAddress = "";
	private static String mainInfoFile = "mailInfo.txt";
	
	private static boolean readMailInfo() {
		String filePath = System.getProperty("user.dir") + "\\" + mainInfoFile;
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			host = reader.readLine();
			username = reader.readLine();
			password = reader.readLine();
			toAddress = reader.readLine();
            return true;
        } catch (IOException e) {}
		return false;
	}
	
	public static void sendMessage(String msg) {
		if(host.isEmpty()) {
			if(!readMailInfo()) {
				return;
			}
		}
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.port", "465");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.ssl.enable", "true");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            message.setSubject("Message Critical Error");
            message.setText(msg);
            Transport.send(message);
        } catch (MessagingException e) {
        }
	}
	
}
