# Wiki-Backend

Wiki-Backend Proejct. Based on spring framework.
* Spring Framework 4.3.0.RC1
	* core
	* jdbc
	* web
	* webmvc
	* aspects
	* test
* Database
	* org.serial.sqlite-jdbc 3.8.11.2
	* org.apache.tomcat 8.0.20
	* org.mybatis 3.4.0
		* org.mybatis.spring 1.3.0
	* spring.data.jpa 1.10.1.RELEASE
		* org.hibernate.javax.persistence 1.0.0.Final
		* org.hibernate 5.1.0.Final
* Code Quality
	* lombok 1.16.8
* Test
	* Junit 4.12
		* Logger : 
		* Slf4j 1.7.21
	 		* jcl-over-slf4j (enable spring log)
		* Logback 1.1.7
			* groovy 2.4.6
  		
## Database

* [Sqlite](https://www.sqlite.org/)

## Database Template

* Spring JDBC Template [[Link] Docs](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html)
* Mybatis Template [[Link] Docs](http://www.mybatis.org/mybatis-3/)
* Spring data JPA [[Link] Docs](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
		
## Logging
  
Use `logback.groovy`.
  
## Package Architecture

* me.ppangya.me.framework
	* annotation
		* DatabaseConditional
		* OrmConditional
	* config
		* RootApplication
		* DataSourceConfig
		* JdbcTransactionMangerConfig
		* MybatisTransactionMangerConfig
		* JpaTransactionMangerConfig
		* DispatcherServlet
	* constant
		* SystemProperties
			* ObjectRelationalMapping
			* Database
	* util

* me.ppangya.me.backend
	* controller
	* facade
	* service
	* repository
    * entity
        * function ( such as board, account )
            * jdbc
            * mybatis
            * jpa
