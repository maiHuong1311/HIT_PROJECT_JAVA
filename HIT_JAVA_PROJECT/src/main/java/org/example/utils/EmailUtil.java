package org.example.utils;

import jakarta.mail.Message;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Transport;

public class EmailUtil {
    public static boolean sendEmail(String to, String subject, String content) {
        final String from = "maihunw@gmail.com";
        final String password = "ifmtxelqqcekimxk";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getInstance(prop, auth);
        MimeMessage mes = new MimeMessage(session);
        try {
            mes.setFrom(new InternetAddress(from));
            mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            mes.setSubject(subject, "UTF-8");
            mes.setText(content, "UTF-8");
            Transport.send(mes);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
