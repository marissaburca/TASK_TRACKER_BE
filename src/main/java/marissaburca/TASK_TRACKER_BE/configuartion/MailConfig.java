package marissaburca.TASK_TRACKER_BE.configuartion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Value;
import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    JavaMailSender getJavaMailSender(@Value ("${spring.mail.host}") String host,
                                     @Value ("${spring.mail.port}") int port,
                                     @Value ("${spring.mail.username}") String email,
                                     @Value ("${spring.mail.password}") String password
    ) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(email);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
