package com.essar.jsongenerator.dao;

import java.util.ArrayList;
import java.util.List;

public class SF_dao {
	
	private SF_FormInfo_dao sfForm;
	private SF_Signatures_dao sfSigns;
	private SF_Attachments_dao sfAttachs;
	private List<SF_Hashes_dao> sfHashes;
	private List<String> coorelatedIds = new ArrayList<String>();
	
	public SF_dao(){
		this.sfForm = new SF_FormInfo_dao();
		this.sfSigns = new SF_Signatures_dao();
		this.sfAttachs = new SF_Attachments_dao(sfForm, sfSigns);
		this.sfHashes = new ArrayList<SF_Hashes_dao>();
		SF_Hashes_dao formHash = new SF_Hashes_dao(sfForm);
		SF_Hashes_dao signHash = new SF_Hashes_dao(sfSigns);
		this.sfHashes.add(formHash);
		this.sfHashes.add(signHash);
	}

	public SF_FormInfo_dao getSfForm() {
		return sfForm;
	}

	public void setSfForm(SF_FormInfo_dao sfForm) {
		this.sfForm = sfForm;
	}

	public SF_Signatures_dao getSfSigns() {
		return sfSigns;
	}

	public void setSfSigns(SF_Signatures_dao sfSigns) {
		this.sfSigns = sfSigns;
	}

	public SF_Attachments_dao getSfAttachs() {
		return sfAttachs;
	}

	public void setSfAttachs(SF_Attachments_dao sfAttachs) {
		this.sfAttachs = sfAttachs;
	}

	public List<SF_Hashes_dao> getSfHashes() {
		return sfHashes;
	}

	public void setSfHashes(List<SF_Hashes_dao> sfHashes) {
		this.sfHashes = sfHashes;
	}

	public List<String> getCoorelatedIds() {
		return coorelatedIds;
	}

	public void setCoorelatedIds(List<String> coorelatedIds) {
		this.coorelatedIds = coorelatedIds;
	}
		
}
