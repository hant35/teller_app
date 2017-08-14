package vn.fpt.util.rest;

import java.io.Serializable;
import java.util.List;

public class RestParameter<T> implements Serializable {
	private T model;
	private String username;
	private List<String> dataPermissionFilter;
}
