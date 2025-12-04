#!/bin/sh
# Minimal Maven Wrapper script for environments without mvn installed.
BASEDIR="$(cd "$(dirname "$0")" && pwd)"
JAVA_CMD="${JAVA_HOME:+$JAVA_HOME/bin/java}"
JAVA_CMD="${JAVA_CMD:-java}"
WRAPPER_JAR="$BASEDIR/.mvn/wrapper/maven-wrapper.jar"

exec "$JAVA_CMD" "-Dmaven.multiModuleProjectDirectory=$BASEDIR" -cp "$WRAPPER_JAR" org.apache.maven.wrapper.MavenWrapperMain "$@"
