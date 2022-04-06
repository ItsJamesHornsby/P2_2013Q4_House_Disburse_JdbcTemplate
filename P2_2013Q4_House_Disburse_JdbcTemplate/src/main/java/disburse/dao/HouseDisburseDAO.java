package disburse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import disburse.vo.HouseDisburseDetail;

import org.springframework.jdbc.core.RowMapper;

@Service
public class HouseDisburseDAO {

	private JdbcTemplate jdbcTemplate;
	//Constructor injection below!
	HouseDisburseDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Map<String, Object>> getListOfAllDisburses() {
		return jdbcTemplate.queryForList("SELECT * from T_2013Q4_HOUSE_DISBURSE");
	}
	
	public List<HouseDisburseDetail> getListofAllHDRowMapper() {
		return jdbcTemplate.query("SELECT * from T_2013Q4_HOUSE_DISBURSE", new RowMapper<HouseDisburseDetail>() {
			@Override
			public HouseDisburseDetail mapRow(ResultSet rs, int rowNumber) throws SQLException {
				HouseDisburseDetail hdd = new HouseDisburseDetail();
				//hdd.setRecordID(rs.getInt("recordID"));
				hdd.setBioGuideID(rs.getString("BIOGUIDE_ID"));
				hdd.setOffice((rs.getString("OFFICE")).toLowerCase());
				hdd.setCategory(rs.getString("CATEGORY"));
				hdd.setPayee(rs.getString("PAYEE"));
				hdd.setStartDate(rs.getString("START_DATE"));
				hdd.setEndDate(rs.getString("END_DATE"));
				hdd.setPurpose(rs.getString("PURPOSE"));
				hdd.setAmount(rs.getDouble("AMOUNT"));
				hdd.setYear(rs.getString("YEAR"));
				return hdd;
			}
		});
	}
}
