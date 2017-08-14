package vn.fpt.dbp.vccb.core.domain.casa;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;

@Entity
@Table(name = "SERVICE_GROUP")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ServiceGroup.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class ServiceGroup extends WorkFlowModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4048941306193942423L;

	/**
	 * @Structor<br>
	 * 
	 */
	public ServiceGroup() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public ServiceGroup(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public ServiceGroup(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public ServiceGroup(String id) {
		this.id = new Long(id);
	}

	/** Mã nhóm dịch vụ **/
	@Column(name = "CODE", length = 50)
	private String code;

	/** Tên nhóm dịch vụ **/
	@Column(name = "NAME", length = 200)
	private String name;

	/** Trạng thái nhóm dịch vụ **/
	@Column(name = "STATUS", length = 200)
	private String status;

	/** Ngày hiệu lực **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_DATE")
	private Date validDate;

	/** Ngày hết hạn **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRED_DATE")
	private Date expiredDate;

	/** Ghi chú **/
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "serviceGroup")
	private Set<ServiceGroupType> serviceGroupTypes;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ServiceGroup))
			return false;
		if (this.getId() == null || ((ServiceGroup) obj).getId() == null)
			return false;
		return this.getId().equals(((ServiceGroup) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
