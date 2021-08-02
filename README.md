# DS-RPC-RMI
Actividad de Distribuidos


## Preparacion del Entorno de Desarrollo
 - Se debe tener instalado java en su version 8
 - Se debe tener instalado Docker y Docker-compose en la computadora  
Ejecutar para clonar el repositorio
```console
$ git clone https://github.com/OscarM04/DS-RPC-RMI.git
```

### Uso
Ejecutar comando para dirigirse a la carpeta del proyecto
```console
$ cd DS-RPC-RMI
```
### Compilado:
Ejecutar el siguiente comando para compilar el proyecto
```console
$ docker-compose up compile-project
```
### Ejecución:
Ejecutar los siguientes comandos para correr el servidor BANK
- En una ventana del terminal ejecutar el siguiente comando para iniciar la base de datos 
```console
$ docker compose up db_Storage 
```
- En otra ventana del terminal ejecutar el siguiente comando para iniciar el servidor, verificar que el puerto 6666 este libre 
```console
$ java -jar ./RPC-RMI/BANK/target/BANK-1.0.0-jar-with-dependencies.jar 
```
Ejecutar los siguientes comandos para correr el cliente ATM 
- En otra ventana del terminal ejecutar el siguiente comando para iniciar el servidor, verificar que el puerto 6666 este libre 
```console
$ java -jar ./RPC-RMI/ATM/target/ATM-1.0.0-jar-with-dependencies.jar 
```