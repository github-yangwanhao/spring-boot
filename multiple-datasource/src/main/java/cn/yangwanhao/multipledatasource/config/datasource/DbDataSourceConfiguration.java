package cn.yangwanhao.multipledatasource.config.datasource;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/7/22 10:21
 */
@Configuration
@MapperScan(basePackages = {"cn.yangwanhao.multipledatasource.mapper.db.**"}, sqlSessionFactoryRef = "dbSqlSessionFactory")
public class DbDataSourceConfiguration extends DataSourceConfiguration {

    private static final String[] MAPPER_LOCATION = {"classpath*:cn/yangwanhao/multipledatasource/sql/xml/db/*.xml"};

    @Value("${spring.datasource.db.url}")
    private String url;
    @Value("${spring.datasource.db.username}")
    private String username;
    @Value("${spring.datasource.db.password}")
    private String password;
    @Value("${spring.datasource.db.driver-class-name}")
    private String driverClassName;

    @Autowired(required = false)
    private List<ConfigurationCustomizer> configurationCustomizers;

    @Bean(name = "dbDataSource")
    public DataSource dbDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean(name = "dbTransactionManager")
    public DataSourceTransactionManager dbTransactionManager(@Qualifier("dbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dbSqlSessionFactory")
    public SqlSessionFactory dbSqlSessionFactory(@Qualifier("dbDataSource") DataSource dataSource) throws Exception {
        return initSqlSessionFactoryBean(dataSource, configurationCustomizers, MAPPER_LOCATION).getObject();
    }

}
