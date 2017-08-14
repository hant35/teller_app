package vn.fpt.dbp.vccb.core.domain.casa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "SERVICE_TYPE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ServiceType.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class ServiceType extends WorkFlowModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4048941306193942423L;

	/**
	 * @Structor<br>
	 * 
	 */
	public ServiceType() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public ServiceType(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public ServiceType(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public ServiceType(String id) {
		this.id = new Long(id);
	}

	/** Mã Dịch vụ **/
	@Column(name = "CODE", length = 50)
	private String code;

	/** Tên Dịch vụ **/
	@Column(name = "NAME", length = 200)
	private String name;

	/** Trạng thái dịch vụ **/
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

	/** Định dạng dịch vụ **/
	@Column(name = "SERVICE_TYPE_FORM", length = 200)
	private String serviceTypeForm;

	/** Danh sách(Nếu là listbox, user nhập) **/
	@Column(name = "DATA_LIST", length = 2000)
	private String dataList;

	/** Cột thứ 1 **/
	@Column(name = "DATA_COLUMN_01", length = 200)
	private String DataColumn01;

	/** Cột thứ 2 **/
	@Column(name = "DATA_COLUMN_02", length = 200)
	private String DataColumn02;

	/** Cột thứ 3 **/
	@Column(name = "DATA_COLUMN_03", length = 200)
	private String DataColumn03;

	/** Cột thứ 4 **/
	@Column(name = "DATA_COLUMN_04", length = 200)
	private String DataColumn04;

	/** Cột thứ 5 **/
	@Column(name = "DATA_COLUMN_05", length = 200)
	private String DataColumn05;

	/** Cột thứ 6 **/
	@Column(name = "DATA_COLUMN_06", length = 200)
	private String DataColumn06;

	/** Cột thứ 7 **/
	@Column(name = "DATA_COLUMN_07", length = 200)
	private String DataColumn07;

	/** Cột thứ 8 **/
	@Column(name = "DATA_COLUMN_08", length = 200)
	private String DataColumn08;

	/** Cột thứ 9 **/
	@Column(name = "DATA_COLUMN_09", length = 200)
	private String DataColumn09;

	/** Cột thứ 10 **/
	@Column(name = "DATA_COLUMN_10", length = 200)
	private String DataColumn10;

	/** Cột thứ 11 **/
	@Column(name = "DATA_COLUMN_11", length = 200)
	private String DataColumn11;

	/** Cột thứ 12 **/
	@Column(name = "DATA_COLUMN_12", length = 200)
	private String DataColumn12;

	/** Cột thứ 13 **/
	@Column(name = "DATA_COLUMN_13", length = 200)
	private String DataColumn13;

	/** Cột thứ 14 **/
	@Column(name = "DATA_COLUMN_14", length = 200)
	private String DataColumn14;

	/** Cột thứ 15 **/
	@Column(name = "DATA_COLUMN_15", length = 200)
	private String DataColumn15;

	/** Cột thứ 16 **/
	@Column(name = "DATA_COLUMN_16", length = 200)
	private String DataColumn16;

	/** Cột thứ 17 **/
	@Column(name = "DATA_COLUMN_17", length = 200)
	private String DataColumn17;

	/** Cột thứ 18 **/
	@Column(name = "DATA_COLUMN_18", length = 200)
	private String DataColumn18;

	/** Cột thứ 19 **/
	@Column(name = "DATA_COLUMN_19", length = 200)
	private String DataColumn19;

	/** Cột thứ 20 **/
	@Column(name = "DATA_COLUMN_20", length = 200)
	private String DataColumn20;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ServiceType))
			return false;
		if (this.getId() == null || ((ServiceType) obj).getId() == null)
			return false;
		return this.getId().equals(((ServiceType) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
