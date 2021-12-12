/**
 * 
 */
package com.orianecare.comingsoonsubscription.service.impl;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.orianecare.comingsoonsubscription.service.EmailSenderSerivce;

/**
 * @author vennelakanti
 *
 */
@Service
public class EmailSenderServiceImpl implements EmailSenderSerivce{
	 @Autowired
	 private JavaMailSender emailSender;

	@Override
	@Async
	public void sendSimpleMessage(String to, String subject, String body){
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("${spring.mail.username}");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(body);
        emailSender.send(message);
      
	}

	@Override
	@Async
	public void sendMessageWithAttachment(String to, String subject, String text, String fileName) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom("${spring.mail.username}");
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);
	        
	    FileSystemResource file 
	      = new FileSystemResource(new ClassPathResource(
	    	      "data/"+fileName).getFile());
	    helper.addAttachment(fileName, file);

	    emailSender.send(message);
		
	}
}
