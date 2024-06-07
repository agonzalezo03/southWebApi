#!/usr/bin/env bash

# Descargar e instalar Maven
MAVEN_VERSION=3.8.6
wget https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz
tar xzvf apache-maven-$MAVEN_VERSION-bin.tar.gz
export M2_HOME=$PWD/apache-maven-$MAVEN_VERSION
export PATH=$M2_HOME/bin:$PATH

# Verificar la instalación de Maven
mvn -version
