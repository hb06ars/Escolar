package brandaoti.sistema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Treino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //Esse número é o ID automático gerado.
	
	@Column
	private Boolean ativo = true;
	
	@Column
	private Integer tipoOrdem; // Treino A, B, C... (0,1,2,3)
	
	@Column
	private Integer ordemDoDia; // Primeiro exercício, segundo, tercerio, etc...
	
	@Column
	private String descricao;
	
	@Column
	private Integer series;
	
	@Column
	private Integer repeticoes;
	
	@Column
	private String descanso;
	
	@Column
	private Integer ultimoTreinoExecutado = 0;
	
	@Column
	private String matricula;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getTipoOrdem() {
		return tipoOrdem;
	}

	public void setTipoOrdem(Integer tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}

	public Integer getOrdemDoDia() {
		return ordemDoDia;
	}

	public void setOrdemDoDia(Integer ordemDoDia) {
		this.ordemDoDia = ordemDoDia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(Integer repeticoes) {
		this.repeticoes = repeticoes;
	}

	public String getDescanso() {
		return descanso;
	}

	public void setDescanso(String descanso) {
		this.descanso = descanso;
	}

	public Integer getultimoTreinoExecutado() {
		return ultimoTreinoExecutado;
	}

	public void setultimoTreinoExecutado(Integer ultimoTreinoExecutado) {
		this.ultimoTreinoExecutado = ultimoTreinoExecutado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}

	
	
	
}
