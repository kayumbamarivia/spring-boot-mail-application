package com.me.mailing.example.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.me.mailing.example.dtos.EmailRequest;

import jakarta.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(EmailRequest emailRequest) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(emailRequest.to());
            helper.setSubject(emailRequest.subject());
            helper.setText(emailRequest.body(), true);
            helper.setFrom(fromEmail);
            helper.setReplyTo(emailRequest.to());
            helper.setSentDate(new Date());

            handlingAttachment(emailRequest, helper);

            mailSender.send(mimeMessage);
            logger.info("Email sent successfully to {}", emailRequest.to());

        } catch (Exception e) {
            logger.error("Failed to send email to {}: {}", emailRequest.to(), e.getMessage());
        }
    }

    private void handlingAttachment(EmailRequest emailRequest, MimeMessageHelper helper){
        try {
            if (emailRequest.attachment() != null && !emailRequest.attachment().isEmpty()) {
                MultipartFile file = emailRequest.attachment();
                String filename = file.getOriginalFilename();
                helper.addAttachment(filename != null ? filename : "report.txt",file::getInputStream);
                logger.info("Attachment added: {}", filename != null ? filename : "report.txt");
            }
        } catch (Exception fileException) {
            logger.warn("Attachment could not be added: {}", fileException.getMessage());
        }
    }
}
