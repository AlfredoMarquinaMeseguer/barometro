#language: es
Requisito: las borrascas intensas son de diferencias de -1 o menores 
y los  anticiclones intensos son de diferencias de +1 o mayores

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 6.6 mmHg menor que la mas reciente
Entonces se acerca una borrasca intensa

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 6.5 mmHg menor que la mas reciente
Entonces se acerca una borrasca intensa

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 5.5 mmHg menor que la mas reciente
Entonces se acerca una borrasca intensa

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 5.4 mmHg menor que la mas reciente
Entonces se acerca una borrasca intensa

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 1.0 mmHg menor que la mas reciente
Entonces se acerca una borrasca intensa

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 0.9 mmHg menor que la mas reciente
Entonces NO se acerca una borrasca intensa ni un anticiclon intenso

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 0.9 mmHg mayor que la mas reciente
Entonces NO se acerca una borrasca intensa ni un anticiclon intenso

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 1.0 mmHg mayor que la mas reciente
Entonces se acerca un anticiclon intenso

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 5.4 mmHg mayor que la mas reciente
Entonces se acerca un anticiclon intenso

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 5.5 mmHg mayor que la mas reciente
Entonces se acerca un anticiclon intenso

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 6.5 mmHg mayor que la mas reciente
Entonces se acerca un anticiclon intenso

Escenario: el historial solo tiene dos mediciones y una se hizo una 
hora anterior a la otra
Cuando la medicion de hace una hora es 6.6 mmHg mayor que la mas reciente
Entonces se acerca un anticiclon intenso