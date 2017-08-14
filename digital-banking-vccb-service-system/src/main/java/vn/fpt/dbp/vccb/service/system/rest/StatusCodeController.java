package vn.fpt.dbp.vccb.service.system.rest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class StatusCodeController {

}
