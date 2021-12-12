package com.orianecare.comingsoonsubscription.service;

import java.io.IOException;

import javax.mail.MessagingException;

public interface EmailSenderSerivce {
	public void sendSimpleMessage(String to, String subject, String body);
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException, IOException;
}
