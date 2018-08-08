package cz.leris.playgames.server.generaldao;

//import cz.leris.playgames.server.sessionobjects.stations.pdos.PlayerActualStatTable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class SetPlayerLoginCount extends SetDao<Boolean> {

	private int idCardUsage;
	private int idCompany;
	private long time;
	private int change;

	public SetPlayerLoginCount(int idCardUsage, int idCompany, long time, int change) {
		super("SetPlayerLoginCount");
		this.idCardUsage = idCardUsage;
		this.idCompany = idCompany;
		this.time = time;
		this.change = change;
	}

	@Override
	protected Boolean executeImpl(DbConnection con) throws SQLException {
		return changeCount(con, PlayerActualStatTable.DAY) && changeCount(con, PlayerActualStatTable.MONTH);
	}

	private boolean changeCount(DbConnection con, PlayerActualStatTable table) throws SQLException {
		sql = "INSERT INTO pg_player_actual_" + table.getTableName() + "_stat SET " +
				" IDuser=(SELECT IDuser FROM pg_card_usage where IDcardUsage = ?), " +
				" IDCompany=?, " +
				" `date`=?," +
				" `login_count`= ? " +
				" ON DUPLICATE KEY UPDATE " +
				" `login_count`=VALUES(`login_count`) + `login_count`";

		try (PreparedStatement statsStmt = con.prepareStatement(sql)) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(time);

			if (PlayerActualStatTable.MONTH.equals(table)) {
				cal.set(Calendar.DATE, 1);
			}

			statsStmt.setInt(1, idCardUsage);
			statsStmt.setInt(2, idCompany);
			statsStmt.setDate(3, new java.sql.Date(cal.getTimeInMillis()));
			statsStmt.setInt(4, change);

			return statsStmt.executeUpdate() > 0;
		}
	}
}
