package brandaoti.sistema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class CadastrarAvaliacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //Esse número é o ID automático gerado.
	
	@Column
	private String codigo;
	
	@OneToOne
	private Usuario aluno;
	
	@Column
	private Boolean ativo = true;
	
	@Column
	private String observacoes;

	@Column
	private Date inicio = new Date();
	
	@Column
	private Date fim;
	
	@OneToOne
	private Usuario avaliador;

	@Column
	private Date inicioAvaliacao = new Date();
	@Column
	private Date ultimaVisita = new Date();
	@Column
	private Double gorduraCoroporal;
	@Column
	private Double gorduraTrans;
	@Column
	private Double peso;
	@Column
	private Double altura;
	@Column
	private Double abdominal;
	@Column
	private Double biceps;
	@Column
	private Double triceps;
	@Column
	private Double costas;
	@Column
	private Double perna;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	
	public Date getInicioAvaliacao() {
		return inicioAvaliacao;
	}
	public void setInicioAvaliacao(Date inicioAvaliacao) {
		this.inicioAvaliacao = inicioAvaliacao;
	}
	public Date getUltimaVisita() {
		return ultimaVisita;
	}
	public void setUltimaVisita(Date ultimaVisita) {
		this.ultimaVisita = ultimaVisita;
	}
	public Double getGorduraCoroporal() {
		return gorduraCoroporal;
	}
	public void setGorduraCoroporal(Double gorduraCoroporal) {
		this.gorduraCoroporal = gorduraCoroporal;
	}
	public Double getGorduraTrans() {
		return gorduraTrans;
	}
	public void setGorduraTrans(Double gorduraTrans) {
		this.gorduraTrans = gorduraTrans;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getAbdominal() {
		return abdominal;
	}
	public void setAbdominal(Double abdominal) {
		this.abdominal = abdominal;
	}
	public Double getBiceps() {
		return biceps;
	}
	public void setBiceps(Double biceps) {
		this.biceps = biceps;
	}
	public Double getTriceps() {
		return triceps;
	}
	public void setTriceps(Double triceps) {
		this.triceps = triceps;
	}
	public Double getCostas() {
		return costas;
	}
	public void setCostas(Double costas) {
		this.costas = costas;
	}
	public Double getPerna() {
		return perna;
	}
	public void setPerna(Double perna) {
		this.perna = perna;
	}
	public Usuario getAluno() {
		return aluno;
	}
	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}
	public Usuario getAvaliador() {
		return avaliador;
	}
	public void setAvaliador(Usuario avaliador) {
		this.avaliador = avaliador;
	}
	
	
	
	
	
	
	
}
