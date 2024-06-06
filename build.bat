@echo off
setlocal

set src_dir=%~dp0
set temp_dir=%src_dir%temp
set bin_dir=%src_dir%bin
set lib_dir=%src_dir%lib

set jar_name=DAOREFLECT.jar

::create temp and bin they don't exist
if not exist "%bin_dir%" mkdir "%bin_dir%"
if not exist "%temp_dir%" mkdir "%temp_dir%"

:: copy the all file.java from src to temp
for /R "%src_dir%" %%f in (*.java) do (
    xcopy "%%f" "%temp_dir%" /I
)

::compile 
cd "%temp_dir%"
javac -d "%bin_dir%" -cp "%lib_dir%/*" "*.java"

::create jar
cd "%bin_dir%"
jar cvf "%jar_name%" *

:: move jar to testlib
move "%jar_name%" "../"

::delete temp   
rd /S /Q "%temp_dir%"
rd /S /Q "%bin_dir%"

echo %test_dir%
endlocal