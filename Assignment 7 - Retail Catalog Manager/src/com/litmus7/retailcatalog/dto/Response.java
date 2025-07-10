package com.litmus7.retailcatalog.dto;

public class Response<T> {

    private ResponseStatus status;
    private String message;
    private T data;
    
	/**
	 * @param status
	 * @param message
	 * @param data
	 */
	public Response(ResponseStatus status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

    public static <T> Response<T> success(String message, T data) {
        return new Response<>(ResponseStatus.SUCCESS, message, data);
    }

    public static <T> Response<T> error(String message, T data) {
        return new Response<>(ResponseStatus.ERROR, message, data);
    }

    public static <T> Response<T> success(String message) {
        return new Response<>(ResponseStatus.SUCCESS, message, null);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(ResponseStatus.ERROR, message, null);
    }

	/**
	 * @return the status
	 */
	public ResponseStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [status=" + 
				status + 
				", message=" + message + 
				", data=" + data + 
				"]";
	}  
}
