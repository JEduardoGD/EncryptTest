@echo off
cls

rem ECHO =========== COMPILE ==============
rem set MAVEN_HOME=C:\sft\apache-maven-3.0.5
rem set JAVA_HOME=C:\Program Files\Java\jdk1.6.0_45
rem 
rem set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
rem set M2_HOME=%MAVEN_HOME%
rem 
rem call mvn -v
rem 
rem call mvn -o --offline -Dmaven.test.skip=true -Pprod clean package install

ECHO =========== SONAR ==============
    set MAVEN_HOME=C:\sft\apache-maven-3.6.3
    set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_251

set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
set M2_HOME=%MAVEN_HOME%

call mvn -v

call mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=admin -Dsonar.password=admin

ECHO ==========================================================
ECHO =========== C O M P I L A T I O N   E N D S ==============
ECHO ==========================================================

pause