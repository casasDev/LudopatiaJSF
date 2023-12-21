package dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Evento {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long eventNumber;
	private String description; 
	private Date eventDate;
	
	@OneToMany(targetEntity=Pregunta.class,fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@Fetch(value = FetchMode.SELECT)
	private List<Pregunta> questions=new ArrayList<Pregunta>();
	
	public List<Pregunta> getQuestions() {
		return questions;
	}

	public void addQuestions(Pregunta q) {
		this.questions.add(q);
	}

	public Evento() {
		//super();
	}


	public Evento( String description,Date eventDate) {
		this.description = description;
		this.eventDate=eventDate;
	}

	public Long getEventNumber(){
		return eventNumber;
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
	public String toString(){
		return eventNumber.toString()+"/"+description+"/"+eventDate+"/"+questions.toString();
	}
	
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Pregunta addQuestion(String question, float betMinimum)  {
        Pregunta q=new Pregunta(question,betMinimum, this);
        return q;
	}

	
	/**
	 * This method checks if the question already exists for that event
	 * 
	 * @param question that needs to be checked if there exists
	 * @return true if the question exists and false in other case
	 */
	public boolean DoesQuestionExists(String question)  {

		for (Pregunta q:this.getQuestions()){
			if (q.compareTo(question)==0)
				return true;
		}
		return false;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		long result = 1;
		result = prime * result + eventNumber;
		return (int) result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (eventNumber != other.eventNumber)
			return false;
		return true;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return eventNumber;
	}
	
	
	
}
