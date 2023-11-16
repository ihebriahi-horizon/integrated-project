#!/bin/bash

env_vars="
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
"

for var in $env_vars
do
    if [ -z $(eval echo \$$var) ]; then
        echo -e "\033[31mError: $var is not set.\033[0m"
        exit 1
    else
        echo -e "\033[32m$var is set.\033[0m"
    fi
done

echo -e "\033[32mAll environment variables are set.\033[0m"

mvn package

java -jar /app/target/Back-1.0.0.jar
