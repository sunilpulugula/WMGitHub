package com.wm.github;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Sunil Kumar
 * Date: 31/3/14
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */

public class SendMail {
    public void sendMail(String m_from,String m_to,String m_subject,String m_body){
        try {
            Session m_Session;
            Message m_simpleMessage;
            InternetAddress m_fromAddress;
            InternetAddress m_toAddress;
            Properties m_properties;

            m_properties     = new Properties();
            m_properties.put("mail.smtp.host", "smtp.gmail.com");
            m_properties.put("mail.smtp.socketFactory.port", "465");
            m_properties.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            m_properties.put("mail.smtp.auth", "true");
            m_properties.put("mail.smtp.port", "465");

            m_Session=Session.getDefaultInstance(m_properties,new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("psunil1278@gmail.com","wavemaker_243809"); // username and the password
                }
            });

            m_simpleMessage  =   new MimeMessage(m_Session);
            m_fromAddress    =   new InternetAddress(m_from);
            m_toAddress      =   new InternetAddress(m_to);
            m_simpleMessage.setFrom(m_fromAddress);
            m_simpleMessage.setRecipient(Message.RecipientType.TO, m_toAddress);
            m_simpleMessage.setSubject(m_subject);

            m_simpleMessage.setContent(m_body, "text/html");
            //m_simpleMessage.setContent(m_body,"text/plain");

            Transport.send(m_simpleMessage);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}