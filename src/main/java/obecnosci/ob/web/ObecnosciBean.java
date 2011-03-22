package obecnosci.ob.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import javax.inject.Named;

import obecnosci.ob.domain.Obecnosci;
import obecnosci.ob.domain.Student;
import obecnosci.ob.domain.Zajecia;
import obecnosci.ob.service.ObecnosciManager;
import obecnosci.ob.service.StudentManager;
import obecnosci.ob.service.ZajeciaManager;

@SessionScoped
@Named
public class ObecnosciBean implements Serializable {

	private static final long serialVersionUID = 1691436078640069528L;
	@Inject
	ObecnosciManager obecnosciManager;
	@Inject
	StudentManager studentManager;
	@Inject
	ZajeciaManager zajeciaManager;

	//Zmienne
	private long id=0;
	private Student student;
	private Zajecia zajecia;
	private long IdZajec;
	// DATATABLE BACKEND
	private HtmlDataTable obecnosci;
	private HtmlDataTable studenci;
	
	//SETTERY i GETTERY
	
	
	public long getId() {
		return id;
	}
	public long getIdZajec() {
		return IdZajec;
	}
	public void setIdZajec(long idZajec) {
		IdZajec = idZajec;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Zajecia getZajecia() {
		return zajecia;
	}
	public void setZajecia(Zajecia zajecia) {
		this.zajecia = zajecia;
	}
	
	//METODY WLASNE
	public List<Obecnosci> getWszystkieObecnosci(){
	return obecnosciManager.pobierzWszystkie();
	}
	public List<Student> getWszystkichStudentow(){
		return studentManager.pobierzWszystkich();
		
	}
	public List<Zajecia> getWszystkieZajecia(){
		return zajeciaManager.pobierzWszystkie();
		
	}
	
	
	
	
	// DATATABLE BACKEDN
	public HtmlDataTable getObecnosci() {
		return obecnosci;
	}
	public void setObecnosci(HtmlDataTable obecnosci) {
		this.obecnosci = obecnosci;
	}
	public HtmlDataTable getStudenci() {
		return studenci;
	}
	public void setStudenci(HtmlDataTable studenci) {
		this.studenci = studenci;
	}
	
	
	//AKCJE
	public String zapiszObecnosc(){
	//dodawanie obecnosci
		Student instancja = (Student) studenci.getRowData();
		obecnosciManager.zapiszObecnosc(instancja.getId(),IdZajec);
		return "";	
	}
	
	public String zatwierdzObecnosc(){
		Obecnosci instancja = (Obecnosci) obecnosci.getRowData();
	obecnosciManager.zatwierdzObecnosci(instancja.getId());
		return "";	
	}

	
}
	
	
	
	

