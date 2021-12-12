/**
 * 
 */
package com.orianecare.comingsoonsubscription.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.orianecare.comingsoonsubscription.service.impl.EmailSenderServiceImpl;

/**
 * @author vennelakanti
 *
 */
@ExtendWith(MockitoExtension.class)
class EmailSenderServiceImplTest {
	@InjectMocks
	private EmailSenderServiceImpl emailServiceSender;
	@Mock
	private JavaMailSender javaMailSender;
	
	@Mock
	private MimeMessage mimeMessage;

	/**
	 * Test method for {@link com.orianecare.comingsoonsubscription.service.impl.EmailSenderServiceImpl#sendSimpleMessage(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testSendSimpleMessage() {
		
		doNothing().when(javaMailSender).send(Mockito.any(SimpleMailMessage.class));
		emailServiceSender.sendSimpleMessage("abc@example.com", "unit testing", "email body");
		verify(javaMailSender).send(Mockito.any(SimpleMailMessage.class));
	}

	/**
	 * Test method for {@link com.orianecare.comingsoonsubscription.service.impl.EmailSenderServiceImpl#sendMessageWithAttachment(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	@Test
	void testSendMessageWithAttachment() throws MessagingException, IOException {
		when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
		doNothing().when(javaMailSender).send(Mockito.any(MimeMessage.class));
		emailServiceSender.sendMessageWithAttachment("abc@example.com", "unit testing", "email body","dummy.txt");
		verify(javaMailSender).send(Mockito.any(MimeMessage.class));
		
	}

}
