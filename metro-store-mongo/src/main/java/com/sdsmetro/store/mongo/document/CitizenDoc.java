package com.sdsmetro.store.mongo.document;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sdsmetro.domain.entity.Citizen;

import io.naraplatform.share.domain.granule.Name;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "MT_CITIZEN")
@CompoundIndexes({
        @CompoundIndex(name = "idx_citizen_name",
                unique = true,
                def = "{'metroId' : 1, 'name' : 1}"),
        @CompoundIndex(name = "idx_citizen_email",
                unique = true,
                def = "{'metroId' : 1, 'email' : 1}")
})
@Getter
@Setter
public class CitizenDoc {

	@Id
	private String id;
	private long sequence;

	private Name name;
	private String username;
	private String email;
	private String phone;

	private boolean guest;
	private long time;
	private boolean disqualified;

	private String metroId;

	@Version
	private Long version;

	public CitizenDoc() {

	}

	public static CitizenDoc toDocument(Citizen citizen) {
		//
		CitizenDoc citizenDoc = new CitizenDoc();
		citizenDoc.setId(citizen.getId());
		citizenDoc.setVersion(citizen.getVersion());
		BeanUtils.copyProperties(citizen, citizenDoc);
		return citizenDoc;
	}

	public static List<Citizen> toDomains(List<CitizenDoc> citizenDocs) {
		return citizenDocs.stream()
				.map( doc -> doc.toDomain() )
				.collect(Collectors.toList());
	}

	public Citizen toDomain() {
		//
		Citizen citizen = new Citizen(id);
		citizen.setVersion(version);
		BeanUtils.copyProperties(this, citizen);
		return citizen;
	}
	
}
