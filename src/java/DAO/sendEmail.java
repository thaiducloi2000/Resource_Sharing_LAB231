/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserDTO;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendEmail {

    public String getRandom() {
	Random rnd = new Random();
	int number = rnd.nextInt(999999);
	return String.format("%06d", number);
    }

    //send email to the user email
    public boolean sendEmail(UserDTO user, String code) {
	boolean test = false;

	String toEmail = user.getGmail();
	final String fromEmail = "thaiducloi2000@gmail.com";
	final String password = "0903682409";

	try {

	    // your host email smtp server details
	    Properties pr = new Properties();
	    pr.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
	    pr.put("mail.smtp.port", "587"); //TLS Port
	    pr.put("mail.smtp.auth", "true"); //enable authentication
	    pr.put("mail.smtp.starttls.enable", "true");
	    pr.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	    //get session to authenticate the host email address and password
	    Authenticator auth = new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(fromEmail, password);
		}
	    };

	    Session session = Session.getInstance(pr, auth);

	    //set email message details
	    Message mess = new MimeMessage(session);

	    //set from email address
	    mess.setFrom(new InternetAddress(fromEmail));
	    //set to email address or destination email address
	    mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

	    //set email subject
	    mess.setSubject("User Email Verification");

	    //set message text
	    mess.setText("Registered successfully.Please verify your account using this code: " + code);
	    //send the message
	    Transport.send(mess);

	    test = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return test;
    }
}
