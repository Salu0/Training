package cz.leris.playgames.server.generaldao;

/**
 *
 * @author ghost
 * @param <T>
 */
public interface TransactionTask<T> {

	T execute(DbConnection con) throws Exception;
	
}
