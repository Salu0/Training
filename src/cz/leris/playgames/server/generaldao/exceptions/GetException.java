package cz.leris.playgames.server.generaldao.exceptions;

/**
 * Created by jirka on 10/16/15.
 */
public class GetException extends DaoException {

	public GetException(String source, DaoExceptionType type, String info) {
		super(source, type, info);
	}

	public GetException(String source, DaoExceptionType type) {
		super(source, type, "");
	}
}
