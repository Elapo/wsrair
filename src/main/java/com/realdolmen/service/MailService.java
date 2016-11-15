package com.realdolmen.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.realdolmen.domain.Booking;

public class MailService {
	final String username = "rair2k16@gmail.com";
	final String password = "realdolmen123";
	Properties props;

	public MailService() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}

	public boolean mailBooking(String to, String subject, Booking b) {
		String body = "Thanks for your booking! You can print your ticket here: http://localhost:8080/rair/filtered/regular/secure.xhtml?bookingId="
				+ b.getId();
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message mess = new MimeMessage(session);
			mess.setFrom(new InternetAddress(username));
			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

			MimeMultipart multipart = new MimeMultipart();

			mess.setSubject(subject);
			BodyPart messBodyPart = new MimeBodyPart();
			messBodyPart.setContent(body, "text/html");

			multipart.addBodyPart(messBodyPart);

			mess.setContent(multipart);

			Transport.send(mess);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
