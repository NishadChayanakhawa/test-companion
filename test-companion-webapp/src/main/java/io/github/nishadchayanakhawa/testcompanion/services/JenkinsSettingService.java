package io.github.nishadchayanakhawa.testcompanion.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.nishadchayanakhawa.testcompanion.jparepositories.JenkinsSettingRepository;
import io.github.nishadchayanakhawa.testcompanion.libraries.JenkinsProcessing;
import io.github.nishadchayanakhawa.testcompanion.libraries.exceptions.JenkinsConnectionException;
import io.github.nishadchayanakhawa.testcompanion.model.JenkinsSetting;

@Service
public class JenkinsSettingService {
	@Autowired
	private JenkinsSettingRepository jenkinsSettingRepository;
	
	public void saveJenkinsSetting(JenkinsSetting jenkinsSetting) {
		jenkinsSetting.setId(1);
		this.jenkinsSettingRepository.save(jenkinsSetting);
	}
	
	public void testJenkinsSetting(JenkinsSetting jenkinsSetting) throws IOException, JenkinsConnectionException {
		JenkinsProcessing.testJenkinsConnection(
				jenkinsSetting.getUrl(),
				jenkinsSetting.getUsername(),
				jenkinsSetting.getApiKey());
	}
	
	public JenkinsSetting getJenkinsSetting() {
		return this.jenkinsSettingRepository.getJenkinsSettingById(1);
	}
}
