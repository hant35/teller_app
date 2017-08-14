package vn.fpt.dbp.vccb.core.domain.user.event;

import lombok.Value;
import vn.fpt.dbp.vccb.core.domain.user.User;

@Value
public class UserApprovedEvent {
	private User user;
}
