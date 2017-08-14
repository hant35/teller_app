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
@Table(name = "ACCOUNT_CHEQUE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AccountCheque.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class AccountCheque extends WorkFlowModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4048941306193942423L;

	/**
	 * @Structor<br>
	 * 
	 */
	public AccountCheque() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public AccountCheque(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public AccountCheque(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public AccountCheque(String id) {
		this.id = new Long(id);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B_ACCOUNT")
	private BAccount bAccount;

	/** Mã Đơn vị **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/** Số Seri bắt đầu **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CHEQUE_SERIAL_START")
	private ChequeSerial serialNoStart;

	/** Số Seri kết thúc **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CHEQUE_SERIAL_END")
	private ChequeSerial serialNoEnd;

	/** Số tờ **/
	@Column(name = "PIECE_NUMBER")
	private int pieceNumber;

	/** Ngày cấp séc **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGISTED_DATE")
	private Date registedDate;

	/** Ngày hiệu lực **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_DATE")
	private Date validDate;

	/** Nội dung **/
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;

	@XmlTransient
	public BAccount getBAccount() {
		return this.bAccount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AccountCheque))
			return false;
		if (this.getId() == null || ((AccountCheque) obj).getId() == null)
			return false;
		return this.getId().equals(((AccountCheque) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
