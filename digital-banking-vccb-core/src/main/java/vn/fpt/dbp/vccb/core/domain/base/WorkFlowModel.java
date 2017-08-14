package vn.fpt.dbp.vccb.core.domain.base;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.role.Role;
import vn.fpt.dbp.vccb.core.domain.user.User;

@Getter
@Setter
@Entity
@Table(name = "WORKFLOW_MODEL")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable

public class WorkFlowModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public WorkFlowModel(){}
	
	
	public WorkFlowModel(Long id){
		this.id = id;
	}
	
	/*
	public WorkFlowModel(int id){
		this.id = new Long(id);
	}
	
	public WorkFlowModel(String id){
		this.id = new Long(id);
	}
	*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="dbpIdGenerator")
	//@GeneratedValue(strategy=GenerationType.AUTO, generator="dbpIdGenerator")
	@GenericGenerator(name="dbpIdGenerator", strategy="vn.fpt.dbp.vccb.core.util.DBPIdGenerator")
	protected Long id;
		
	@ManyToMany
	@JoinTable(
	      name="POTENTIAL_ASSIGNEE",
	      joinColumns=@JoinColumn(name="WF_MODEL_ID", referencedColumnName="ID"),
	      inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ID"))
	private Set<Role> potentialAssignees;
	
	@Column(name = "ORIGINAL_ID")
	private Long originalId;
		
	@Column(name = "WORKFLOWSTATUS",length = 50)
	private String workflowStatus;
	
	@Column(name = "TASK_ID")
	private Long taskId;
	
	@Column(name = "PROCESS_INSTANCE_ID")
	private Long processInstanceId;
	
	@Column(name = "PROCESS_DEPLOYMENT_ID",length = 100)
	private String processDeploymentId;
	
	@Column(name = "REFERENCE_CODE",length = 50)
	private String referenceCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate; // ngày tao
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	private User createdBy; // nguoi tao
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ASSIGNED_DATE")
	private Date assignedDate; // ngay nhan
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSIGNEE")
	private User assignee; // nguoi nhan
	
	@Column(name = "ASSIGN_GROUP",length = 50)
	private String assignGroup; //danh cho jBPM xu ly
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPROVED_DATE")
	private Date approvedDate; // ngay approve
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPROVED_BY")
	private User approvedBy; // nguoi approve
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="workFlowModel")
	@OrderBy("commentDate DESC") //orderby commentDate
	private Set<Comment> comments;
	
	@XmlTransient
	public Set<Role> getPotentialAssignees()
	{
		return this.potentialAssignees;
	}
	
	@Transient
	@JsonProperty
	private Date fromDate;
	
	@Transient
	@JsonProperty
	private Date toDate;
}
