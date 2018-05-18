package com.sdsmetro.store.mongo.document;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sdsmetro.domain.entity.LoginUser;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MT_LOGIN_USER")
@Getter
@Setter
public class LoginUserDoc {

	@Id
	private String id;
	private String username;
	private String email;
	private String password;
	private long passwordChangedTime;
	private String metroId;

	@Version
	private Long version;

	public LoginUserDoc() {
		//
	}

	public static LoginUserDoc toDocument(LoginUser loginUser) {
		//
		LoginUserDoc doc = new LoginUserDoc();
		doc.setId(loginUser.getId());
		doc.setVersion(loginUser.getVersion());
		BeanUtils.copyProperties(loginUser, doc);
		return doc;
	}

	public static List<LoginUser> toDomains(List<LoginUserDoc> loginUserDocuments) {
		//
		return loginUserDocuments.stream()
				.filter(Objects::nonNull)
				.map(LoginUserDoc::toDomain)
				.collect(Collectors.toList());
	}

	public LoginUser toDomain() {
		//
		LoginUser loginUser = new LoginUser(id);
		loginUser.setVersion(version);
		BeanUtils.copyProperties(this, loginUser);
		return loginUser;
	}
	
}
