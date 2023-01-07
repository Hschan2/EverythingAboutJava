package com.bs.lec20.config;

import java.beans.PropertyVetoException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DBConfig {

	// xml 사용이 아닌 Java 파일을 이용한 자동 주입
	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
		dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		dataSource.setUser("hseongchan2");
		dataSource.setPassword("448744");
		dataSource.setMaxPoolSize(200);
		dataSource.setCheckoutTimeout(60000);
		dataSource.setMaxIdleTime(1800);
		dataSource.setIdleConnectionTestPeriod(600);
		
		return dataSource;
	}
}
