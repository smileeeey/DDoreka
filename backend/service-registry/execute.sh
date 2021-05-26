#!/bin/bash

./mvnw package
java -jar target/service-registry-v0.1.0.jar

