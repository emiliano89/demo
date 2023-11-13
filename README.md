# demo

url donde se levanta el proyecto http://localhost:8080

ENDPOINTS: 

FindAll: 
Metodo get : /public

FindAll con seguridad: 
Metodo get : /secured 
( Datos de logueo enviando basic auth  Usr: user / pass: password ) 

FindOne: 
Metodo get : /public/{ID}
id: corresponde al id de la persona que se busca

Save: 
Metodo post : public/save

Ejemplo Json 
{
    "person": {
        "id": 3 {LONG VALUE}(no puede ser nulo),
        "fullName": "JUAN PE" {STRING VALUE}(no puede ser nulo),
        "identification": 12345678 {LONG VALUE}(no puede ser nulo),
        "age": 12 {INT VALUE},
        "gender": "M"  {STRING(1) VALUE},
        "state": true {BOOLEAN},
        "drive": true {BOOLEAN},
        "useGlasses": false {BOOLEAN},
        "diabetic": false {BOOLEAN}
    },
    "otherDiseaseList": [
        {
            "otherDiseaseId": 1 {LONG VALUE},
            "personId": 3 {LONG VALUE}(Es el id de la persona),
            "otherDisease": false {BOOLEAN},
            "description": null {STRING}
        },
        {
            "otherDiseaseId": 2 {LONG VALUE},
            "personId": 3 {LONG VALUE}(Es el id de la persona),
            "otherDisease": false {BOOLEAN},
            "description": null {STRING}
        }
    ]
}

Update: 
Metodo put : /public/update

Ejemplo Json 
{
    "person": {
        "id": 3 {LONG VALUE}(no puede ser nulo),
        "fullName": "JUAN PE" {STRING VALUE}(no puede ser nulo),
        "identification": 12345678 {LONG VALUE}(no puede ser nulo),
        "age": 12 {INT VALUE},
        "gender": "M"  {STRING(1) VALUE},
        "state": true {BOOLEAN},
        "drive": true {BOOLEAN},
        "useGlasses": false {BOOLEAN},
        "diabetic": false {BOOLEAN}
    },
    "otherDiseaseList": [
        {
            "otherDiseaseId": 1 {LONG VALUE},
            "personId": 3 {LONG VALUE}(Es el id de la persona),
            "otherDisease": false {BOOLEAN},
            "description": null {STRING}
        },
        {
            "otherDiseaseId": 2 {LONG VALUE},
            "personId": 3 {LONG VALUE}(Es el id de la persona),
            "otherDisease": false {BOOLEAN},
            "description": null {STRING}
        }
    ]
}


Delete: 
Metodo delete : /public/{ID}

ID : es el id de la persona que se desea eliminar de bd

Obener temperatura: 
Metodo post :  public/city/{city}

city: es la ciudad de la que se desea consultar la temperatura actual. Solo estan disponibles estas ciudades : CORDOBA, BUENOS AIRES y ROSARIO;

este endpoint consulta la temperatura actual a la url https://api.open-meteo.com/ con parámetros guardados en base de datos h2.

------------------------------------------------------------------

Los tests unitarios se encuentran en src\test


