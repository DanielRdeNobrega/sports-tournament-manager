# Sports Tournament Manager

**Java · Object-Oriented Programming · Command-Line Interface · Design Patterns**

## Overview / Descripción

**EN:** Team-developed academic application for managing sports tournaments through a Java command-line interface. The code models players, teams, tournaments and matches, with separate commands for administrators and players. The implementation uses object-oriented design and strategy-based matchmaking.

**ES:** Aplicación académica desarrollada en equipo para gestionar torneos deportivos mediante una interfaz de línea de comandos en Java. El código modela jugadores, equipos, torneos y partidos, con comandos diferenciados para administradores y jugadores. Emplea diseño orientado a objetos y estrategias de emparejamiento.

## Features / Funcionalidades

- Create and remove players, teams and tournaments. / Crear y eliminar jugadores, equipos y torneos.
- Add and remove players from teams. / Añadir y retirar jugadores de equipos.
- Register or remove tournament participants. / Inscribir y retirar participantes de torneos.
- Display tournaments and player statistics. / Consultar torneos y estadísticas de jugadores.
- Administrator and player command sets, including login and logout. / Comandos diferenciados por rol, con inicio y cierre de sesión.
- Matchmaking strategies implemented in dedicated classes. / Estrategias de emparejamiento implementadas en clases separadas.

## Technologies / Tecnologías

Java, object-oriented programming (OOP), command-line interface (CLI), Strategy pattern and command classes.

## Structure / Estructura

```text
src/
├── SportsManager.java             # Main entry point / Punto de entrada
├── controllers/                  # Application controller
├── matchmaking/                  # Matchmaking strategies
├── teams/                        # Teams
├── tournaments/                  # Tournaments and matches
├── users/                        # Users, players and statistics
└── views/                        # CLI, commands and error handling
```

## How to run / Cómo ejecutarlo

**Recommended / Recomendado:** Open the project in IntelliJ IDEA (or another Java IDE), configure a compatible JDK and run `SportsManager.main()` from `src/SportsManager.java`.

**CLI alternative / Alternativa por terminal** (from the repository root, with a JDK installed):

```bash
javac -d build $(find src -name "*.java")
java -cp build SportsManager
```

The CLI command above uses a Unix-like shell (for Windows, the IDE method is simpler). The repository does not include a Maven or Gradle build file, so no Maven command is assumed. / El comando de terminal usa una shell tipo Unix; en Windows resulta más sencillo abrirlo con un IDE. El repositorio no contiene un fichero de configuración de Maven o Gradle.

## Academic context / Contexto académico

Collaborative object-oriented programming coursework at Universidad Politécnica de Madrid (UPM). / Proyecto colaborativo de programación orientada a objetos de la Universidad Politécnica de Madrid (UPM).

> Note / Nota: Features described above are derived from the source code; execution has not been independently verified on all platforms. / Las funcionalidades se han identificado a partir del código; no se ha verificado su ejecución en todas las plataformas.
