package vn.fpt.dbp.vccb.core.domain.customer;

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
import vn.fpt.dbp.vccb.core.domain.organization.Branch;

/**
 * Entity: Tài khoản liên kết Sở KHĐT (Khach hang phap nhan)
 * 
 */
@Entity
@Table(name = "CUSTOMER_ACCOUNT_NO")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerAccountNo.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class CustomerAccountNo extends WorkFlowModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * @Structor<br>
	 * 
	 */
	public CustomerAccountNo() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public CustomerAccountNo(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public CustomerAccountNo(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public CustomerAccountNo(String id) {
		this.id = new Long(id);
	}

	/** Tên đơn vị **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/** Số tài khoản **/
	@Column(name = "ACCOUNT_NO")
	private String accountNo;

	/** Kích hoạt **/
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER")
	private Customer customer;

	@XmlTransient
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerAccountNo))
			return false;
		if (this.getId() == null || ((CustomerAccountNo) obj).getId() == null)
			return false;
		return this.getId().equals(((CustomerAccountNo) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
