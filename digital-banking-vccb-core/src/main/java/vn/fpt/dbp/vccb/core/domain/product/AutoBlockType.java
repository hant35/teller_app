package vn.fpt.dbp.vccb.core.domain.product;

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
@Table(name = "AUTO_BLOCK_TYPE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AutoBlockType.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class AutoBlockType implements Serializable {
	private static final long serialVersionUID = 1L;

	public AutoBlockType() {
	}

	public AutoBlockType(Long id) {
		this.id = id;
	}

	public AutoBlockType(int id) {
		this.id = new Long(id);
	}

	public AutoBlockType(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "AUTO_BLOCK_TYPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@Column(name = "CODE", updatable = true, insertable = true, length = 50)
	private String code;

	@Column(name = "NAME", updatable = true, insertable = true, length = 200)
	private String name;

	@Column(name = "DESCRIPTION", updatable = true, insertable = true, length = 500)
	private String description;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AutoBlockType))
			return false;
		if (this.getId() == null || ((AutoBlockType) obj).getId() == null)
			return false;
		return this.getId().equals(((AutoBlockType) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
