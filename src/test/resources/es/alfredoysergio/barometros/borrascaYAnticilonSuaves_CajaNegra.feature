#language: es
Requisito: las borrascas suaves son de diferencias de entre -6.5 y -5.5
y los  anticiclones suaves son de diferencias de entre 6.5 y 5.5

Escenario: Medicion dia anterior -6.6
Cuando la medicion de hace un dia es 6,6 mmHg menor que la mas reciente
Entonces NO se acerca una borrasca suave

Escenario: Medicion dia anterior -6.5
Cuando la medicion de hace un dia es 6.5 mmHg menor que la mas reciente
Entonces se acerca una borrasca suave

Escenario: Medicion dia anterior  -5.5
Cuando la medicion de hace un dia es 5.5 mmHg menor que la mas reciente
Entonces se acerca una borrasca suave

Escenario: Medicion dia anterior -5.4
Cuando la medicion de hace un dia es 5.4 mmHg menor que la mas reciente
Entonces NO se acerca una borrasca suave

Escenario: Medicion dia anterior -1.0
Cuando la medicion de hace un dia es 1.0 mmHg menor que la mas reciente
Entonces NO se acerca una borrasca suave

Escenario: Medicion dia anterior -0.9
Cuando la medicion de hace un dia es 0.9 mmHg menor que la mas reciente
Entonces NO se acerca una borrasca suave ni un anticiclon suave

Escenario: Medicion dia anterior 0.9
Cuando la medicion de hace un dia es 0.9 mmHg mayor que la mas reciente
Entonces NO se acerca una borrasca suave ni un anticiclon suave

Escenario: Medicion dia anterior +1.0
Cuando la medicion de hace un dia es 1.0 mmHg mayor que la mas reciente
Entonces NO se acerca un anticiclon suave

Escenario: Medicion dia anterior +5.4
Cuando la medicion de hace un dia es 5.4 mmHg mayor que la mas reciente
Entonces NO se acerca un anticiclon suave

Escenario: Medicion dia anterior +5.5
Cuando la medicion de hace un dia es 5.5 mmHg mayor que la mas reciente
Entonces se acerca un anticiclon suave

Escenario: Medicion dia anterior +6.5
Cuando la medicion de hace un dia es 6.5 mmHg mayor que la mas reciente
Entonces se acerca un anticiclon suave

Escenario: Medicion dia anterior +6.6
Cuando la medicion de hace un dia es 6.6 mmHg mayor que la mas reciente
Entonces NO se acerca un anticiclon suave