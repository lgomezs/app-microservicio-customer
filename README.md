# README #

Aplicacion que se conecta al config Server para recuperar los archivos de configuracion del aplicativo app-identity-app, a su vez encripta la informacion del key vault de azure para su posterior recuperacion y generar la conexion a BD Mongo cosmo de AZURE.


##Inciar config-server
Para el config-server crear la variable de entorno ${SPRING_PROFILES_ACTIVE}, indicando el tipo de repositorio.

Iniciar el config-server:
  mvn spring-boot run
  
  Ejemplo : http://localhost:8888/
 
#Generar key:

 Ejecutar el comando para generar un key (Para ejecutar el curl en windows; agregar el path bin de git,     ejemplo : C:\Program Files\Git\mingw64\bin )
 
   curl localhost:8888/encrypt -d Passw0rd >> password #te genera el Passw0rd encriptado
   
   Agregar el password encriptado en el encrypt.key del config-server.
   
   #test config server :
   
   curl localhost:8888/app-identity-app.yml
   
    
##Iniciar app-identity-app
   
  Para las variables de entorno: bootstrap.yml
  
  	 ${KEY_ENCRYPT}  -> El generado con el comando curl
  	 ${PROFILE}		 -> El tipo de conexion para recuperar los ficheros de configuracion(el cual puede ser local o algun repositorio como GitHub o Bitbucket).
  	 ${URI_CONFIG_SERVER} -> Ruta de tu Config Server.
  	
  	
  En el profile elegio anteriormente crear el fichero de configuracion : app-identity-app.yml
  
    
  identity:
  keyvault:
    uri: https://keyvaultrippletest.vault.azure.net/
    clientid:  '{cipher}<CLIENT_ID_ENCRIPTADO>'
    clientkey: '{cipher}<CLIENT_KEY_ENCRIPTADO>'
   
  mongodb:
    keyvault:
      secretname: kvsecretcdbripple
    dbname: BDPrueba   
    collectioncustomer: ripplecustomer 
    
     	
   <CLIENT_ID_ENCRIPTADO>,de ejecutar:     	 
   curl localhost:8888/encrypt -d CLIENTID >> CLIENT_ID_AZURE
     	 	 
   CLIENT_KEY_ENCRIPTADO, de ejecutar:     	 	 
   curl localhost:8888/encrypt -d CLIENTKEY >> CLIENT_KEY_AZURE
     	 	  
# Generar un secret en AZURE con la conexion a BD (URI)
    
   --create resource
	az group create --name TestRG1 --location "South Central US"
	az group list


--create keyvault
	az keyvault create --name keyvaultrippletest    \
                   --resource-group TestRG1 \
                   --location 'East US'         \
                   --enabled-for-deployment true          \
                   --enabled-for-disk-encryption true     \
                   --enabled-for-template-deployment true \
                   --sku standard


az keyvault set-policy --spn ddb86841-572d-4dd9-b8cc-f1459c4c7a81 --name 	keyvaultrippletest --secret-permissions set delete get list


  --create secret
    az keyvault secret set --vault-name 'keyvaultrippletest' --name 'kvsecretcdbripple' \
                       --value   	'mongodb://dbmongoripple:XXXTDTBYv0ziOZyWEEAy37G5oeMvBMd1UPaar7jESfN6B6PP1TEWa1J9REzpa7Mjwio21PJVEcS8ifapm5PBpVo		         uNg==@dbmongoripple.documents.azure.com:10255/?ssl=true&replicaSet=globaldb'
   
   	 
 # Iniciar el aplicativo:
   mvn spring-boot run
   
   endpoint:
   	http://localhost:8083/identity/listCustomer
   	
   	 
  
  