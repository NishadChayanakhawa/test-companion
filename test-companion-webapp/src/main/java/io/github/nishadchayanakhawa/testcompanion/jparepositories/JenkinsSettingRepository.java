package io.github.nishadchayanakhawa.testcompanion.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.nishadchayanakhawa.testcompanion.model.JenkinsSetting;

public interface JenkinsSettingRepository extends JpaRepository<JenkinsSetting,Integer>{
	public JenkinsSetting getJenkinsSettingById(int id);
}
