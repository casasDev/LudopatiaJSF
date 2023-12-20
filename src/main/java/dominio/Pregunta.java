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

@Entity
public class Pregunta {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long questionNumber;
	private String question; 
	private float betMinimum;
	private String result;
	@ManyToOne(targetEntity=Evento.class, fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@Fetch(value = FetchMode.SELECT)
	private Evento event;

	public Pregunta(){}
	
	public Pregunta(Long queryNumber, String query, float betMinimum, Evento event) {
		//super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}
	
	public Pregunta(String query, float betMinimum,  Evento event) {
		//super();
		this.question = query;
		this.betMinimum=betMinimum;

		this.event = event;
	}
	
	public Pregunta(String pregu, float betMinimun) {
		this.question = pregu;
		this.betMinimum=betMinimum;
		
	}

	public String getQuestion() {
		return question;
	}
	public Long getQuestionNumber() {
		return questionNumber;
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

	public void setResult(String result) {
		this.result = result;
	}

	public Evento getEvent() {
		return event;
	}


	public void setEvent(Evento event) {
		this.event = event;
	}


	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum)+";"+event;
	}
	
	public Long getId() {
		return questionNumber;
	}

	public int compareTo(String question2) {
		
		if (this.question ==question2) {
			return 0;
		}
		else return 1;
	}
	
}
