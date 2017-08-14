package vn.fpt.dbp.vccb.service.param.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Status;

@Component
public class UserUtil {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String username){

        if(StringUtils.isEmpty(username)){
            return null;
        }

        return userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username, Status.APPROVED);
    }
}
