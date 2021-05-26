#!/bin/bash

./mvnw package
java -jar target/cloud-gateway-0.1.0.jar 
