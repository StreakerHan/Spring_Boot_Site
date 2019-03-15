package com.hyp.config;

import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.github.pagehelper.PageInterceptor;

/**
 * 

* @Title: DataSourceConfig  

* @Description:   

* @author HanYupeng  

* @date 2018-06-13 14:48
 */
@Configuration
@MapperScan("com.hyp.dao") //通过使用@MapperScan可以指定要扫描的Mapper类的包的路径
@EnableTransactionManagement //开启事务管理
public class DataSourceConfig{
	//Environment可以方便的访问property属性，包含系统属性，环境变量和自定义的
	@Autowired
	private Environment env;
	
	@Autowired
	private PageInterceptor pageInterceptor;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(dataSource);
		//该配置非常重要，如果不将PageIbterceptor设置到SqlSessionFactoryBean中，导致分页失效
		fb.setPlugins(new Interceptor[]{pageInterceptor});
		fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
		fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
		return fb.getObject();
	}
}
