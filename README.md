# homeworkone
gradle clean -Pbrowser=firefox -DverifyUI=true -Pplatform=mac -Ptarget=remote testGroups

p.s.
1 - java -jar selenium-server-standalone-3.141.59.jar -role hub
2 - java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.2.10:4444/grid/register