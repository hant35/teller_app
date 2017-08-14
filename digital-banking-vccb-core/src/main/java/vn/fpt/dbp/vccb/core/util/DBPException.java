package vn.fpt.dbp.vccb.core.util;

import java.io.Serializable;

public class DBPException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String RECORD_NOT_EXISTED = "RECORD_NOT_EXISTED";
	public static final String NO_CREATER = "NO_CREATER";
	public static final String NO_ASSIGNEE = "NO_ASSIGNEE";
	public static final String NO_APPROVER = "NO_APPROVER";
	public static final String NO_COMMENT = "NO_COMMENT";
	public static final String DUPLICATE_DETAIL = "DUPLICATE_DETAIL";
	public static final String WRONG_WORKFLOW_STATUS = "WRONG_WORKFLOW_STATUS"; //workflowstatus sai khi cap nhat trang thai
}
