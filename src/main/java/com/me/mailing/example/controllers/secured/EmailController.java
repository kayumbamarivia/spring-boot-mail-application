package com.me.mailing.example.controllers.secured;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.me.mailing.example.dtos.EmailRequest;
import com.me.mailing.example.services.MailService;

@RestController
// @RequestMapping("/api/v1/email")
@RequestMapping("secured")
public class EmailController {

    private final MailService mailService;
    private final Logger logger = LoggerFactory.getLogger(EmailController.class);
    public EmailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
        @RequestParam(required = true) String to,
        @RequestParam(required = true) String subject,
        @RequestParam(required = true) String body,
        @RequestParam(required = true) MultipartFile attachment,
        @RequestParam(required = false) EmailRequest request) {
        try {
            if (request == null) {
                request = new EmailRequest(to, subject, body, attachment);
            }
            mailService.sendMail(request);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            logger.error("Error sending email: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }
    }

    @GetMapping("/sec")
    public int num() {
        return 1;
    }

}
