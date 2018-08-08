package cz.leris.playgames.server.generaldao;

import cz.leris.playgames.server.generaldao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jirka on 10/8/15.
 */
public interface GenericDao<T> {
	Logger logger = LoggerFactory.getLogger(GenericDao.class);

	T execute(DbConnection con) throws DaoException;
}
