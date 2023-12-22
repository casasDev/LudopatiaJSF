package modelo.bean;

public class MenuBean {

	public String irVerPreg() {
        return "QueryQuestions.xhtml?faces-redirect=true";
		//return "irQueryQuestion";
    }

    public String irCrearPreg() {
        return "CreateQuestion.xhtml?faces-redirect=true";
    	//return "irCreateQuestion";
    }
	
}
