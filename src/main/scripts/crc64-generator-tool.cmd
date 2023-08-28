@echo off

::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Launching script for LYS CRC-64 Generator Tool ::
::::::::::::::::::::::::::::::::::::::::::::::::::::

:: Check JAVA_HOME environment variable is set
if not defined JAVA_HOME (
    echo JAVA_HOME environment variable is not set
    echo You need to set it through the configuration panel:
    echo 1. Type keys 'Win' + R
    echo 2. Enter "sysdm.cpl" and validate
    echo 3. Go to "Advanced" tab
    echo 4. Click on "Environment variables"
    echo 5. Click "New"
    echo 6. Enter "JAVA_HOME" in the variable name field
    echo 7. Enter the path of the Java installation directory in the variable value field
    echo    This path is generally under C:\Program Files\Java
    pause
    exit /b
)

:: Java 
set JAVA_BINARY=%JAVA_HOME%\bin\javaw.exe

if not exist "%JAVA_BINARY%" (
    echo JAVA_HOME environment variable is set to "%JAVA_HOME%" but file "%JAVA_BINARY%" does not exist
    echo Make sure the environment variable is set properly
    pause
    exit /b
)

:: CRC64TOOL directories
set CRC64TOOL_HOME_DIR=%~dp0
:: Jar directories
set CRC64TOOL_LIB_DIR=%CRC64TOOL_HOME_DIR%bin

set CRC64TOOL_MAIN_CLASS=org.lys.apps.crcgenerator.CrcGeneratorTool

:: Execute application
start "CRC-64 Generation Tool" "%JAVA_BINARY%" -cp "%CRC64TOOL_LIB_DIR%\*" %CRC64TOOL_MAIN_CLASS%
