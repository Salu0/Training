package cz.leris.playgames.server.generaldao;

import cz.leris.playgames.server.generaldao.exceptions.DaoExceptionType;
import cz.leris.playgames.server.generaldao.exceptions.SetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by jirka on 10/8/15.
 */
public abstract class SetDao<T> implements GenericDao<T> {
	protected final Logger logger = LoggerFactory.getLogger(SetDao.class);
	protected final String source;
	protected String sql = "";

	protected SetDao(String source) {
		this.source = source;
	}

	@Override
	public T execute(DbConnection con) throws SetException {
		T result;
		try {
			result = executeImpl(con);
		} catch (SQLException e) {
//			logger.error("<SetDao> SQL error occured - source: {}, info: {}: {}", getSource(), getInfo(), e);
			throw new SetException(getSource(), DaoExceptionType.UPDATE_FAILED, getInfo());
		}

		if (isResultValid(result)) {
			return result;
		}   else {
			throw new SetException(getSource(), DaoExceptionType.NOT_INSERTED, getInfo());
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
