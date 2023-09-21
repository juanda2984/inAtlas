# InAtlas

Descripción breve de mi proyecto genial.

## Tabla de Contenido

- [Estado del Proyecto](#estado-del-proyecto)
- [Requisitos Previos](#requisitos-previos)
- [Uso](#uso)
- [Ejemplos](#ejemplos)
- [Contribución](#contribución)
- [Créditos](#créditos)
- [Contacto](#contacto)

## Estado del Proyecto

En desarrollo

## Requisitos Previos

- java 8.x

## Uso

En eclipse o STS, se debe ejecutar mediante la opcion Run As -> Java Application y seleccionar el fichero InAtlasApplication.java como punto de entrada.
Tambien se puede instalar como una libreria mvn y observar la ejecucion de las pruebas de integración y unitarias mediante el mismo IDE, Run As -> Maven Install

## Ejemplos

*En un cliente como postman se puede realizar la ejecucion mediante la siguiente peticion en operación get: 
http://localhost:8080/product/
Con esta se conseguiria el menú como tal del Coffee Shop

*En un cliente como postman se puede realizar la ejecucion mediante la siguiente peticion en operación post: 
http://localhost:8080/saleOrder/
Teniendo en cuenta que se debe enviar un body en formato json, como por ejemplo:
[
    {
        "product": {           
            "productName": "Latte"
        }
    }
]
Esta operación realiza una orden, verifica y aplica loas descuentos y promociones que den lugar y genera el recibo de pago de manera detallada.

## Contribución

¡Gracias por la oportunidad!.

## Créditos

- Autor Principal: Juan David Sánchez Loaiza
- 
## Contacto

Puedes contactarme en juanda2984@gmail.com
