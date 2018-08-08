package cz.leris.playgames.server.generaldao.exceptions;

/**
 * Created by jirka on 10/16/15.
 */
public class SetException extends DaoException {

	public SetException(String source, DaoExceptionType type, String info) {
		super(source, type, info);
	}

	public SetException(String source, DaoExceptionType type) {
		super(source, type, "");
	}
}
