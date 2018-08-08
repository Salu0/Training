package cz.leris.playgames.server.generaldao;

/**
 * Created by martin on 04/04/18.
 */
public enum PlayerActualStatTable {
	DAY("day"),
	MONTH("month");

	private final String tableName;

	PlayerActualStatTable(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}
}
