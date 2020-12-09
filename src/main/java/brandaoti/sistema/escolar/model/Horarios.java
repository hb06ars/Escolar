package brandaoti.sistema.escolar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Horarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //Esse número é o ID automático gerado.
	
	@OneToOne
	private Periodos periodo;
	
	@Column
	private Integer sala;
	
	@Column
	private String horarioDaAula;
	
	@Column
	private String diaDaSemana;
	
	@Column
	private String turma;
	
	@Column
	private String serie;
	
	@Column
	private String disciplina;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToOne
	private Usuario susbstituto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Periodos getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodos periodo) {
		this.periodo = periodo;
	}

	public Integer getSala() {
		return sala;
	}

	public void setSala(Integer sala) {
		this.sala = sala;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getHorarioDaAula() {
		return horarioDaAula;
	}

	public void setHorarioDaAula(String horarioDaAula) {
		this.horarioDaAula = horarioDaAula;
	}

	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public Usuario getSusbstituto() {
		return susbstituto;
	}

	public void setSusbstituto(Usuario susbstituto) {
		this.susbstituto = susbstituto;
	}

	
	
}
