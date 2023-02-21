#language: es
Requisito: las borrascas suaves son de diferencias de entre -6.5 y -5.5
y los  anticiclones suaves son de diferencias de entre 6.5 y 5.5

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra 
Cuando la medicion de hace un dia es 6,6 mmHg menor que la mas reciente
Entonces NO se acerca una borrasca suave

Escenario: borrasca suave 6.5
Cuando la medicion de hace un dia es 6.5 mmHg menor que la mas reciente
Entonces se acerca una borrasca suave

Escenario: borrasca suave 5.5
Cuando la medicion de hace un dia es 5.5 mmHg menor que la mas reciente
Entonces se acerca una borrasca suave

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra
Cuando la medicion de hace un dia es 5.4 mmHg menor que la mas reciente
Entonces NO se acerca una borrasca suave

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra
Cuando la medicion de hace un dia es 1.0 mmHg menor que la mas reciente
Entonces NO se acerca una borrasca suave

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra
Cuando la medicion de hace un dia es 0.9 mmHg menor que la mas reciente
Entonces NO se acerca una borrasca suave ni un anticiclon suave

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra
Cuando la medicion de hace un dia es 0.9 mmHg mayor que la mas reciente
Entonces NO se acerca una borrasca suave ni un anticiclon suave

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra
Cuando la medicion de hace un dia es 1.0 mmHg mayor que la mas reciente
Entonces NO se acerca un anticiclon suave

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra
Cuando la medicion de hace un dia es 5.4 mmHg mayor que la mas reciente
Entonces NO se acerca un anticiclon suave

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra
Cuando la medicion de hace un dia es 5.5 mmHg mayor que la mas reciente
Entonces se acerca un anticiclon suave

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra
Cuando la medicion de hace un dia es 6.5 mmHg mayor que la mas reciente
Entonces se acerca un anticiclon suave

Escenario: el historial solo tiene dos mediciones y una se hizo un 
dia anterior a la otra
Cuando la medicion de hace un dia es 6.6 mmHg mayor que la mas reciente
Entonces NO se acerca un anticiclon suave