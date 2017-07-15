
package com.skyline.teapi;
public final class ApiException extends RuntimeException {
	private static final long serialVersionUID = -3405864458883181507L;
	public ApiException() { super(); }
	  public ApiException(String message) { super(message); }
	  public ApiException(String message, Throwable cause) { super(message, cause); }
	  public ApiException(Throwable cause) { super(cause); }
}
