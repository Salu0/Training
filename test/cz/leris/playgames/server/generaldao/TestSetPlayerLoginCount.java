package cz.leris.playgames.server.generaldao;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import cz.leris.playgames.server.generaldao.exceptions.SetException;
import org.junit.Test;

//import cz.leris.playgames.server.DB;
//import cz.leris.playgames.server.sessionobjects.stations.daos.set.SetPlayerLoginCount;
//import cz.leris.playgames.test.integration.utility.DBTest;

public class TestSetPlayerLoginCount extends DBTest {

	private static final int ID_COMPANY = 1;
	private static final int ID_CARD_USAGE = 4;

	public TestSetPlayerLoginCount() {
		super("TRUNCATE pg_player_actual_day_stat",
				"TRUNCATE pg_player_actual_month_stat");
	}

	@Test
	public void simple() throws SQLException, SetException {
		Boolean result;
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 0, 2, 8, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long startTime = cal.getTimeInMillis();

		SetPlayerLoginCount dao = new SetPlayerLoginCount(ID_CARD_USAGE, ID_COMPANY, startTime, 2);
		try (Connection rawCon = DB.getConnection()) {
			DbConnection con = new DbConnection(rawCon);
			result = dao.execute(con);
		}

		assertTrue(result);
		assertSql("SELECT 1 FROM pg_player_actual_day_stat where IDuser=1043 AND `date`='2016-01-02' AND `login_count`=2",
						"SELECT 1 FROM pg_player_actual_month_stat where IDuser=1043 AND `date`='2016-01-01' AND `login_count`=2");
	}

	@Test
	public void existing() throws SQLException, SetException {
		prepareSql("INSERT INTO pg_player_actual_day_stat SET IDstat=1, IDuser=1040, IDcompany=1, `date`='2016-01-02', `login_count`=1",
				"INSERT INTO pg_player_actual_month_stat SET IDstat=1, IDuser=1041, IDcompany=1, `date`='2016-01-01', `login_count`=2");
		Boolean result = null;
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 0, 2, 8, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long startTime = cal.getTimeInMillis();

		SetPlayerLoginCount dao = new SetPlayerLoginCount(ID_CARD_USAGE, ID_COMPANY, startTime, 2);
		try (Connection rawCon = DB.getConnection()) {
			DbConnection con = new DbConnection(rawCon);
			result = dao.execute(con);
		}

		assertTrue(result);
		assertSql("SELECT 1 FROM pg_player_actual_day_stat where IDuser=1040 AND `date`='2016-01-02' AND `login_count`=1",
				"SELECT 1 FROM pg_player_actual_month_stat where IDuser=1041 AND `date`='2016-01-01' AND `login_count`=2");
	}
}