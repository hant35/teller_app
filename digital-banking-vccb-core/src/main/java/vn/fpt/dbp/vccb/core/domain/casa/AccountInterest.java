package vn.fpt.dbp.vccb.core.domain.casa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;

@Entity
@Table(name = "ACCOUNT_INTEREST")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AccountInterest.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class AccountInterest extends WorkFlowModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4048941306193942423L;

	/**
	 * @Structor<br>
	 * 
	 */
	public AccountInterest() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public AccountInterest(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public AccountInterest(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public AccountInterest(String id) {
		this.id = new Long(id);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B_ACCOUNT")
	private BAccount bAccount;

	/** Đơn vị giao dịch **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/** Ngày kết lãi **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CALCULATED_DATE")
	private Date caculatedDate;

	/** Số tiền lãi **/
	@Column(name = "INTEREST_MOUNT")
	private double interestMount;

	/** Số dư tài khoản **/
	@Column(name = "BALANCE")
	private double balance;

	/** Tổng số dư **/
	@Column(name = "TOTAL_BALANCE")
	private double totalBalance;

	@XmlTransient
	public BAccount getBAccount() {
		return this.bAccount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AccountInterest))
			return false;
		if (this.getId() == null || ((AccountInterest) obj).getId() == null)
			return false;
		return this.getId().equals(((AccountInterest) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
