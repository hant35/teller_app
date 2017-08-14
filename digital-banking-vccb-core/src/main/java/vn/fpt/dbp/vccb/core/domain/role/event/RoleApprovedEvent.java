package vn.fpt.dbp.vccb.core.domain.role.event;

import lombok.Value;
import vn.fpt.dbp.vccb.core.domain.role.Role;

@Value
public class RoleApprovedEvent {
	private Role role;

}
