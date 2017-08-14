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

/*
 * Entity luu tru cac status cua cac object
 */

@Entity
@Table(name = "STATUS_CODE")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=StatusCode.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class StatusCode implements Serializable{
	private static final long serialVersionUID = 1L;
	public StatusCode(){}
	
	public StatusCode(Long id){
		this.id = id;
	}
	
	public StatusCode(int id){
		this.id = new Long(id);
	}
	
	public StatusCode(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "STATUS_CODE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	/*
	 * Ma cua object. Vi du: USER, LIMIT, ROLE...
	 */
	@Column(name = "OBJECT_CODE", updatable=true, insertable=true, length = 50)
	private String object;
	/*
	 * Ma cua status. Vi du: OPEN, CLOSE, DISABLE, ENABLE,... 
	 */
	@Column(name = "STATUS_CODE", updatable=true, insertable=true, length = 50)
	private String code;
	/*
	 * Gia tri cua key theo ngon ngu Viet Nam: Mo, Dong...
	 */
	@Column(name = "STATUS_VALUE", updatable=true, insertable=true, length = 50)
	private String value;
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof StatusCode))
			return false;
		if (this.getId() == null || ((StatusCode) obj).getId() == null)
			return false;
		return this.getId().equals(((StatusCode) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
