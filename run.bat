@echo off & set a="0"
javac FTS.java -Xdiags:verbose -Xlint:deprecation -Xlint:unchecked && java FTS && set a="1"
if %a%=="0" set /p id=""