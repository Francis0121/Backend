# Wiki-Backend

Wiki-Backend Proejct. Based on spring framework.

## Release History
PreRelease - 16.05.11 - v1.0.5

## Enabled

orm | database
---- | ----
mybatis | h2
mybatis | sqlite
jpa | h2

# Java Vm option
`-Dorm=jpa -Ddatabase=h2 -Duser.region=US -Duser.language=en`

# Library

* Spring Framework 4.3.0.RC1
	* core
	* jdbc
	* web
	* webmvc
	* aspects
	* test
* Json
	* com.fasterxml.jackson 2.7.4
* Database
	* org.serial.sqlite-jdbc 3.8.11.2
	* com.h2database 1.4.191
	* org.apache.tomcat 8.0.20
	* org.mybatis 3.4.0
		* org.mybatis.spring 1.3.0
	* spring.data.jpa 1.10.1.RELEASE
		* org.hibernate.javax.persistence 1.0.0.Final
		* org.hibernate 5.1.0.Final
* Validator
	* org.hibernate.validator 5.2.4.Final
	* javax.el 2.2
* Code Quality
	* lombok 1.16.8
* Logger : 
    * Slf4j 1.7.21
        * jcl-over-slf4j (enable spring log)
    * Logback 1.1.7
        * groovy 2.4.6
* Servlet
	* servlet-api 2.5
	* javax.servlet-api 3.1.0
* Test
	* Junit 4.12
	* Mockito 1.10.19
		* com.jayway.jsonpath 2.2.0
  		
## Database

* [Sqlite](https://www.sqlite.org/)
* [H2](http://www.h2database.com/html/main.html)

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
		* function ( such as board, account )
		* dto
	* exception
	* service
		* function ( such as board, account )
	* repository
        * entity
        * function ( such as board, account )
            * jdbc
            * mybatis
            * jpa
