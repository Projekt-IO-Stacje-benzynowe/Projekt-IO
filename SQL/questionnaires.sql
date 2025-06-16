DROP TABLE IF EXISTS Answers;
DROP TABLE IF EXISTS Responses;
DROP TABLE IF EXISTS Options;
DROP TABLE IF EXISTS Questions;
DROP TABLE IF EXISTS Surveys;

CREATE TABLE Surveys (
    id INTEGER PRIMARY KEY,
    title TEXT NOT NULL
);

CREATE TABLE Questions (
    id INTEGER PRIMARY KEY,
    survey_id INTEGER REFERENCES Surveys(id),
    question_text TEXT NOT NULL,
    type TEXT NOT NULL CHECK (type IN ('scale', 'single', 'text'))
);

CREATE TABLE Options (
    id INTEGER PRIMARY KEY,
    question_id INTEGER REFERENCES Questions(id),
    text TEXT NOT NULL
);

CREATE TABLE Responses (
    id INTEGER PRIMARY KEY,
    user_id INTEGER NOT NULL,
    survey_id INTEGER REFERENCES Surveys(id),
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Answers (
    id INTEGER PRIMARY KEY,
    response_id INTEGER REFERENCES Responses(id),
    question_id INTEGER REFERENCES Questions(id),
    option_id INTEGER,
    text_answer TEXT
);
INSERT INTO Surveys (id, title) VALUES
(1, 'Promocje'),
(2, 'Produkty'),
(3, 'Zadowolenie z produktów');

INSERT INTO Questions (id, survey_id, question_text, type) VALUES
(1, 1, 'Czy często korzystasz z promocji?', 'single'),
(2, 1, 'Jak oceniasz przejrzystość promocji?', 'scale'),
(3, 1, 'Czy promocje są atrakcyjne?', 'scale'),
(4, 1, 'Czy informacja o promocjach jest łatwo dostępna?', 'scale'),
(5, 1, 'Czy promocje skłaniają Cię do zakupów?', 'scale'),
(6, 1, 'Czy korzystasz z kuponów rabatowych?', 'single'),
(7, 1, 'Czy ceny promocyjne są uczciwe?', 'scale'),
(8, 1, 'Czy korzystasz z promocji online?', 'single'),
(9, 1, 'Czy aplikacja pokazuje promocje?', 'single'),
(10, 1, 'Co sądzisz o naszych promocjach?', 'text');

INSERT INTO Questions (id, survey_id, question_text, type) VALUES
(11, 2, 'Czy uważasz nasze produkty za wysokiej jakości?', 'scale'),
(12, 2, 'Czy nasze produkty są dostępne w Twojej okolicy?', 'single'),
(13, 2, 'Jak oceniasz stosunek ceny do jakości?', 'scale'),
(14, 2, 'Czy produkty spełniły Twoje oczekiwania?', 'scale'),
(15, 2, 'Czy kupiłbyś nasze produkty ponownie?', 'single'),
(16, 2, 'Jak oceniasz wygląd/opakowanie produktów?', 'scale'),
(17, 2, 'Czy nasze produkty są łatwe w użyciu?', 'scale'),
(18, 2, 'Czy poleciłbyś nasze produkty innym?', 'single'),
(19, 2, 'Jak oceniasz dostępność informacji o produkcie?', 'scale'),
(20, 2, 'Jakie masz sugestie co do naszych produktów?', 'text');

INSERT INTO Questions (id, survey_id, question_text, type) VALUES
(21, 3, 'Czy jesteś zadowolony z zakupu?', 'single'),
(22, 3, 'Na ile oceniasz jakość produktu?', 'scale'),
(23, 3, 'Czy produkt działa zgodnie z opisem?', 'scale'),
(24, 3, 'Czy obsługa klienta pomogła Ci w razie problemu?', 'single'),
(25, 3, 'Czy produkt był dobrze zapakowany?', 'single'),
(26, 3, 'Na ile oceniasz czas dostawy?', 'scale'),
(27, 3, 'Jak oceniasz trwałość produktu?', 'scale'),
(28, 3, 'Czy produkt spełnia Twoje potrzeby?', 'scale'),
(29, 3, 'Czy kupisz inne produkty tej marki?', 'single'),
(30, 3, 'Co najbardziej lubisz lub zmieniłbyś w produkcie?', 'text');





