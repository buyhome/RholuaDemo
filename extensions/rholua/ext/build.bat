
if "%RHO_PLATFORM%" == "android" (
echo ** Location of TARGET_TEMP_DIR is: %TARGET_TEMP_DIR%
echo ** Removing it and recreating.
echo

rmdir /s /q "%TARGET_TEMP_DIR%"
mkdir "%TARGET_TEMP_DIR%"

echo ** Copying our non Rho jar files to the TARGET_TEMP_DIR location
cd ..\libs
copy *.jar "%TARGET_TEMP_DIR%"
copy *.so "%TARGET_TEMP_DIR%"
cd ..\ext\rholua\platform\android
rake --trace

)

if "%RHO_PLATFORM%" == "iphone" (

cd rholua\platform\phone
rake --trace

)

if "%RHO_PLATFORM%" == "wm" (

cd rholua\platform\wm
rake --trace

)

if "%RHO_PLATFORM%" == "win32" (

cd rholua\platform\wm
rake --trace

)

if "%RHO_PLATFORM%" == "bb" (

cd rholua\platform\bb
rake --trace

)

