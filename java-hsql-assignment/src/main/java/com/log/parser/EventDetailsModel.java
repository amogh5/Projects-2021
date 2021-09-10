package com.log.parser;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties


public class EventDetailsModel {

	
	private String id;

	private long eventDuration;

	private String state;

	
	private String type;

	private String host;

	private boolean alert;
	
	private long timestamp;


	public EventDetailsModel() {

	}


	public EventDetailsModel(String eventId, long eventDuration, String state, String type, String host, boolean alert,
			long timestamp) {
		super();
		this.id = eventId;
		this.eventDuration = eventDuration;
		this.state = state;
		this.type = type;
		this.host = host;
		this.alert = alert;
		this.timestamp = timestamp;
	}


	public String getEventId() {
		return id;
	}


	public void setEventId(String eventId) {
		this.id = eventId;
	}


	public long getEventDuration() {
		return eventDuration;
	}


	public void setEventDuration(long eventDuration) {
		this.eventDuration = eventDuration;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public boolean isAlert() {
		return alert;
	}


	public void setAlert(boolean alert) {
		this.alert = alert;
	}


	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	
	
	}
