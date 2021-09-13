# Strange File Server
Strange File Server is a simplistic file server webapp. It serves files under a **root** directory which is a **required** parameter for the service to start up successfuly. Additionally a **secret** parameter is required to be passed a startup. This is used in order to calculate a digest with the file content for security purposes. 

It is a Java 1.8/Spring Boot project set up with Maven.

# Assignment Requirements

It is required to run this app in a kubernetes cluster, exposed as a service on port 5000.
Thus a docker image should be produced together with the relative kubernetes configuration.
The service should be configured for resilience and scalability.

### Notes

Please provide the relevant Docker and Kubernetes files, together with all the commands or instructions required to succesfully build, run and setup the app in kubernetes.

* You can use minikube or any other local Kubernetes setup.

* Please do not push or share any of the files or images created in any public code or image repositories.


### App Configuration
The app requires the following two parameters at runtime to startup succesfully. 

* **root**: This must be the path to an existing directory
* **secret**: A simple string value. Note that as this is supposed to be a secret value, it should be configured accordingly.

_Note_: For the purpose of this assigment, the root directory could be simply be a directory in the docker image.

## Guide

* To run the app:

>mvnw spring-boot:run -Dspring-boot.run.arguments="--secret=secret --root=content"

_Note:_ You can optionally simply set the required arguments as environmental variables instead.

* Folder _content_ is just a sample directory with a text file used to demonstrate the app.

* HELP.md contains links with information on Maven or how to run Spring Boot applications.

## Strange File Server API

>GET /files

Response: 
```json
["file1", "file2"]
```
>GET /files/{filename}

Response: 

```json
{
  "content": "Super important",
  "digest": "kcMF1aY25sTOWDeOK65E13YI8058xoQiGcXgUyh3grU="
}
```

>GET /actuator/health

```
{
  "status": "UP"
}
```
This endpoint is to be used as the health or status endpoint for the running application.