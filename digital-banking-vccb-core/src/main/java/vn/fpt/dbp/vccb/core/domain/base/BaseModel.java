package vn.fpt.dbp.vccb.core.domain.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter 
public class BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	//@Id
	//@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "ID_SEQ")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	//protected Long id;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate; // ngày tao
	
	@Column(name = "CREATED_BY")
	private String createdBy; // nguoi tao
	

}
