package io.github.nishadchayanakhawa.testcompanion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class JenkinsSetting {
	@Id
	private int id;
	private String url;
	private String username;
	private String apiKey;
	public JenkinsSetting() {
		super();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
