package br.com.aplicaVacinas.config.validacao;

public class ErroDeRequisicaoDto {

	private String campo;
	private String erro;

	public ErroDeRequisicaoDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
