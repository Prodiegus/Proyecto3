# Simulador de Memoria Swapping
## Descripción
Este programa simulara el funcionamiento de una memoria swapping, contiene un menu de linea de comandos que permite al usuario realizar cambios estaticos y un menu interno que permite interactuar con la memoria.
## Entradas de lineas de comandos
### -h o -help
Muestra la ayuda del programa.
### -TMI <tamano_memoria_intercambio>
Define el tamaño de la memoria de intercambio.
### -TMP <tamano_memoria_principal>
Define el tamaño de la memoria principal.
### -FIFO o -LRU
Define el algoritmo de reemplazo de paginas.
## Menu interno
### add <nombre_proceso> <quantum>
Agrega un proceso a la cola de procesos.
### add -l <nombre_archivo>
Agrega un lote de procesos que debe ser cargado desde un fichero. el archivo debe estar en el fichero lotes y debe contar con el siguiente formato:
```
<nombre_proceso> <quantum>
<nombre_proceso> <quantum>
<nombre_proceso> <quantum>
<nombre_proceso> <quantum>
```
### run
Ejecuta los procesos en la cola de procesos.
### look -main
Muestra los procesos en memoria principal.
### look -swap
Muestra los procesos en memoria de intercambio.
### look -all
Muestra todos los procesos. 
### look -queue
Muestra la cola de procesos.
### look
Muestra las opciones de look.
### kill <nombre_proceso>
Elimina un proceso.
### change (-FIFO o -LRU)
Cambia el algoritmo de reubicacion.
### exit
Sale del programa.
## como compilar
Es necesario tener instalado java y javac.
Al ser un programa en java su compilacion es sencilla, solo es necesario ejecutar el siguiente comando:
```bash
javac Main.java
```
## como ejecutar
Para ejecutar el programa es necesario ejecutar el siguiente comando:
```bash
java Main
```
Este seria la ejecucion por defecto, si se desea ejecutar 
con parametros debera agregarlos al final de la linea de comandos.
## Ejemplos de ejecucion
### Ejecucion por defecto
```bash
java Main
```
### Ejecucion con parametros
```bash
java Main -TMI 100 -TMP 50 -LRU
```
### Ejecucion para mostrar la ayuda
```bash
java Main -h
```
### Ejecucion para agregar un proceso
```bash
java Main
add proceso1 10
```
### Ejecucion para agregar un lote de procesos
```bash
java Main
add -l lote1.txt
```
### Ejecucion para ejecutar los procesos
```bash
java Main
run
```
### Ejecucion para mostrar los procesos en memoria principal
```bash
java Main
look -main
```
### Ejecucion para mostrar los procesos en memoria de intercambio
```bash
java Main
look -swap
```
### Ejecucion para mostrar todos los procesos
```bash
java Main
look -all
```
## autore
- [Diego Fernandez](mailto:dfernandez19@alumnos.utalca.cl)

