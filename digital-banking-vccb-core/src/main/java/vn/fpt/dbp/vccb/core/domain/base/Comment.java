package vn.fpt.dbp.vccb.core.domain.base;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "VCCB_COMMENT") // oracle bao loi neu table = COMMENT
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Comment.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","workFlowModel"})
@Remotable
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Comment(){}
	
	public Comment(Long id){
		this.id = id;
	}
	
	public Comment(int id){
		this.id = new Long(id);
	}
	
	public Comment(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "COMMENT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	/*
	 * Noi dung comment
	 */
	
	@Column(name = "CONTENT", updatable=true, insertable=true, length = 2000)
	private String content;
	
	/*
	 * user name of commentor
	 */
	@Column(name = "COMMENTOR", updatable=true, insertable=true, length = 50)
	private String commentor;
	
	/*
	 * Ngay comment
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENT_DATE")
	private Date commentDate;
	
	@Column(name = "WORKFLOWSTATUS",length = 50)
	private String workflowStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKFLOW_MODEL")
	private WorkFlowModel workFlowModel;
	
	@XmlTransient
	public WorkFlowModel getWorkFlowModel()
	{
		return this.workFlowModel;
	}

}
