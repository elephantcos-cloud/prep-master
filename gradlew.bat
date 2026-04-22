@rem Gradle startup script for Windows
@if "%DEBUG%"=="" @echo off
@rem Set local scope for variables
setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.

if defined GRADLE_HOME goto findJavaFromGradleHome
if defined JAVA_HOME goto findGradle

:findGradle
where gradle >nul 2>nul
if %errorlevel% == 0 goto executeGradle

echo ERROR: Cannot find gradle. Install gradle or set GRADLE_HOME. 1>&2
goto fail

:findJavaFromGradleHome
set GRADLE_CMD=%GRADLE_HOME%\bin\gradle

:executeGradle
%GRADLE_CMD% %*
goto end

:fail
exit /b 1

:end
