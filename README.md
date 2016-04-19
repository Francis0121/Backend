# Wiki-Backend

Wiki-Backend Proejct. Based on spring framework.
* Spring Framework 4.3.0.RC1
* Junit 4.12
* Logger : 
	* Slf4j 1.7.21
	* Logback 1.1.7
		* groovy 2.4.6
 		
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
    * sqlite
