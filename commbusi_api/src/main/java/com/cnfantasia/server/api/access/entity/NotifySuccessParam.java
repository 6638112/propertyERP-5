package com.cnfantasia.server.api.access.entity;


public class NotifySuccessParam {
	private Integer parkId;
	private Object workstationId;
	private Object recordId;
	private Object code;
	private Object command;
	private Object result;

	public NotifySuccessParam() {
		super();
	}

	public NotifySuccessParam(Integer parkId, Object workstationId,
			Object recordId, Object code, Object command, Object result) {
		super();
		this.parkId = parkId;
		this.workstationId = workstationId;
		this.recordId = recordId;
		this.code = code;
		this.command = command;
		this.result = result;
	}

	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public Object getWorkstationId() {
		return workstationId;
	}

	public void setWorkstationId(Object workstationId) {
		this.workstationId = workstationId;
	}

	public Object getRecordId() {
		return recordId;
	}

	public void setRecordId(Object recordId) {
		this.recordId = recordId;
	}

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}

	public Object getCommand() {
		return command;
	}

	public void setCommand(Object command) {
		this.command = command;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
