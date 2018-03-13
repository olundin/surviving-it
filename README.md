# Surviving It with Yuri Doroshenko

## Ett TDDE30 projekt av Angus Lothian och Oskar Lundin

### 1. Introduktion till projektet
Vi ska utveckla ett spel som handlar om överlevnad. Spelet ska vara i 2D, tile-baserat, och ritas “top-down”, ovanifrån spelaren.

Spelaren rör sig runt i en värld med piltangenterna och interagerar med sin omgivning på olika sätt. Målet med spelet är att utforska och överleva. Det sistnämnda görs genom att hitta mat att äta och vapen att döda fiender med. Spelaren har från början endast hp som går ner när spelaren tar skada, sedan läggs till hunger som går ner konstant och som går upp när spelaren äter. När hunger går ner till noll så börjar man ta hp skada. Sedan läggs även till värme som går ner långsamt och som man tar skada av när den når noll, värmen går upp när man sitter nära en lägereld.

Grunden blir att implementera spelar rörelse, kollisionshantering och en enklare värld för spelaren att rör sig i. I mån av tid lägger vi till fiender, föremål, ett stridssystem med mera.

Det ska finnas både fiender som försöker döda spelaren och passiva djur som inte attackerar spelaren. Exempel på fiender som spelaren kan möta på är en björn eller en varg och exempel på passiva djur är en älg. De dödade djuren ska ge spelaren mat.

Exempel på vapen är svärd, pilbåge och pinne.

Om vi får tid vill vi även implementera slumpmässig terrain generation med Perlin Noise algoritm.

### 2. Ytterligare bakgrundsinformation
Som sagt är spelet top-down och tile-baserat. Detta innebär att allting ritas ovanifrån. Att det är tile-baserat innebär att spelvärlden är uppbyggd av mindre fyrkantiga brickor. Fördelen med detta är att man kan återanvända samma bilder flera gånger.

Ett exempel på ett sådant spel är The Legend of Zelda: A Link to the Past.
Se bild:

![Zelda top down example](http://application.denofgeek.com/pics/games/zeldastyle/03.jpg)

Mer info om Perlin noise algoritmen som vi vill använda för slumpmässig terrain generation finns här: https://www.redblobgames.com/maps/terrain-from-noise/

Vi har också tänkt använda A* search algoritm för hur djuren ska kunna hitta runt i världen, mer info om det finns här: https://www.redblobgames.com/pathfinding/a-star/introduction.html

Vi har också tänkt modella djuren som simpla finite statemachines, mer info om det finns här: https://gamedevelopment.tutsplus.com/tutorials/finite-state-machines-theory-and-implementation--gamedev-11867

### 3. Milstolpar

| **#** | **Beskrvining** | **Status** |
|-------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|
| 1 | Det finns en spelloop där vi uppdaterar logik och ritar saker. Vi kan rita en bild på skärmen på vald position. Vi har en fönsterklass, en ritarklass och en spelklass som hanterar dessa. | ✅ |
| 2 | Den grundläggande klasshierarkin är klar med scener, spelobjekt och tiles. Alla dessa kan ritas på olika positioner. Vi har ett objekt för spelaren som kan styras av tangentbordet och en pekar input som styrs av musen. | In progress |
| 3 | Spelet har kollisionshantering för tiles. Till exempel finns det vissa tiles som man inte kan gå över. Vi testar med hål tiles som spelaren inte kan gå över. |  |
| 4 | Spelet har kollisionshantering för spelobjekt med hitboxes. Vi testar detta genom att skapa ett trädobjekt som spelaren inte kan gå över. |  |
| 5 | Items finns i spelet. Dessa kan ligga i “containers” som kistor eller i spelarens ryggsäck. |  |
| 6 | Andra spelobjekt, såsom djur och fiender, finns i världen som rör sig omkring fritt. Djuren är programmerade som simpla finite statemachines och de använder A* search algoritm för att ta sig runt i världen. |  |
| 7 | Grundläggande interaktion mellan spelobjekt finns genom meddelande delande. Testas genom att ta skada från något. |  |
| 8 | Konvertering från items till spelobjekt finns (och viceversa). |  |
| 9 | Funktionalitet för strid som bygger på interaktion mellan spelobjekt,finns i relevanta spelobjekt som t.ex. i spelaran och i en ny spelobjektstyp björn. Detta kan testas genom att spelaren slåss mot björnen.. |  |
| 10 | Vi skapar ett antal flera varelser (både neutrala och fiender), items och växter som ger spelet djup |  |
| 11 | Mer survival element som hunger, kyla, ev. mer, läggs till i spelaren. |  |
| 12 | Grundläggande HUD för hp, hunger, kyla. |  |
| 13 | Grundläggande HUD för inventory. |  |
| 14 | Spelaren kan skapa lägereld. |  |
| 15 | Funktionalitet för serialisering av spelobjekten finns för att kunna spara. |  |
| 16 | Funktionalitet för att kunna ladda sparade spel. |  |
| 17 | Sprites kan använda animationer. |  |
| 18 | Slumpgenererade nivåer med Perlin Noise algorithm. |  |
| 19 | Mer avancerade jakt mechanics som att man följer fotspår. |  |
| 20 | Graphics renderas via OpenGL med shapes. |  |

### 4. Övriga implementationsförberedelser
Vi tänker ha en spelklass, som innehåller spelloopen. Denna har fält för allting som hör till spelet och uppdaterar dessa. Spelklassen har en fönsterklass, som hanterar fönstret där allting syns.

Allting ritas med hjälp av en ritarklass, som har färdiga funktioner för att rita bilder, text o.s.v. Denna samarbetar med fönsterklassen, förslagsvis genom att allting ritas till fönstret via ritarklassen. För att rita saker skickas ritarklassen till ritafunktionen hos allt som ska ritas.

Spelklassen har också en scenklass som innehåller det som ska synas på skärmen. En scen innehåller en array av Tiles, som motsvarar banan, samt en lista av spelobjekt. Ett spelobjekt är en så kallad aktör i spelet. Det kan vara ett djur, en blomma eller spelaren - allting som kräver mer avancerad funktionalitet och interagerar med saker lite mer än bara genom kollisioner.

För kommunikation mellan olika spelobjekt använder vi oss av ett slags meddelandesystem. Alla spelobjekt har en funktion för att ta emot ett meddelande. Ett meddelande är en klass som har en typ och data. Funktionen receive kan sedan hantera meddelandet som skickas på lämpligt sätt.

### 5. Utveckling och samarbete
Vi tänker använda oss av Git för versionshantering. För att ha koll på vad som behöver göras använder vi Trello.

I början av projektet tänker vi göra allting tillsammans, för att båda ska få en förståelse för hur allting fungerar. När vi sedan kommer till mer specifika saker kan vi börja dela upp arbetet.

Vi träffas på alla schemalagda tider för att arbeta tillsammans och uppdatera varandra. Annars jobbar vi lite som vi vill. Vi bestämmer vissa tider som vi kör “hackathons” då vi träffas utanför schemalagd tid.

Vi siktar på betyg 5.
