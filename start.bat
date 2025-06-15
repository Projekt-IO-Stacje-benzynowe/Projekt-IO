@echo off
start "" javaw --module-path "javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar target\app-java-1.0-SNAPSHOT.jar
exit