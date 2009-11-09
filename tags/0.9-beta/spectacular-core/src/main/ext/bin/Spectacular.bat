@REM ----------------------------------------------------------------------------
@REM Spectacular Start Up Batch script
@REM Heavily stolen from the Maven2 Batch Startup Script, because those guys are 
@REM way smarter than me ;)
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir
@REM
@REM Optional ENV vars
@REM SPECTACULAR_HOME - location of Spectacular's installed home dir
@REM ----------------------------------------------------------------------------

@REM Begin all REM lines with '@' 
@echo off

@REM set %HOME% to equivalent of $HOME
if "%HOME%" == "" (set "HOME=%HOMEDRIVE%%HOMEPATH%")

@REM Execute a user defined script before this one
if exist "%HOME%\Spectacular_pre.bat" call "%HOME%\Spectacular_pre.bat"

set ERROR_CODE=0

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal
if "%OS%"=="WINNT" @setlocal

@REM ==== START VALIDATION ====
if not "%JAVA_HOME%" == "" goto OkJHome

echo.
echo ERROR: JAVA_HOME not found in your environment.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation
echo.
goto error

:OkJHome
if exist "%JAVA_HOME%\bin\java.exe" goto chkMHome

echo.
echo ERROR: JAVA_HOME is set to an invalid directory.
echo JAVA_HOME = "%JAVA_HOME%"
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation
echo.
goto error

:chkMHome
if not "%SPECTACULAR_HOME%"=="" goto valMHome

if "%OS%"=="Windows_NT" SET "SPECTACULAR_HOME=%~dp0.."
if "%OS%"=="WINNT" SET "SPECTACULAR_HOME=%~dp0.."
if not "%SPECTACULAR_HOME%"=="" goto valMHome

echo.
echo ERROR: SPECTACULAR_HOME not found in your environment.
echo Please set the SPECTACULAR_HOME variable in your environment to match the
echo location of the Spectacular installation
echo.
goto error

:valMHome

:stripMHome
if not "_%SPECTACULAR_HOME:~-1%"=="_\" goto checkMBat
set "SPECTACULAR_HOME=%SPECTACULAR_HOME:~0,-1%"
goto stripMHome

:checkMBat
if exist "%SPECTACULAR_HOME%\bin\Spectacular.bat" goto init

echo.
echo ERROR: SPECTACULAR_HOME is set to an invalid directory.
echo SPECTACULAR_HOME = "%SPECTACULAR_HOME%"
echo Please set the SPECTACULAR_HOME variable in your environment to match the
echo location of the Spectacular installation
echo.
goto error
@REM ==== END VALIDATION ====

:init
@REM Decide how to startup depending on the version of windows

@REM -- Windows NT with Novell Login
if "%OS%"=="WINNT" goto WinNTNovell

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

:WinNTNovell

@REM -- 4NT shell
if "%@eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set SPECTACULAR_CMD_LINE_ARGS=%*
goto endInit

@REM The 4NT Shell from jp software
:4NTArgs
set SPECTACULAR_CMD_LINE_ARGS=%$
goto endInit

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of agruments (up to the command line limit, anyway).
set SPECTACULAR_CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto endInit
set SPECTACULAR_CMD_LINE_ARGS=%SPECTACULAR_CMD_LINE_ARGS% %1
shift
goto Win9xApp

@REM Reaching here means variables are defined and arguments have been captured
:endInit
SET SPECTACULAR_JAVA_EXE="%JAVA_HOME%\bin\java.exe"

@REM -- 4NT shell
if "%@eval[2+2]" == "4" goto 4NTCWJars

@REM -- Regular WinNT shell
for %%i in ("%M2_HOME%"\boot\classworlds-*) do set CLASSWORLDS_JAR="%%i"
goto runm2

@REM The 4NT Shell from jp software
:4NTCWJars
for %%i in ("%M2_HOME%\boot\classworlds-*") do set CLASSWORLDS_JAR="%%i"
goto runm2

@REM Start SPECTACULAR
:runm2
%SPECTACULAR_JAVA_EXE% %SPECTACULAR_OPTS% -classpath %SPECTACULAR_HOME%\lib\spectacular-0.9-beta.jar minderupt.spectacular.spine.SpectacularRunner %SPECTACULAR_CMD_LINE_ARGS%
echo %SPECTACULAR_JAVA_EXE% %SPECTACULAR_OPTS% -classpath %SPECTACULAR_HOME%\lib\spectacular-0.9-beta.jar minderupt.spectacular.spine.SpectacularRunner %SPECTACULAR_CMD_LINE_ARGS%

if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
if "%OS%"=="WINNT" @endlocal
set ERROR_CODE=1

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT
if "%OS%"=="WINNT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set SPECTACULAR_JAVA_EXE=
set SPECTACULAR_CMD_LINE_ARGS=
goto postExec

:endNT
@endlocal & set ERROR_CODE=%ERROR_CODE%

:postExec
if exist "%HOME%\Spectacular_post.bat" call "%HOME%\Spectacular_post.bat"
@REM pause the batch file if SPECTACULAR_BATCH_PAUSE is set to 'on'
if "%SPECTACULAR_BATCH_PAUSE%" == "on" pause

if "%SPECTACULAR_TERMINATE_CMD%" == "on" exit %ERROR_CODE%

cmd /C exit /B %ERROR_CODE%

