#!/bin/bash

./gradlew bootjar
java -jar build/libs/order-v0.1.0.jar 
