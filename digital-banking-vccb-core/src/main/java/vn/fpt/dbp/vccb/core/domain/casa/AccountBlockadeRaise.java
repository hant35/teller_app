package vn.fpt.dbp.vccb.core.domain.casa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ACCOUNT_BLOCKADE_RAISE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AccountBlockadeRaise.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class AccountBlockadeRaise extends WorkFlowModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4048941306193942423L;

	/**
	 * @Structor<br>
	 * 
	 */
	public AccountBlockadeRaise() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public AccountBlockadeRaise(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public AccountBlockadeRaise(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public AccountBlockadeRaise(String id) {
		this.id = new Long(id);
	}

	/** Phong toả **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_BLOCKADE")
	private AccountBlockade accountBlockade;

	/** Hình thức giải tỏa **/
	@Column(name = "RAISE_TYPE", length = 200)
	private String raiseType;

	/** Số lần giải tỏa **/
	@Column(name = "RAISE_NUMBER")
	private int raiseNumber;

	/** Số tiền giải tỏa **/
	@Column(name = "RAISE_AMOUNT")
	private double raiseAmount;

	/** Nội dung giải tỏa **/
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;

	@XmlTransient
	public AccountBlockade getAccountBlockade() {
		return this.accountBlockade;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AccountBlockadeRaise))
			return false;
		if (this.getId() == null || ((AccountBlockadeRaise) obj).getId() == null)
			return false;
		return this.getId().equals(((AccountBlockadeRaise) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
