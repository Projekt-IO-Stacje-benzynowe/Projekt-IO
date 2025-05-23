## O projekcie

Celem tego projektu jest stworzenie zestawu narzędzi do obsługi promocji czasowych przez firmy t.j. stacje benzynowe.

## Kompilacja

Do kompilacji potrzebna jest zainstalowana Java w wersji 22 lub późniejszej (do pobrania [tutaj](https://www.oracle.com/java/technologies/downloads/)), jak również SDK JavaFX w wersji 24.0.1 (do pobrania [tutaj](https://gluonhq.com/products/javafx/)). W systemie Windows zalecane jest utworzenie zmiennej środowiskowej `PATH_TO_FX` kierującej do folderu `javafx-sdk-24.0.1/lib`, wówczas w wierszu poleceń można skompilować kod źródłowy w następujący sposób:
```
javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -d out src\*.java
```