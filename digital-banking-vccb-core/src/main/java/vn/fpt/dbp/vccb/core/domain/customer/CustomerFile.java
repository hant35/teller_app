package vn.fpt.dbp.vccb.core.domain.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CUSTOMER_FILE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerFile.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class CustomerFile implements Serializable {
	private static final long serialVersionUID = 1L;

	public CustomerFile() {
	}

	public CustomerFile(Long id) {
		this.id = id;
	}

	public CustomerFile(int id) {
		this.id = new Long(id);
	}

	public CustomerFile(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_FILE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/**  **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER")
	private Customer customer;

	/** Loại chứng từ **/
	@Column(name = "FILE_TYPE", length = 50)
	private String fileType;

	/** Loại chứng từ(Khác) **/
	@Column(name = "FILE_TYPE_OTHER", length = 50)
	private String fileTypeOther;

	/** Bản chính **/
	@Column(name = "IS_FILE_ORIGINAL")
	private Boolean isFileOriginal;

	/** Bản sao/Photo **/
	@Column(name = "IS_FILE_COPY")
	private Boolean isFileCopy;

	/** Số lượng **/
	@Column(name = "FILE_AMOUNT")
	private int fileAmount;

	/** Chứng từ chưa cung cấp **/
	@Column(name = "IS_FILE_PROVIDED")
	private Boolean isFileProvided;

	/** Ghi chú **/
	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	@XmlTransient
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerFile))
			return false;
		if (this.getId() == null || ((CustomerFile) obj).getId() == null)
			return false;
		return this.getId().equals(((CustomerFile) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
