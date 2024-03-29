package ar.com.larreta.commons.persistence.dao.impl;


public abstract class Order extends QueryElement{

	private String direction;
	
	public Order(LoadArguments args, String field, String direction){
		this.name = field;
		this.direction = direction;
		this.args = args;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
