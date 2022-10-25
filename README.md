# EjercicioBlackjack

## Desafío Casino
En la clase pasada usted fue capaz de crear un diagrama UML en Visual Paradigm para
representar el casino como un sistema basado en clases utilizando POO. Ahora bien, esto
se hizo utilizando, esperablemente, dependencias; por lo que, hoy, tendrá que reemplazar
aquellas que cree que en lugar de ser dependencias encajan mejor como asociaciones,
composiciones y agregaciones. Así como tendrá que indicar su cardinalidad y sentido
(unilateral o bilateral).
### Visual Paradigm
Se les piden dejen anexada una foto de su Diagrama en su repositorio, así como el código
ejecutable y funcional y las pruebas unitarias del código, así también el archivo Log con los
logs de dichas pruebas unitarias. Deberá generar automáticamente usando la herramienta
de Visual Paradigm.

| Clase      | Relacionada | Tipo de relación | Sentido        | Cardinalidad |
|------------|-------------|------------------|----------------|--------------|
| Baraja     | Carta       | Agregación       | Unidireccional | 0..*         |
| Carta      | Indice      | Composición      | Unidireccional | 1            |
| Carta      | Pinta       | Composición      | Unidireccional | 1            |
| Juego      | Baraja      | Composición      | Unidireccional | 1            |
| Juego      | Jugador     | Agregación       | Unidireccional | 0..*         |
| Jugador    | Estado      | Composición      | Unidireccional | 1            |
| Jugador    | Mano        | Asociación       | Unidireccional | 1            |
| Mano       | Baraja      | Dependencia      | Unidireccional | N/A          |
| Mano       | Carta       | Agregación       | Unidireccional | 0..*         |

