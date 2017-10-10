package br.com.prestador.servico.personal.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private long id;  
	@Column(name="email")
    private String email;
	@Column(name="lati")
    private double lati;
	@Column(name="longi")
    private double longi;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dado_bancario_id")
	private DadoBancario dadoBancario;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aluno", targetEntity = Treino.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Treino> treinoAluno;
	
	@JsonIgnore
	@OneToMany(mappedBy = "professor", targetEntity = Treino.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Treino> treinoProfessor;
	
	public DadoBancario getDadoBancario() {
		return dadoBancario;
	}
	public void setDadoBancario(DadoBancario dadoBancario) {
		this.dadoBancario = dadoBancario;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getLati() {
		return lati;
	}
	public void setLati(double lati) {
		this.lati = lati;
	}
	public double getLongi() {
		return longi;
	}
	public void setLongi(double longi) {
		this.longi = longi;
	}
	public List<Treino> getTreinoAluno() {
		return treinoAluno;
	}
	public void setTreinoAluno(List<Treino> treinoAluno) {
		this.treinoAluno = treinoAluno;
	}
	public List<Treino> getTreinoProfessor() {
		return treinoProfessor;
	}
	public void setTreinoProfessor(List<Treino> treinoProfessor) {
		this.treinoProfessor = treinoProfessor;
	}
}
