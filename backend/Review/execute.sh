#!/bin/bash

./gradlew bootjar
java -jar build/libs/review-v0.1.0.jar 
