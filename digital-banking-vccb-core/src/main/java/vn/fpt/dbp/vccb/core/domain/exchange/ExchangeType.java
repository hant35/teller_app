package vn.fpt.dbp.vccb.core.domain.exchange;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EXCHANGE_TYPE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ExchangeType.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class ExchangeType implements Serializable {
	private static final long serialVersionUID = 1L;

	public ExchangeType() {
	}

	public ExchangeType(Long id) {
		this.id = id;
	}

	public ExchangeType(int id) {
		this.id = new Long(id);
	}

	public ExchangeType(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "EXCHANGE_TYPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@Column(name = "CODE", length = 50)
	private String code;

	@Column(name = "NAME", length = 200)
	private String name;

	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ExchangeType))
			return false;
		if (this.getId() == null || ((ExchangeType) obj).getId() == null)
			return false;
		return this.getId().equals(((ExchangeType) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
