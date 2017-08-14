package vn.fpt.dbp.vccb.core.domain.customer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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

@Entity
@Table(name = "CUSTOMER_IMAGE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerImage.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class CustomerImage implements Serializable {
	private static final long serialVersionUID = 1L;

	public CustomerImage() {
	}

	public CustomerImage(Long id) {
		this.id = id;
	}

	public CustomerImage(int id) {
		this.id = new Long(id);
	}

	public CustomerImage(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_IMAGE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/**  **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER")
	private Customer customer;

	/** Loại hình ảnh **/
	@Column(name = "TYPE", length = 200)
	private String type;

	/** Mô tả của hình ảnh **/
	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	/** Tên khách hàng **/
	@Column(name = "CUSTOMER_NAME", length = 200)
	private String customerName;

	/** Chức danh khách hàng **/
	@Column(name = "CUSTOMER_POSITION", length = 50)
	private String customerPosition;

	/** Ngày hiệu lực **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_DATE")
	private Date validDate;

	/** Ngày hết hạn **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRED_DATE")
	private Date expiredDate;

	/** Upload file url **/
	@Column(name = "FILE_URL", length = 200)
	private String fileUrl;

	/** data file image **/
	@Lob
	@Column(name = "IMAGE")
	private byte[] image;

	@XmlTransient
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerImage))
			return false;
		if (this.getId() == null || ((CustomerImage) obj).getId() == null)
			return false;
		return this.getId().equals(((CustomerImage) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
