package vn.fpt.dbp.vccb.service.reference.rest;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.product.AutoBlockType;
import vn.fpt.dbp.vccb.core.domain.product.QAutoBlockType;
import vn.fpt.dbp.vccb.core.domain.product.repository.AutoBlockTypeRepository;
import vn.fpt.util.rest.PagedResource;

import java.security.Principal;
import java.util.List;

/**
 * Created by T450 on 4/20/2017.
 */

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class AutoBlockTypeController {
    @Value("${dbp.api-gateway-url}")
    private String apiGatewayUrl;// = "http://10.86.202.223:8080";

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    protected AutoBlockTypeRepository autoBlockTypeRepository ;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/autoblocktype/list", method = RequestMethod.GET, produces = "application/json")
    public List<AutoBlockType> listAutoBlockType(Principal principle) {
        return autoBlockTypeRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/autoblocktype/detail/{id}", method = RequestMethod.GET, produces = "application/json")
    public AutoBlockType autoBlockTypeInfo(Principal principle, @PathVariable("id") Long id) {
        AutoBlockType autoBlockType = autoBlockTypeRepository.findOne(id);

        return autoBlockType;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/autoblocktype/search", method = RequestMethod.POST, produces = "application/json")
    public PagedResource<AutoBlockType> searchAutoBlockType(Principal principle, @RequestBody AutoBlockType autoBlockType, Pageable pageable) {
        QAutoBlockType qAutoBlockType = QAutoBlockType.autoBlockType;
        BooleanExpression booleanExpression = qAutoBlockType.id.isNotNull();

        Page<AutoBlockType> result = autoBlockTypeRepository.findAll(booleanExpression, pageable);

        return new PagedResource<AutoBlockType>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages());
    }
}
