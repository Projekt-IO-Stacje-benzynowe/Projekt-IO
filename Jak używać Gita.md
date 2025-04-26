## Podstawy organizacji lokalnej wersji

Przede wszystkim zainspirowane [tą stroną](https://docs.github.com/en/migrations/importing-source-code/using-the-command-line-to-import-source-code/adding-locally-hosted-code-to-github).

Najpierw lokalnie tworzymy sobie gałąź `main`:
```
git init -b main
```
Commity lokalnie robimy w następujący sposób (najpierw dodajemy wszystkie pliki w danym folderze, a potem je commitujemy):

Najpierw powinniście się zautoryzować komendą `git config --global user.email "twój adres email"`.

Następnie dodajemy sobie nasz projekt jako `remote`:
```
git remote add origin https://github.com/Projekt-IO-Stacje-benzynowe/Projekt-IO.git
git remote -v
```

W tym momencie idealnie powinno dać się zrobić `git pull origin main`, żeby zsynchronizować lokalny folder z tym online.
Jeżeli będzie narzekać, zmieniamy to na `git pull --allow-unrelated-histories origin main`.

Teraz jesteśmy zaktualizowani, commity wykonujemy w przedstawiony poniżej sposób - najpierw dodajemy do wersji roboczej commita wszystkie lokalne pliki, a potem robimy commita
```
git add .
git commit -m "Nazwa commita"
```
Alternatywnie możemy pisać od razu `git commit -a -m "Nazwa commita"`.
Następnie wysyłamy je do repozytorium online poprzez `git push origin main`.

## Tworzenie własnej gałęzi

Tworzymy nową gałąż poprzez:
```
git checkout -b nazwa_gałęzi
```

`checkout` służy do przełączania się pomiędzy gałęziami, opcja `-b` oznacza tworzenie od razu gałęzi.

Następnie po przejściu do gałęzi main (`git checkout main`) możemy zaktualizować `main` z tej gałęzi poprzez `git merge nazwa_bocznej_krawędzi`. Możemy też ją usunąć za pomocą `git branch -d nazwa_usuwanej_krawędzi`.