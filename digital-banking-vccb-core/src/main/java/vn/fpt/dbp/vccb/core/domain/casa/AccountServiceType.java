package vn.fpt.dbp.vccb.core.domain.casa;

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
@Table(name = "ACCOUNT_SERVICE_TYPE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AccountServiceType.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class AccountServiceType implements Serializable {
	private static final long serialVersionUID = 1L;

	public AccountServiceType() {
	}

	public AccountServiceType(Long id) {
		this.id = id;
	}

	public AccountServiceType(int id) {
		this.id = new Long(id);
	}

	public AccountServiceType(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "ACCOUNT_SERVICE_TYPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_SERVICE")
	private AccountService accountService;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_SERVICE_GROUP")
	private AccountServiceGroup accountServiceGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_TYPE")
	private ServiceType serviceType;

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

	@XmlTransient
	public AccountServiceGroup getAccountServiceGroup() {
		return this.accountServiceGroup;
	}

	@XmlTransient
	public AccountService getAccountService() {
		return this.accountService;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AccountServiceType))
			return false;
		if (this.getId() == null || ((AccountServiceType) obj).getId() == null)
			return false;
		return this.getId().equals(((AccountServiceType) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
}