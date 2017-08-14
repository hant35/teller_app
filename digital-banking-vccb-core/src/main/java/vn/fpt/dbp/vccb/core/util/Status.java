package vn.fpt.dbp.vccb.core.util;

import java.io.Serializable;

public class Status implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String SAVE_DRAFT = "DRAFT";
	public static final String SEND_FOR_APPROVE = "SEND_FOR_APPROVE";
	public static final String ASSIGNED = "ASSIGNED";
	public static final String APPROVED = "APPROVED";
	public static final String REJECTED = "REJECTED";
	
	public static final String ENABLE = "ENABLE";
	public static final String DISABLE = "DISABLE";
	public static final String LOCK = "LOCK";
	public static final String OPEN = "OPEN";
	public static final String CLOSE = "CLOSE";

}
