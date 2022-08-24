#### MindTickle Automation Project

This project contains code base for MindTickle automation assignment.Under this, we are writing end to end automated tests for user and pet functionality along with unit tests to test automated scripts.


### **Built With**

* Maven - Dependency management
* TestNG - Testing framework
* Allure Reports - Reporting framework
* Scripting Language- Java
* Unit tests - TestNG and Mockito framework


## Local development environment Setup

#### **Setting up your IDE (IntelliJ)**
    1. Install JDK 11 for windows using "https://www.oracle.com/java/technologies/downloads/"
    2. Install Maven from "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.5.4/"
    3. Set environment variables path for both already installed JDK and Maven. 
    3. Install Git from this location "https://git-scm.com/download/win"
    4. Download and configure IntelliJ preferebly 2020.2 version to point to the right Java SDK version 11
    5. Configure IntelliJ to point to the right Maven version installed and also ensure maven importing is set to automatic setting.
    6. Make sure to have the Maven IntelliJ plugins installed through market place.
    7. Ensure downloading allure reports binaries and save it on your machine.Set environment path variable for the same. 
    8. Pull the code from gitlab using command "git clone https://github.com/ariz05/mindtickleautomation.git"
              
                

#### **Execution without using maven maven commands -**
    1. If user choose to execute the project without maven, it can certainly be done by right clicking on the TestNg.xml file under project root directory. 
    2. Click on 'Run TestNg.xml' 



#### **Execution using maven through the commandline –**

#As this project uses Maven, we can invoke the tests using Maven goals.

    1. Pull the code from github using command "git clone https://github.com/ariz05/mindtickleautomation.git"
    2. To run the test,point Maven to the project and use the goals:
       - clean test



#### **Tool / Technologies Leveraged –**

|Tool|Usage|

|GitHub Enterprise -> Acts as a code repository for maintaining the framework, automation test scripts and reports with help of version  controlling to avoid any data loss.|
|IntelliJ          -> Used as a developer editing tool for designing/maintaining automation framework.|
|Maven             -> Is a build tool used internally to run the build and validate the build status.|
|Scripting         -> Java language used for writing scripts.|
|Java.net          -> Library used for writing the automation scripts for APIs|
|TestNG            -> For managing integration tests and unit tests|
|Extent Reports    -> To generate a detailed and userDto-friendly report with some good aesthetics.|



#### **Platforms Supported -**

	- OS supported
	      - Windows

#### **Execution Reporting –**
      1.Allure Reports – Used for generating graphical html test execution reports. Our framework supports some extensive reports like -  
           - Allure Reports from qameta
           - Source-directory/allure-results/allure-report.html
           After execution - open cmd and run command - 'allure serve source-directory-path\allure-results'
      2.Default Surefire report (HTML Only)
                - target/surefire/index.html	
       
		 

#### **About project files/directories –**

##This project is built on Maven build tool. It consists of below components:

1. src module    ->   This module keeps test cases , dtos , test data ,helpers and common utilities that will be needed for the framework
 		             to support GUI automation.

2. Configuration ->   This directory contains config.properties file that keeps property details like baseurl of the environment,etc.
		             More details can be included and leveraged in future for better parameterization.
		     
3. extent-config ->   This file comprises details such as extentreports configuration details like theme,encoding,protocol,document title,report name,test-view chart 
		             location,etc. being used in report.

4. pom           ->   This file contains dependencies and plugins used in the project.

5. TestNG.xml    ->   This file contains information about the test suites , test classes and test methods we have written and wants to execute . We can group them according 
		             our requirement . This contains some parameters as well that are going to be used for applicable test cases during test execution. 

6. src/test/java ->   This directory contains subdirectories that contains code to perfrom different actions during execution.Subdirectories are below :
 
                      DTOS - contains pojo classes for different request and response API signatures.

		              TestCases   - contains test cases for API testing.It has BaseClass which is inherited extended by other tests classes.Comprises
                                      TestNG test cases and flow .  

                      TestData    - comprises different test data for API testing scenarios under excelsheet TestData.xlsx
                      
                      Helpers    - comprises assertion methods for different APIs.

		              Utilities   - contains different java classes which has some common utility methods that can be used across the framework to perform similar 
                                      tasks at different steps.

7. allure-results ->   Directory contains allure report config files.


 
### Scope of enhancements

* Jenkins tool integration for continuous integration.
* Allure report integration with jenkins using plugin.
* Mutiple environment support to run the suite on QA/Non-prod/prod environments.
* Code coverage can be improved by adding some more unit tests.
* More end to end test scenarios can be designed and added to improve quality.   		 
           