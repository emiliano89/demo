# demo

url donde se levanta el proyecto http://localhost:8080

ENDPOINTS: 

FindAll: 
Metodo get : /api/persona

FindOne: 
Metodo get : /api/persona/{ID}

Save: 
Metodo post : /api/persona

Json {
   "persona":{
      "id": {LONG VALUE}(no puede ser nulo),
      
      "nombreCompleto": {STRING VALUE}(no puede ser nulo),
      
      "identificacion":{LONG VALUE}(no puede ser nulo),
      
      "edad":{INT VALUE},
      
      "genero": {STRING(1) VALUE},
      
      "estado":{BOOLEAN},
      
      "maneja":{BOOLEAN},
      
      "usaLentes":{BOOLEAN},
      
      "diabetico":{BOOLEAN}
      
   },
   "otraEnfermedadList":[
      {
      
         "id":{LONG VALUE},
         
         "personaId":{LONG VALUE},
         
         "otraEnfermedad":{BOOLEAN},
         
         "descripcion":{STRING VALUE}
         
      }
      
   ]
   
}


Update: 
Metodo put : /api/persona

Json {

   "persona":{
   
      "id": {LONG VALUE}(no puede ser nulo),
      
      "nombreCompleto": {STRING VALUE}(no puede ser nulo),
      
      "identificacion":{LONG VALUE}(no puede ser nulo),
      
      "edad":{INT VALUE},
      
      "genero": {STRING(1) VALUE},
      
      "estado":{BOOLEAN},
      
      "maneja":{BOOLEAN},
      
      "usaLentes":{BOOLEAN},
      
      "diabetico":{BOOLEAN}
      
   },
   
   "otraEnfermedadList":[
   
      {
      
         "id":{LONG VALUE},
         
         "personaId":{LONG VALUE},
         
         "otraEnfermedad":{BOOLEAN},
         
         "descripcion":{STRING VALUE}
         
      }
      
   ]
   
}


Delete: 
Metodo delete : /api/persona/{ID}
