## MongoDB
Run MongoDB and update the ip and port in props/application-test.properties file.

## Java

java -Dspring.profiles.active=test -Dserver.port=7010 -Dspring.config.location=props/ -jar build/libs/to-do-1.0.jar --debug --logging.path=logs/

## Docker  
docker-compose run --service-ports  -e SPRING_PROFILES_ACTIVE=dev -d pdd_core_feature_props2yml 
