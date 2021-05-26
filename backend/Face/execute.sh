#!/bin/bash

./gradlew bootjar
java -jar build/libs/face-v0.1.0.jar 
