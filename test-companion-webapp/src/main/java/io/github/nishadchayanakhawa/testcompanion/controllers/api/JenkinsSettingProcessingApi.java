package io.github.nishadchayanakhawa.testcompanion.controllers.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.nishadchayanakhawa.testcompanion.libraries.exceptions.JenkinsConnectionException;
import io.github.nishadchayanakhawa.testcompanion.model.JenkinsSetting;
import io.github.nishadchayanakhawa.testcompanion.services.JenkinsSettingService;

@RestController
@RequestMapping("/api/jenkinsSetting")
public class JenkinsSettingProcessingApi {
	@Autowired
	private JenkinsSettingService jenkinsSettingService;
	
	@GetMapping
	public ResponseEntity<JenkinsSetting> getJenkinsSetting() {
		return new ResponseEntity<>(this.jenkinsSettingService.getJenkinsSetting(),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> saveJenkinsSetting(@RequestBody JenkinsSetting jenkinsSetting) {
		this.jenkinsSettingService.saveJenkinsSetting(jenkinsSetting);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> testJenkinsSetting(@RequestBody JenkinsSetting jenkinsSetting) {
		try {
			this.jenkinsSettingService.testJenkinsSetting(jenkinsSetting);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IOException | JenkinsConnectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
