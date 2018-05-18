package com.sdsmetro.store.mongo.document;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sdsmetro.domain.entity.LoginTime;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MT_LOGIN_TIME")
@Getter
@Setter
public class LoginTimeDoc {

	//
	@Id
	private String id;

	private String citizenId;
	private ZonedDateTime time;

	@Version
	private Long version;

	public LoginTimeDoc() {
		//
	}

	public static LoginTimeDoc toDocument(LoginTime loginTime) {
		//
		LoginTimeDoc doc = new LoginTimeDoc();
		doc.setId(loginTime.getId());
		doc.setVersion(loginTime.getVersion());
		BeanUtils.copyProperties(loginTime, doc);
		return doc;
	}

	public static List<LoginTime> toDomains(List<LoginTimeDoc> loginTimeDocuments) {
		//
		return loginTimeDocuments.stream()
				.filter(Objects::nonNull)
				.map(LoginTimeDoc::toDomain)
				.collect(Collectors.toList());
	}

	public LoginTime toDomain() {
		//
		LoginTime loginTime = new LoginTime(id);
		loginTime.setVersion(version);
		BeanUtils.copyProperties(this, loginTime);
		return loginTime;
	}
	
}
