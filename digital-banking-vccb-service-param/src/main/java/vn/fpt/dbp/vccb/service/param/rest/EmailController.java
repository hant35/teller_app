package vn.fpt.dbp.vccb.service.param.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.service.param.service.EmailService;

import java.security.Principal;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class EmailController {

    @Autowired
    EmailService emailService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/mailgreeting/send", method = RequestMethod.POST, produces = "application/json")
    public String sendGreetingMail(Principal principle, @RequestBody User user) throws Exception {
        return emailService.sendEmail(user, "Greeting.vm", "TellerApp Greeting");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/mailresetpassword/send", method = RequestMethod.POST, produces = "application/json")
    public void sendResetPasswordMail(Principal principle, @RequestBody User user) throws Exception {
        emailService.sendEmail(user, "ResetPassword.vm", "TellerApp Reset Password");
    }

}
