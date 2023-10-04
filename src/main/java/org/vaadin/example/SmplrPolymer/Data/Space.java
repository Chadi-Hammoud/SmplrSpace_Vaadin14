package org.vaadin.example.SmplrPolymer.Data;

public class Space {

	private String space_Id;
	private String client_Token;
	private String container_Id;

	public Space() {
	}

	public Space(String space_Id, String client_Token, String container_Id) {
		this.space_Id = space_Id;
		this.client_Token = client_Token;
		this.container_Id = container_Id;
	}

	public String getSpace_Id() {
		return space_Id;
	}

	public void setSpace_Id(String space_Id) {
		this.space_Id = space_Id;
	}

	public String getClient_Token() {
		return client_Token;
	}

	public void setClient_Token(String client_Token) {
		this.client_Token = client_Token;
	}

	public String getContainer_Id() {
		return container_Id;
	}

	public void setContainer_Id(String container_Id) {
		this.container_Id = container_Id;
	}
	
	
}
