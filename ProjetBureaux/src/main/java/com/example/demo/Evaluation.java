package com.example.demo;

public class Evaluation {
	private String idEvaluation;
	private String userName;
	private int note;
	private String commentaire;

	
	public Evaluation(String idEvaluation, String userName, int note, String commentaire) {
		this.idEvaluation = idEvaluation;
		this.userName = userName;
		this.note = note;
		this.commentaire = commentaire;
	}
	

	public String getIdEvaluation() {
		return idEvaluation;
	}

	
	public String getUserName() {
		return userName;
	}


	public int getNote() {
		return note;
	}


	public String getCommentaire() {
		return commentaire;
	}
	
	
}
