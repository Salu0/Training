package cz.leris.playgames.server.generaldao;

import cz.leris.playgames.server.generaldao.exceptions.DaoExceptionType;
import cz.leris.playgames.server.generaldao.exceptions.GetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by jirka on 10/8/15.
 */
public abstract class GetDao<T> implements GenericDao<T> {
	protected final Logger logger = LoggerFactory.getLogger(GetDao.class);
	protected final String source;
	// language=SQL
	protected String sql = "";

	protected GetDao(String source) {
		this.source = source;
	}

	@Override
	public T execute(DbConnection con) throws GetException {
		T result;
//		Marker marker = new Marker();
		try {
			result = executeImpl(con);
		} catch (SQLException e) {
//			logger.error("<GetDao> SQL error occured - source: {}, info: {}: {}", getSource(), getInfo(), e);
			throw new GetException(getSource(), DaoExceptionType.SQL_ERROR, getInfo());
		}

		if (isResultValid(result)) {
			return result;
		}   else {
			throw new GetException(getSource(), DaoExceptionType.NOT_FOUND, getInfo());
		}
	}

	protected abstract T executeImpl(DbConnection con) throws SQLException;

	protected boolean isResultValid(T result) {
		return true;
	}

	private String getSource() {
		return source;
	}

	public String getInfo() {
		return "";
	}
}
