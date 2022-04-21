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
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/7/22 10:21
 */
@Configuration
@MapperScan(basePackages = {"cn.yangwanhao.multipledatasource.mapper.base.**"}, sqlSessionFactoryRef = "baseSqlSessionFactory")
public class BaseDataSourceConfiguration extends DataSourceConfiguration {

    private static final String[] MAPPER_LOCATION = {"classpath*:cn/yangwanhao/multipledatasource/sql/xml/base/*.xml"};

    @Value("${spring.datasource.base.url}")
    private String url;
    @Value("${spring.datasource.base.username}")
    private String username;
    @Value("${spring.datasource.base.password}")
    private String password;
    @Value("${spring.datasource.base.driver-class-name}")
    private String driverClassName;

    @Autowired(required = false)
    private List<ConfigurationCustomizer> configurationCustomizers;

    @Bean(name = "baseDataSource")
    public DataSource baseDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean(name = "baseTransactionManager")
    public DataSourceTransactionManager baseTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseSqlSessionFactory")
    public SqlSessionFactory baseSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) throws Exception {
        return initSqlSessionFactoryBean(dataSource, configurationCustomizers, MAPPER_LOCATION).getObject();
    }

    @Bean(name = "baseTransactionTemplate")
    public TransactionTemplate baseTransactionTemplate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(baseTransactionManager(baseDataSource()));
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        return transactionTemplate;
    }

}
