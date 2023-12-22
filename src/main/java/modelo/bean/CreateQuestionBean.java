package modelo.bean;

import java.text.SimpleDateFormat; 
import dominio.Evento;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import businessLogic.BLFacadeImplementation;

@ManagedBean
@ApplicationScoped
public class CreateQuestionBean {
	private Date fecha;
	private List<Evento> eventos;
	private Evento eventoElegido;
	private String question;
	private float minBet;
	private BLFacadeImplementation bl = new BLFacadeImplementation();

	// private String elementoSeleccionado;
	public CreateQuestionBean() {
		this.eventos = new ArrayList<Evento>();
	}

	public Evento getEventoElegido() {
		return this.eventoElegido;
	}

	public void setEventoElegido(Evento e) {
		System.out.println(e);
		this.eventoElegido = e;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setFecha(Date d) {
		this.fecha = d;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public float getMinBet() {
		return minBet;
	}

	public void setMinBet(float minBet) {
		this.minBet = minBet;
	}

	public void onDateSelect(SelectEvent e) {
		this.setFecha((Date) e.getObject());
		this.eventos = bl.getEvents(fecha);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Events: " + new SimpleDateFormat("dd/MM/YYYY").format(e.getObject())));
	}
	public void anadirPreguntaAEvento() {
		if(this.eventoElegido!=null) {
			try {
				bl.createQuestion(eventoElegido, question, minBet);
			} catch (EventFinished e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (QuestionAlreadyExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta creada con éxito"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Por alguna razón que desconozco este evento lo detecta como null. Prueba con otro evento"));
		}
	}
	public String irMenu() {
        //return "QueryQuestions.xhtml?faces-redirect=true";
		return "irMenu";
    }
	
}
