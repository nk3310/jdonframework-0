E:
CD E:\h2\bin
@start javaw -cp "h2-1.3.174.jar;%H2DRIVERS%;%CLASSPATH%" org.h2.tools.Console %*
@if errorlevel 1 pause

start D:\indigo\eclipse\eclipse.exe
start explorer E:\src\git\nk3310\jdonframework\example\jdonMVC+CQRS+ES
start explorer E:\workspace\indigo\jdon\jdonmvc_cqrs_es
start explorer E:\jetty\logs

CD E:\jetty
call jetty.bat
