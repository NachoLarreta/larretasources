package ar.com.larreta.commons.persistence.dao.impl;


public class NotInSubquery extends Subquery {

	private static final String NOT_IN = "NOT IN";

	public NotInSubquery(LoadArguments args, String field, LoadArguments argsSub) {
		super(args, field, NOT_IN, argsSub);
	}

}
