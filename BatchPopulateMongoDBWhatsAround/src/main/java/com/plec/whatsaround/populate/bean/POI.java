package com.plec.whatsaround.populate.bean;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Index;
import com.google.code.morphia.annotations.NotSaved;
import com.google.code.morphia.emul.org.bson.types.ObjectId;
@Entity("poi")
public class POI {
	/** POI's id */
	@Id private ObjectId id;
	/** POI's id */
	@NotSaved
	private String sourceId;
	/** POI's name */
	private String name;
	/** POI's Localisation */
	private LatLng latlng = new LatLng();
	/** POI's type */
	private POIType type;
	/** POI's description */
	private String description;
	/** POI's datebeg */
	private Date dateBeg;
	/** POI's dateEnd */
	private Date dateEnd;
	/** POI's periode ouverture */
	private String periodeOuverture;
	/** POI's periode fermeture */
	private String periodeFermeture;
	/** POI's horaires */
	private String horaires;
	/** POI's note */
	private String note;
	/** POI's ville */
	private String ville;
	/** POI's adresse */
	private String adresse;
	/** POI's formatted adresse */
	private String formattedAddress;
	/** POI's cp */
	private String cp;

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}
	/**
	 * @return the latlng
	 */
	public LatLng getLatlng() {
		return latlng;
	}
	/**
	 * @param latlng the latlng to set
	 */
	public void setLatlng(LatLng latlng) {
		this.latlng = latlng;
	}
	/**
	 * @return the type
	 */
	public POIType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(POIType type) {
		this.type = type;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the dateBeg
	 */
	public Date getDateBeg() {
		return dateBeg;
	}
	/**
	 * @param dateBeg the dateBeg to set
	 */
	public void setDateBeg(Date dateBeg) {
		this.dateBeg = dateBeg;
	}
	/**
	 * @return the dateEnd
	 */
	public Date getDateEnd() {
		return dateEnd;
	}
	/**
	 * @param dateEnd the dateEnd to set
	 */
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	/**
	 * @return the horaires
	 */
	public String getHoraires() {
		return horaires;
	}
	/**
	 * @param horaires the horaires to set
	 */
	public void setHoraires(String horaires) {
		this.horaires = horaires;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}
	/**
	 * @param cp the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the periodeOuverture
	 */
	public String getPeriodeOuverture() {
		return periodeOuverture;
	}
	/**
	 * @param periodeOuverture the periodeOuverture to set
	 */
	public void setPeriodeOuverture(String periodeOuverture) {
		this.periodeOuverture = periodeOuverture;
	}
	/**
	 * @return the periodeFermeture
	 */
	public String getPeriodeFermeture() {
		return periodeFermeture;
	}
	/**
	 * @param periodeFermeture the periodeFermeture to set
	 */
	public void setPeriodeFermeture(String periodeFermeture) {
		this.periodeFermeture = periodeFermeture;
	}
	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}
	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	/**
	 * @return the formattedAdress
	 */
	public String getFormattedAddress() {
		return formattedAddress;
	}
	/**
	 * @param formattedAdress the formattedAdress to set
	 */
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
	
}
