package jp.co.sample.emp_management.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.emp_management.domain.Reminder;

@Repository
public class ReminderRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Reminder> REMINDER_ROW_MAPPER = (rs, i) -> {
		Reminder reminder = new Reminder();
		reminder.setId(rs.getInt("id"));
		reminder.setAdministratorId(rs.getInt("administrator_id"));
		reminder.setRandomStr(rs.getString("random_str"));
		reminder.setRegistDate(rs.getDate("regist_date"));
		reminder.setDelFlg(rs.getInt("del_flg"));
		return reminder;
	};
	
	
	public Reminder findByRandomStr(String randomStr) {
		String sql = """
				select
				  id
				  , administrator_id
				  , random_str
				  , regist_date
				  , del_flg
				from reminders
				where
				  random_str = :randomStr
				and
				  del_flg = 0
				and
				  regist_date > current_timestamp + '-30 minutes';
				""";
		SqlParameterSource param = new MapSqlParameterSource().addValue("randomStr", randomStr);
		try {
			return template.queryForObject(sql, param, REMINDER_ROW_MAPPER);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	public Reminder insert(Reminder reminder) {
		String sql = """
					insert into reminders (administrator_id, random_str)
					  values
					(:administratorId, :randomStr)
					returning id;
				""";
		
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("administratorId", reminder.getAdministratorId())
				.addValue("randomStr", reminder.getRandomStr());
		
		Integer id = template.queryForObject(sql, param, Integer.class);
		reminder.setId(id);
		return reminder;
	}
	
	public void updateDelFlg(Integer id) {
		String sql = """
				update reminders set
				  del_flg = 1
				where
				  id = :id
				""";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
