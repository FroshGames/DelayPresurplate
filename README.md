# DelayPresurplate Pluguin

Un plugin para Minecraft 1.20.1 que permite añadir un retraso configurable a las placas de presión.

## Características
- Permite configurar el tiempo de retardo antes de que una placa de presión active la señal de redstone.
- Comandos para agregar o quitar placas de presión de la lista afectada.
- Configuración sencilla mediante `config.yml`.

## Instalación
1. Descarga el archivo `.jar`.
2. Colócalo en la carpeta `plugins` de tu servidor Spigot/Paper.
3. Reinicia el servidor.
4. Edita `config.yml` para personalizar el tiempo de retardo.

## Comandos
- `/delayedplate setdelay <segundos>`: Cambia el tiempo de retardo.
- `/delayedplate add`: Añade la placa de presión que estás mirando a la lista.
- `/delayedplate remove`: Elimina la placa de presión que estás mirando de la lista.

## Configuración (`config.yml`)
```yaml
delay: 10
plates:
  - STONE_PRESSURE_PLATE
  - OAK_PRESSURE_PLATE
