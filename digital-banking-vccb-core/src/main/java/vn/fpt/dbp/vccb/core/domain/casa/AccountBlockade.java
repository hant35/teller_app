package vn.fpt.dbp.vccb.core.domain.casa;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "ACCOUNT_BLOCKADE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AccountBlockade.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class AccountBlockade extends WorkFlowModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4048941306193942423L;

	/**
	 * @Structor<br>
	 * 
	 */
	public AccountBlockade() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public AccountBlockade(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public AccountBlockade(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public AccountBlockade(String id) {
		this.id = new Long(id);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B_ACCOUNT")
	private BAccount bAccount;

	/** Đơn vị giao dịch **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/** Số tiền phong toả **/
	@Column(name = "BLOCKADE_AMOUNT")
	private double blockadeAmount;

	/** Mất sổ tiết kiệm **/
	@Column(name = "IS_LOSED_SAVINGBOOK")
	private boolean isLosedSavingBook;

	/** Ngày hiệu lực **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_DATE")
	private Date validDate;

	/** Ngày hết hạn **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRED_DATE")
	private Date expiredDate;

	/** Nội dung phong tỏa **/
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountBlockade")
	private Set<AccountBlockadeRaise> accountBlockadeRaises;

	@XmlTransient
	public BAccount getBAccount() {
		return this.bAccount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AccountBlockade))
			return false;
		if (this.getId() == null || ((AccountBlockade) obj).getId() == null)
			return false;
		return this.getId().equals(((AccountBlockade) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
