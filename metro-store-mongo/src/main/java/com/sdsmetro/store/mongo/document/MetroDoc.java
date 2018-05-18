package com.sdsmetro.store.mongo.document;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sdsmetro.domain.entity.Metro;

import io.naraplatform.share.domain.granule.AdminList;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "MT_METRO")
@Getter
@Setter
public class MetroDoc {

	@Id
	private String id;

	@Indexed(unique = true)
	private String name;

	private String memo;
	private long pavilionSequence;
	private long citizenSequence;
	private AdminList admins;
	private long time;

	@Version
	private Long version;

	public MetroDoc() {
		//
	}

	public static MetroDoc toDocument(Metro metro) {
		//
		MetroDoc doc = new MetroDoc();
		doc.setId(metro.getId());
		doc.setName(metro.getName());
		doc.setVersion(metro.getVersion());
		BeanUtils.copyProperties(metro, doc);
		return doc;
	}

	public static List<Metro> toDomains(List<MetroDoc> metroDocuments) {
		//
		return metroDocuments.stream()
				.filter(Objects::nonNull)
				.map(MetroDoc::toDomain)
				.collect(Collectors.toList());
	}

	public Metro toDomain() {
		//
		Metro metro = new Metro(id);
		metro.setName(name);
		metro.setVersion(version);
		BeanUtils.copyProperties(this, metro);
		return metro;
	}
}
