package config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@ComponentScan({"disburse.controller", "disburse.dao"})
@SpringBootApplication
public class HouseDisburseConfig {
	
	@Bean
	public DataSource dataSrc() throws SQLException {
		DataSource ds = new EmbeddedDatabaseBuilder().addScript("classpath:2013Q4_HOUSE_DISBURSE.sql").build();
		ds.getConnection().setAutoCommit(true);
		return ds;
	}
	@Bean
	public JdbcTemplate getJdbcTemplate() throws Exception {
		return new JdbcTemplate(dataSrc());
	}

}
