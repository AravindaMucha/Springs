
//Exception for invalid input

package com.cg.mypaymentapp.exception;

public class CustomerDetailsNotFoundException extends RuntimeException 
{

	public CustomerDetailsNotFoundException(String msg) 
	{
		super(msg);
	}

	public CustomerDetailsNotFoundException()
	{
		super();
		
	}

	public CustomerDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) 
	{
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CustomerDetailsNotFoundException(String message, Throwable cause) 
	{
		super(message, cause);
		
	}

	public CustomerDetailsNotFoundException(Throwable cause) 
	{
		super(cause);
		
	}
	
}
