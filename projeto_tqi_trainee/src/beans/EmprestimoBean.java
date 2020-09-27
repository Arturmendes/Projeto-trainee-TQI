package beans;

public class EmprestimoBean {
	
	private Long id;
	private Long idCliente;
	private Double valor;
	private Long quantidadeParcelas;
	private String situacao;
	private Double parcelas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Long getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(Long quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Double getParcelas() {
		return parcelas;
	}
	public void setParcelas(Double parcelas) {
		this.parcelas = parcelas;
	}
	
	
	
	
	
	
	
	
	

}
