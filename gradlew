#!/bin/sh

# Gradle start up script for POSIX compatible shells
APP_BASE_NAME=$(basename "$0")
APP_HOME=$(cd "$(dirname "$0")" && pwd)

# Use GRADLE_HOME if set, else find gradle on PATH
if [ -n "$GRADLE_HOME" ]; then
    GRADLE_CMD="$GRADLE_HOME/bin/gradle"
elif command -v gradle > /dev/null 2>&1; then
    GRADLE_CMD="gradle"
else
    # Try wrapper jar approach
    WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
    if [ -f "$WRAPPER_JAR" ]; then
        JAVA_CMD="java"
        if [ -n "$JAVA_HOME" ]; then
            JAVA_CMD="$JAVA_HOME/bin/java"
        fi
        exec "$JAVA_CMD" \
            -classpath "$WRAPPER_JAR" \
            org.gradle.wrapper.GradleWrapperMain \
            "$@"
    else
        echo "ERROR: Cannot find gradle. Install gradle or set GRADLE_HOME." >&2
        exit 1
    fi
fi

exec "$GRADLE_CMD" "$@"
