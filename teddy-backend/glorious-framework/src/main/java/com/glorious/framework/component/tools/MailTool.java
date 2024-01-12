package com.glorious.framework.component.tools;

import com.glorious.common.properties.GloriousMailProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class MailTool {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private GloriousMailProperty mailProperty;

    /**
     * 发送简单电邮
     */
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(mailProperty.getRecipient());
        message.setText(content);
        message.setSubject(subject);

        sender.send(message);
    }

    /**
     * 发送 HTML
     */
    public void sendHtml(String to, String subject, String html) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperty.getRecipient());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            sender.send(message);
        } catch (Exception e) { }
    }
}
