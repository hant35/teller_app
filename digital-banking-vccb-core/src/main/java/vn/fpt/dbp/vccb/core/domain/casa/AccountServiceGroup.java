package vn.fpt.dbp.vccb.core.domain.casa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNT_SERVICE_GROUP")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AccountServiceGroup.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class AccountServiceGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	public AccountServiceGroup() {
	}

	public AccountServiceGroup(Long id) {
		this.id = id;
	}

	public AccountServiceGroup(int id) {
		this.id = new Long(id);
	}

	public AccountServiceGroup(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "ACCOUNT_SERVICE_GROUP_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_SERVICE")
	private AccountService accountService;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_GROUP")
	private ServiceGroup serviceGroup;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountServiceGroup")
	private Set<AccountServiceType> accountServiceTypes;

	@XmlTransient
	public AccountService getAccountService() {
		return this.accountService;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AccountServiceGroup))
			return false;
		if (this.getId() == null || ((AccountServiceGroup) obj).getId() == null)
			return false;
		return this.getId().equals(((AccountServiceGroup) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
}