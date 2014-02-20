package org.smslib;
public class InvalidMessageException extends GatewayException{
	private static final long serialVersionUID = 1L;

	public InvalidMessageException(String errorMessage){
		super(errorMessage);
	}

	public InvalidMessageException(){
		super();
	}

	public InvalidMessageException(Throwable e){
		super(e);
	}

	public InvalidMessageException(String errorMessage, Throwable e){
		super(errorMessage, e);
	}
}
