#!/bin/sh

DIR="$(dirname $0)"

mvn -q exec:java             \
    -f "${DIR}/../pom.xml"   \
    -Dexec.mainClass=mx.unam.ciencias.modelado.proyecto2.ServidorProyecto2 -Dexec.args="$*"
