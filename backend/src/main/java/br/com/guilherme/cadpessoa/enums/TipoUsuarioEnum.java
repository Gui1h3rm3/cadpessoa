package br.com.guilherme.cadpessoa.enums;

public enum TipoUsuarioEnum {
	
	ADMIN("admin"),
	USER("user");	
	
	private TipoUsuarioEnum(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	private String tipoUsuario;

	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
	public static TipoUsuarioEnum getEnum(String typeEnum) {
		
		for (TipoUsuarioEnum type : values()) {			
			if(type.getTipoUsuario().equals(typeEnum)) {
				return type;
			}
		}
		
		return null;
	}
}
