#!/bin/bash
# Instalar Maven
apt-get update -y
apt-get install -y maven

# Fazer o build com Maven
mvn clean install
