#language: es
Requisito: Recorrer todos los caminos posibles

Escenario: borrascaIntensa es True
Cuando borrascaIntensa devuelve True
Entonces obtenerMedicion devuelve Tiempo.BORRASCA_INTENSA

Escenario: borrascaLejos es True anterior False
Cuando borrascaLejos devuelve True
Entonces obtenerMedicion devuelve Tiempo.BORRASCA_SUAVE

Escenario: anticiclonIntenso es True anteriores False
Cuando anticiclonIntenso devuelve True
Entonces obtenerMedicion devuelve Tiempo.ANTICICLON_INTENSO

Escenario: anticiclonEntreBorrascas es True anteriores False
Cuando anticiclonEntreBorrascas devuelve True
Entonces obtenerMedicion devuelve Tiempo.ANTICICLON_SUAVE

Escenario: Todas devuelven False
Cuando Todas las comprobaciones son falsas
Entonces obtenerMedicion devuelve Tiempo.INSUFICIENTE