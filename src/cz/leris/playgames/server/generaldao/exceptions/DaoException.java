package cz.leris.playgames.server.generaldao.exceptions;

import com.google.gson.JsonObject;

/**
 * Created by jirka on 10/16/15.
 */
public class DaoException extends Exception {
	private final DaoExceptionType type;
	private final String info;
	private final String source;
	private final JsonObject msg;
	
	public DaoException(String source, DaoExceptionType type, String info) {
		this.source = source;
		this.type = type;
		this.info = info;
		this.msg = null;
	}
	
	public DaoException(String source, DaoExceptionType type, String info, JsonObject msg) {
		this.source = source;
		this.type = type;
		this.info = info;		
		this.msg = msg;
	}

	public DaoExceptionType getType() {
		return type;
	}

	public String getInfo() {
		return info;
	}

	public String getSource() {
		return source;
	}
	
	public JsonObject getResponse() {
		return msg;
	}
}
