package com.bp.learnblog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

/**
 * 连接池 Druid 配置 以及 Mybatis 配置
 *
 * @author DH
 */
@EnableTransactionManagement
@Configuration
public class DruidConfig {
    /**
     * 绑定 application.yml 文件中 druid 的配置
     *
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(name = "druidDataSource", destroyMethod = "close", initMethod = "init")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

//    /**
//     * 设置 sqlSession 连接工厂
//     *
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(druidDataSource());
//        // 指定 Mapper 接口的路径（若为注解方式则不用）
////        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
////                .getResource("/mapper/**/*.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }

    /**
     * 设置后台管理界面访问 Servlet
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean<Servlet> statViewServlet() {
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*");
        // 设置基本参数(这些参数是 Druid 初始化固定的，可见 ResourceServlet类，需要注意不能随便写)
        Map<String, String> initParams = new HashMap<>();
        // 设置登录用户密码
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        // 设置可访问的域名（"" 表示所有可以， "127.0.0.1" 则表示本机访问）
        initParams.put("allow", "");
        // 设置拒绝访问
        // initParams.put("deny", "");

        registrationBean.setInitParameters(initParams);
        return registrationBean;
    }

    /**
     * 配置 Druid 监控的 web 监控的过滤器 Filter
     * WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<Filter> statViewFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        // 设置过滤器的过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        // 这些参数在 WebStatFilter 类中
        // 设置不进行统计过滤的内容
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }
}
