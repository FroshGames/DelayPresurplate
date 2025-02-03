El comando /delayedplate en el plugin DelayedPressurePlate permite gestionar el retraso de activación de las placas de presión en Minecraft 1.20.1. Está definido en plugin.yml​pluginy permite modificar parámetros como el tiempo de retardo y las placas afectadas​README.

Subcomandos y su funcionamiento
La implementación del comando se encuentra en PlateCommand.java​PlateCommand, y cuenta con los siguientes subcomandos:

1. /delayedplate setdelay <segundos>
Modifica el tiempo de retraso antes de que la placa active la señal de redstone.
Guarda el nuevo valor en config.yml bajo la clave delay​config.
Envía un mensaje al jugador confirmando el cambio.
Ejemplo:

sh
Copiar
Editar
/delayedplate setdelay 5
Establece el retraso en 5 segundos.

2. /delayedplate add [radio]
Añade placas de presión cercanas dentro del radio especificado (por defecto 10 bloques).
Obtiene los bloques en un área cúbica alrededor del jugador.
Si encuentra placas de presión (*_PRESSURE_PLATE), las añade a config.yml​config.
Envía un mensaje indicando cuántas placas fueron añadidas.
Ejemplo:

sh
Copiar
Editar
/delayedplate add 15
Añade todas las placas de presión en un radio de 15 bloques alrededor del jugador.

3. /delayedplate remove
Elimina la placa de presión que el jugador está mirando.
Verifica si la placa está en la lista del config.yml.
Si existe, la elimina y guarda los cambios​PlateCommand.
Ejemplo:

sh
Copiar
Editar
/delayedplate remove
Elimina la placa de presión que el jugador está mirando.

4. /delayedplate reload
Recarga la configuración desde config.yml.
No es necesario reiniciar el servidor.
Envía un mensaje confirmando la recarga​PlateCommand.
Ejemplo:

sh
Copiar
Editar
/delayedplate reload
Recarga los cambios en el config.yml.

Integración con el Plugin
Configuración:

Se almacena en config.yml​config.
delay: Tiempo en segundos antes de que la placa de presión active la señal.
plates: Lista de placas afectadas.
Ejemplo de config.yml:

yaml
Copiar
Editar
delay: 10
plates:
  - STONE_PRESSURE_PLATE
  - OAK_PRESSURE_PLATE
Eventos de Redstone (PressurePlateListener.java):

Intercepta el evento BlockRedstoneEvent​PressurePlateListener.
Si el bloque activado está en la lista de plates, cancela la señal temporalmente.
La señal se activa después de delay segundos.
Resumen de Mejoras
✅ Nuevo parámetro en /delayedplate add <radio>: permite añadir múltiples placas a la vez.
✅ Mayor flexibilidad en la configuración (config.yml).
✅ Recarga dinámica con /delayedplate reload sin reiniciar el servidor.

Este comando permite una gestión eficiente y configurable del retraso de placas de presión en Minecraft. 🚀
