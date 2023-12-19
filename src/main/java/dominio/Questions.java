package dominio;


import java.util.Date;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

public class Questions {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private String result;
	@OneToOne(targetEntity=Eventos.class, fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@Fetch(value = FetchMode.SELECT)
	private Eventos event;

	public Questions(){
		//super();
	}
	
	public Questions(Integer queryNumber, String query, float betMinimum, Eventos event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}
	
	public Questions(String query, float betMinimum,  Eventos event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;

		this.event = event;
	}

	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public float getBetMinimum() {
		return betMinimum;
	}


	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}



	public String getResult() {
		return result;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @param result of the query to be setted
	 */
	
	public void setResult(String result) {
		this.result = result;
	}



	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Eventos getEvent() {
		return event;
	}



	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Eventos event) {
		this.event = event;
	}




	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}
	
	
}
