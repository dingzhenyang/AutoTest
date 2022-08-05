
@echo off

taskkill /f /t /im javaw.exe

ping 127.0.0.1 -n 5

start javaw -jar moco-runner-1.3.0-standalone.jar http -p 9000 -c userManager.json

exit