package com.essar.jsongenerator.dao;

import java.util.ArrayList;
import java.util.List;

public class EF_dao extends SF_dao{
	private SF_FormInfo_dao sfForm;
	private SF_Signatures_dao sfSigns;
	private SF_Attachments_dao sfAttachs;
	private EF_Proof_dao efProof;
	private List<SF_Hashes_dao> sfHashes;
	private List<String> coorelatedIds = new ArrayList<String>();
	private SF_dao sfdao;
	
	public EF_dao(){
		this.sfdao = new SF_dao();
		this.efProof = new EF_Proof_dao(sfdao.getSfForm().getId(),sfdao.getSfForm().getHash());
		
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
	public EF_Proof_dao getEfProof() {
		return efProof;
	}
	public void setEfProof(EF_Proof_dao efProof) {
		this.efProof = efProof;
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
