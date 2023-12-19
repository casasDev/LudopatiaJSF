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
public class Questions {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long questionNumber;
	private String question; 
	private float betMinimum;
	private String result;
	@OneToOne(targetEntity=Eventos.class, fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@Fetch(value = FetchMode.SELECT)
	private Eventos event;

	public Questions(){}
	
	public Questions(Long queryNumber, String query, float betMinimum, Eventos event) {
		//super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}
	
	public Questions(String query, float betMinimum,  Eventos event) {
		//super();
		this.question = query;
		this.betMinimum=betMinimum;

		this.event = event;
	}
	
	public Questions(String pregu, float betMinimun) {
		this.question = pregu;
		this.betMinimum=betMinimum;
		
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

	public void setResult(String result) {
		this.result = result;
	}

	public Eventos getEvent() {
		return event;
	}


	public void setEvent(Eventos event) {
		this.event = event;
	}


	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum)+";"+event;
	}
	
	public Long getId() {
		return questionNumber;
	}
	
}
