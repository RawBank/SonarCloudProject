/**
* Copyright 2022 Rawbank. All Rights Reserved.
* 
 * Change Section:
* Programmer               Date
*
* 
 * 
 * Created By:
* @author jacquesk
* @since 9 nov. 2022
* 
 */

package com.rawbank.admin.oracle.entity;

/**
 * @author jacquesk
 *
 */

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CSC_ADMIN_CONFIGURATION" /*, schema = "JACQUESK" */)

//@NoArgsConstructor
public class RawCscConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = true, updatable = true, insertable = true)
	private Long id;

	@Column(name = "CONFKEY", nullable = true, updatable = true, insertable = true)
	private String confkey;

	@Column(name = "CONFVALUE", nullable = true, updatable = true, insertable = true)
	private String confvalue;

	@Column(name = "CONFDESCRIPTION", nullable = true, updatable = true, insertable = true)
	private String confdescription;

	@Column(name = "DATEMODIFICATION", nullable = true, updatable = true, insertable = true)
	private Timestamp datemodif;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConfkey() {
		return confkey;
	}

	public void setConfkey(String confkey) {
		this.confkey = confkey;
	}

	public String getConfvalue() {
		return confvalue;
	}

	public void setConfvalue(String confvalue) {
		this.confvalue = confvalue;
	}

	public String getConfdescription() {
		return confdescription;
	}

	public void setConfdescription(String confdescription) {
		this.confdescription = confdescription;
	}

	public Timestamp getDatemodif() {
		return datemodif;
	}

	public void setDatemodif(Timestamp datemodif) {
		this.datemodif = datemodif;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
