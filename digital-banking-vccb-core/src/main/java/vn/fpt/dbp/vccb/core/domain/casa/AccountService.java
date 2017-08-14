package vn.fpt.dbp.vccb.core.domain.casa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;

@Entity
@Table(name = "ACCOUNT_SERVICE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AccountService.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class AccountService extends WorkFlowModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4048941306193942423L;

	/**
	 * @Structor<br>
	 * 
	 */
	public AccountService() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public AccountService(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public AccountService(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public AccountService(String id) {
		this.id = new Long(id);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B_ACCOUNT")
	private BAccount bAccount;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountService")
	private Set<AccountServiceType> accountServiceTypes;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountService")
	private Set<AccountServiceGroup> accountServiceGroups;

	@XmlTransient
	public BAccount getBAccount() {
		return this.bAccount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AccountService))
			return false;
		if (this.getId() == null || ((AccountService) obj).getId() == null)
			return false;
		return this.getId().equals(((AccountService) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
