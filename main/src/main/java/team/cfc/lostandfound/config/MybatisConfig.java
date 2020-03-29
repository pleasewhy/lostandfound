package team.cfc.lostandfound.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"team.cfc.lostandfound.mapper","team.cfc.lostandfound.dao"})
public class MybatisConfig {
}