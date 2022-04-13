package disburse.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
	
	//Code Challenge 1: Select All
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
	
	//Code Challenge 2: Select by ID
	@SuppressWarnings("deprecation")
	public List<HouseDisburseDetail> getListbyId(String id) {
		return jdbcTemplate.query("SELECT * from T_2013Q4_HOUSE_DISBURSE WHERE BIOGUIDE_ID = ?",new Object[] {id}, new RowMapper<HouseDisburseDetail>() {
			@Override
			public HouseDisburseDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				HouseDisburseDetail hdd = new HouseDisburseDetail();

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
	
	//Code Challenge 2.5: the Postman one
	/*
	 * public void saveAll(List<HouseDisburseDetail> hdList) { for
	 * (HouseDisburseDetail hdL : hdList) { HouseDisburseDetail hdd = new
	 * HouseDisburseDetail(); hdd.setBioGuideID(hdL.getBioGuideID());
	 * hdd.setOffice(hdL.getOffice()); hdd.setCategory(hdL.getCategory());
	 * hdd.setPayee(hdL.getPayee()); hdd.setStartDate(hdL.getStartDate());
	 * hdd.setEndDate(hdL.getEndDate()); hdd.setPurpose(hdL.getPurpose());
	 * hdd.setAmount(hdL.getAmount()); hdd.setYear(hdL.getYear()); } }
	 */
	
	public int[] batchInsert(List<HouseDisburseDetail> hdd) {
		return this.jdbcTemplate.batchUpdate(
				"INSERT INTO T_2013Q4_HOUSE_DISBURSE (BIOGUIDE_ID, office, category, payee, START_DATE, END_DATE, purpose, amount, year)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)",
				new BatchPreparedStatementSetter() {
					
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, hdd.get(i).getBioGuideID());
						ps.setString(2, hdd.get(i).getOffice());
						ps.setString(3, hdd.get(i).getCategory());
						ps.setString(4, hdd.get(i).getPayee());
						ps.setString(5, hdd.get(i).getStartDate());
						ps.setString(6, hdd.get(i).getEndDate());
						ps.setString(7, hdd.get(i).getPurpose());
						ps.setDouble(8, hdd.get(i).getAmount());
						ps.setString(9, hdd.get(i).getYear());
					}

					@Override
					public int getBatchSize() {
						return hdd.size();
					}
			
		});
	}
	
	@SuppressWarnings("deprecation")
	public List<HouseDisburseDetail> findByYear(String year) {
		return jdbcTemplate.query("SELECT * from T_2013Q4_HOUSE_DISBURSE WHERE YEAR = ?",new Object[] {year}, new RowMapper<HouseDisburseDetail>() {
			@Override
			public HouseDisburseDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				HouseDisburseDetail hdd = new HouseDisburseDetail();

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
	
	//Code Challenge 4: Highest Amount
	public List<HouseDisburseDetail> highestAmount() {
		return jdbcTemplate.query("SELECT * from T_2013Q4_HOUSE_DISBURSE where AMOUNT = (SELECT MAX(AMOUNT) from T_2013Q4_HOUSE_DISBURSE)", new RowMapper<HouseDisburseDetail>() {
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

	public Object getHighestByID() {
		return jdbcTemplate.queryForList("SELECT BIOGUIDE_ID, MAX(AMOUNT) from T_2013Q4_HOUSE_DISBURSE GROUP BY BIOGUIDE_ID", new RowMapper<HouseDisburseDetail>() {

			@Override
			public HouseDisburseDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
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
	
	//Code Challenge 5: Highest Amount by BioGuide ID
	
	
}
