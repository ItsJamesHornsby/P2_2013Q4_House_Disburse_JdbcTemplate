package disburse.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HouseDisburseDAO {

	private JdbcTemplate jdbcTemplate;
	//Constructor injection below!
	HouseDisburseDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
