package vn.fpt.dbp.vccb.core.domain.organization;

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

@Entity
@Table(name = "DEPARTMENT")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Department.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class Department implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Department(){}
	
	public Department(Long id){
		this.id = id;
	}
	
	public Department(int id){
		this.id = new Long(id);
	}
	
	public Department(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "DEPARTMENT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;
	
	@Column(name = "CODE", updatable=true, insertable=true, length = 20)
	private String code;
	
	@Column(name = "NAME", updatable=true, insertable=true, length = 200)
	private String name;
	
	@Column(name = "DESCRIPTION", updatable=true, insertable=true, length = 200)
	private String description;

	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Department))
			return false;
		if (this.getId() == null || ((Department) obj).getId() == null)
			return false;
		return this.getId().equals(((Department) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
