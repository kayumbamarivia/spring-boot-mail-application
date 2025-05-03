package com.me.mailing.example.dtos;

import org.springframework.web.multipart.MultipartFile;

public record EmailRequest( 
    String to,
    String subject,
    String body,
    MultipartFile attachment) {
}
