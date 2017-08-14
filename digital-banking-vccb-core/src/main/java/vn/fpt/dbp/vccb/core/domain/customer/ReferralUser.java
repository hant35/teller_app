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

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

/*
 * Nguoi lien quan voi Bank + nguoi quan ly
 */
@Entity
@Table(name = "REFERRAL_USER") 
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ReferralUser.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class ReferralUser implements Serializable {
	private static final long serialVersionUID = 1L;

	public ReferralUser() {
	}

	public ReferralUser(Long id) {
		this.id = id;
	}

	public ReferralUser(int id) {
		this.id = new Long(id);
	}

	public ReferralUser(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "REFERRAL_USER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@Column(name = "CODE", length = 50)
	private String code;

	@Column(name = "NAME", length = 200)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POSITION")
	private Position position;
	
	/** Số giấy tờ pháp lý **/
	@Column(name = "LEGAL_DOCS_NUMBER", length = 50)
	private String legalDocsNumber;

	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ReferralUser))
			return false;
		if (this.getId() == null || ((ReferralUser) obj).getId() == null)
			return false;
		return this.getId().equals(((ReferralUser) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
