#!/bin/bash

./gradlew bootjar
java -jar build/libs/product-v0.1.0.jar 
