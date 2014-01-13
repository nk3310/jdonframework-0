@echo off
rem E:
rem CD E:\h2\bin
rem @start javaw -cp "h2-1.3.174.jar;%H2DRIVERS%;%CLASSPATH%" org.h2.tools.Console %*
rem @if errorlevel 1 pause

rem start D:\indigo\eclipse\eclipse.exe
rem start explorer E:\src\git\nk3310\jdonframework\example\jdonMVC+CQRS+ES
rem start explorer E:\workspace\indigo\jdon\jdonmvc_cqrs_es
rem start explorer E:\jetty\logs

rem CD E:\jetty
rem del logs\jdon.log
rem cls
rem @call jetty.bat


@echo on
E:
CD E:\h2\bin
@start javaw -cp "h2-1.3.174.jar;%H2DRIVERS%;%CLASSPATH%" org.h2.tools.Console %*
@if errorlevel 1 pause

start E:\eclipse\indigo\eclipse\eclipse.exe
start explorer D:\src\git\nk3310\jdonframework\example\jdonMVC+CQRS+ES
start explorer D:\workspace\workspace37\jdon\jdonmvc_cqrs_es
start explorer E:\jetty\logs

CD E:\jetty
del logs\jdon.log
cls
@call jetty.bat
