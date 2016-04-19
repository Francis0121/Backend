# Wiki-Backend

Wiki-Backend Proejct. Based on spring framework.
* Spring Framework 4.3.0.RC1
	* core
	* jdbc
	* web
	* test
* Database
	* org.serial.sqlite-jdbc 3.8.11.2
	* org.apache.tomcat 8.0.20
* Test
	* Junit 4.12
		* Logger : 
		* Slf4j 1.7.21
		* Logback 1.1.7
			* groovy 2.4.6
  		
## Database

* [Sqlite](https://www.sqlite.org/)

## Database Template

* Spring JDBC Template
		
## Logging
  
Use `logback.groovy`.
  
## Package Architecture

* me.ppangya.me.framework
  * config
    * RootApplication
    * DispatcherServlet

* me.ppangya.me.backend
  * controller
  * facade
  * service
  * repository
    * entity
    * function ( such as board, account )
        * sqlite
