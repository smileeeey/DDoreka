#!/bin/bash

./gradlew bootjar
java -jar build/libs/file-v0.1.0.jar 
