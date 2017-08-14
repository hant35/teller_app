package vn.fpt.dbp.vccb.service.param.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import vn.fpt.dbp.vccb.core.domain.user.User;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    VelocityEngine velocityEngine;

    public String sendEmail(User user, String template, String subject) throws Exception {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper( mimeMessage, true);

            mimeMessageHelper.setFrom("nghiapv5@fpt.com.vn");
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject(subject);

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("user", user);
            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template,"UTF-8", model);
            mimeMessageHelper.setText(text, true);

            javaMailSender.send(mimeMessage);
            return "{message : OK}";
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
