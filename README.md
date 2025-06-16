# System Zarządzania Promocjami

Narzędzie służące do koordynowania promocji w sieciach handlowych między pracownikami punktów sprzedaży, koordynatorami promocji i logistyki, oraz analitykami biznesowymi.

## Opis projektu

System służy do zarządzania akcjami promocyjnymi, gdzie klientom oferowane są czasowo nagrody w zamian za kupno odpowiedniej liczby sztuk wyznaczonych produktów.
To repozytorium realizuje interfejs graficzny użytkownika, jak również integrację z systemem kasowym sieci handlowej przez API w języku Java operującego plikami.
Interfejs służy użyciu przez zarówno pracowników kasowych punktów sprzedaży, jak również i pracowników odpowiedzialnych za organizację różnych procesów związanych z promocją i jej analizą w generowanych plikach PDF.
Zapewnia on automatyczne przekazywanie odpowiednich informacji między użytkownikami a centralną bazą danych MySQL, co ma na celu usprawnienie pracę pracowników sieci handlowej związanych z realizacją promocji.

## Jak skompilować aplikację

Do obu kompilacji wymagana jest zainstalowana wersja 24.0.1 Java.

### Kompilacja bez Maven/JavaFX

Należy w folderze ze sklonowanym repozytorium wykonać następujące polecenia w wierszu poleceń:
```
cleanInstall.bat
startIO.bat
```
To powinno wywołać pobranie niezbędnych zależności JavaFX i Maven, kompilację do pliku `target/app-java-1.0-SNAPSHOT.jar` i uruchomienie aplikacji.

### Kompilacja z użyciem Maven i JavaFX

Najpierw należy pobrać Java Development Kit (wersja 24.0.1), JavaFX (wersja 24.0.1) i Maven (wersja 4.0.0), ustawiając przy tym odpowiednie wartości zmiennej środowiskowej `PATH`.
Następnie w folderze ze sklonowanym repozytorium należy wykonać polecenie
```
mvn clean install
```
Potem można uruchomić polecenie `mvn package` w celu kompilacji pliku `target/app-java-1.0-SNAPSHOT.jar` lub `mvn javafx:run` w celu kompilacji tego samego pliku i jego natychmiastowego uruchomienia.

## Instrukcja obsługi

Szczegółowa instrukcja zawarta jest w pliku `docs/Instrukcja obsługi.pdf`.

## Twórcy

Autorami projektu są Antoni Blicharz, Szymon Dybał, Jakub Koszorz, Mikołaj Mroczek i Diego Ostoja-Kowalski.
Kontakt należy podejmować z [Jakubem Koszorzem](https://github.com/JamesDvez), liderem projektu, lub [Diegiem Ostoją-Kowalskim](https://github.com/d13g0h0h0), osobą utrzymującą projekt.
