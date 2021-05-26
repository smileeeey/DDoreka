#!/bin/bash

./gradlew bootjar
java -jar build/libs/seller-v0.1.0.jar 
