package cn.yangwanhao.multipledatasource.config.datasource;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author 杨万浩
 * @version V1.0
 * @since 2021/7/22 10:34
 */
public class DataSourceConfiguration {

    protected SqlSessionFactoryBean initSqlSessionFactoryBean(DataSource dataSource,
        List<ConfigurationCustomizer> configurationCustomizers, String[] mapperLocation) throws IOException {

        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        Configuration configuration = new Configuration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        if (!CollectionUtils.isEmpty(configurationCustomizers)) {
            for (ConfigurationCustomizer configurationCustomizer : configurationCustomizers) {
                configurationCustomizer.customize(configuration);
            }
        }
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setDataSource(dataSource);
        Set<Resource> resources =  new LinkedHashSet<>(16);
        if (mapperLocation != null && mapperLocation.length != 0) {
            for (String location : mapperLocation) {
                resources.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources(location)));
            }
        }
        sqlSessionFactoryBean.setMapperLocations(resources.toArray(new Resource[0]));
        return sqlSessionFactoryBean;
    }

}
