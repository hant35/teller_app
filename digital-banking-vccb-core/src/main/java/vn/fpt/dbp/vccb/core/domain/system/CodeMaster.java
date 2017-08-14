package vn.fpt.dbp.vccb.core.domain.system;

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
@Table(name = "CODE_MASTER")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CodeMaster.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class CodeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	public CodeMaster() {
	}

	public CodeMaster(Long id) {
		this.id = id;
	}

	public CodeMaster(int id) {
		this.id = new Long(id);
	}

	public CodeMaster(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CODE_MASTER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@Column(name = "CODE", length = 50)
	private String code;

	@Column(name = "VALUE", length = 50)
	private String value;

	@Column(name = "NAME", length = 200)
	private String name;

	@Column(name = "SORT_NO")
	private Integer sortNo;

	@Column(name = "IS_DELETE")
	private boolean isDelete;
	
	@Column(name = "IS_DEFAULT")
	private boolean isDefault;

	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CodeMaster))
			return false;
		if (this.getId() == null || ((CodeMaster) obj).getId() == null)
			return false;
		return this.getId().equals(((CodeMaster) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
