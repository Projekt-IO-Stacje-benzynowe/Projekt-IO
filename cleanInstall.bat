@echo off
setlocal

REM ---- KONFIGURACJA ----
set "MAVEN_ZIP=apache-maven-3.9.10-bin.zip"
set "MAVEN_DIR=apache-maven-3.9.10"

REM ---- SPRAWDZENIE CZY JEST JAVA ----
java -version >nul 2>&1
if errorlevel 1 (
    echo [BLAD] Java nie jest zainstalowana lub nie jest w PATH.
    pause
    exit /b 1
)

REM ---- ROZPAKOWANIE MAVENA (JEŚLI NIE MA) ----
goto maven

:maven
REM ---- USTAWIENIE MAVENA LOKALNIE ----
set "MAVEN_HOME=%CD%\%MAVEN_DIR%"
set "PATH=%MAVEN_HOME%\bin;%PATH%"
echo [INFO] Uzywam lokalnego Mavena:
call "%MAVEN_HOME%\bin\mvn" -version

REM ---- BUDOWA PROJEKTU ----
echo [INFO] Rozpoczynam budowanie projektu...
mvn clean install

echo [INFO] Gotowe. Wcisnij dowolny klawisz, aby zamknac.
pause
endlocal
 exit /b 1