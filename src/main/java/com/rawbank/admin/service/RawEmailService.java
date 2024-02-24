package com.rawbank.admin.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.rawbank.admin.model.RawEmailObject;

@Service
public class RawEmailService {

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;
    
    @Value("${mail.username}")
    private String from;
    private static final Logger LOGGER= LoggerFactory.getLogger(RawEmailService.class);

    public RawEmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(RawEmailObject rawEmailObject)  {
        Context context = new Context();
        context.setVariable("rawEmailObject", rawEmailObject);

        try {
			String process = templateEngine.process("emails/emailTemplate", context);
		    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setSubject("Transaction CSC, RRN : " + rawEmailObject.getRr());
			helper.setText(process, true);
			helper.setFrom(from);
			helper.setTo(InternetAddress.parse(rawEmailObject.getSetTo()));
			helper.setCc(InternetAddress.parse(rawEmailObject.getSetCC()));
			javaMailSender.send(mimeMessage);
			LOGGER.info(" email  of  rrn: {}  sent succesfully", rawEmailObject.getRr());
		} catch ( MailException | MessagingException  e) {


			LOGGER.error("", e);
		}
    }
}
