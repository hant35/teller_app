package vn.fpt.dbp.vccb.core.domain.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;

/**
 * Entity: Quan ly kho so TKTT (Khach hang phap nhan)
 * 
 */
@Entity
@Table(name = "ACCOUNT_NO_RESERVED")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AccountNoReserved.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class AccountNoReserved extends WorkFlowModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * @Structor<br>
	 * 
	 */
	public AccountNoReserved() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public AccountNoReserved(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public AccountNoReserved(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public AccountNoReserved(String id) {
		this.id = new Long(id);
	}

	/** Tên đơn vị **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/** Số tài khoản tạo gần nhất **/
	@Column(name = "CURRENT_NO")
	private String currentNo;

	/** Số lượng phát hành **/
	@Column(name = "QUANTITY")
	private Integer quantity;

	/** Số tài khoản cuối **/
	@Column(name = "END_NO")
	private String endNo;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AccountNoReserved))
			return false;
		if (this.getId() == null || ((AccountNoReserved) obj).getId() == null)
			return false;
		return this.getId().equals(((AccountNoReserved) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
