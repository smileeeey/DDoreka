#!/bin/bash

./gradlew bootjar
java -jar build/libs/user-v0.1.0.jar 
