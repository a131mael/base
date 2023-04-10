package br.com.aaf.base.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TesteEnvioEmail {

	public static void main(String[] args) {

		Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.zoho.com");
      //  properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
       // properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug.auth", "true");
        properties.setProperty( "mail.pop3.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() 
        {   @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {   return new PasswordAuthentication("financeiro@tefamel.com","Safeliand_3");
            }
        });
        try 
        {   MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("financeiro@tefamel.com"));
            message.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse("a131mael@gmail.com"));
            message.setSubject("Test Subject");
            message.setText("Test Email Body");
            Transport.send(message);
        } 
        catch (MessagingException e) 
        {   e.printStackTrace();
        }
    }

}
