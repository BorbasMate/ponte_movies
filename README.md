# Movie Rating App

## General introduction
The application presents movie items. The user can click on the title
of the movie to reach more information about the movie, and also to 
send ratings about the selected movie.

The app is using Java backend with Spring-boot and Angular frontend.
The database is implemented of mySql type.

The database has 1 entity ("ratings"), we use this to store the ratings of
the users. The user is asked to provide his email address when sending a rating
(both numeric 1-5 and optionally text).
This email address is available in the ratings entity, though we only show the 
first part of the address on frontend when listing the ratings for a particular
movie.

The application connects the TMDB api check: https://developer.themoviedb.org/docs/getting-started
This external database is used to fetch various movie items. We selected to fetch the
top-rated movies. The api_key is in application.yaml.

## Setup
### Install docker and database

Check the application.yaml file for database port (3306) and schema name (ponte_movies).

Windows:
- Install these updates: https://aka.ms/wsl2kernel (1.-5. points)
- Then install docker: https://docs.docker.com/docker-for-windows/install/
- Then: https://docs.docker.com/docker-for-windows/wsl/ (3. point) 

Linux:
- https://docs.docker.com/engine/install/ubuntu/

MAC OS:
- https://docs.docker.com/desktop/mac/apple-silicon/

You need to create a container that runs mysql image.

docker run -d --name mysql-server -e MYSQL_ROOT_PASSWORD=test1234 -p 3306:3306 -v mysqldata:/var/lib/mysql mysql:8.0

After installation of the docker container, you need to create the schema "ponte_movies". 

### Start backend

Navigate to the backend directory in your project (=> src directory)

Ensure Maven is installed on your system.
Run the following command to build the Java application: mvn clean install

Once the build is finsihed run the following command: mvn spring-boot:run
The above command will start the backend on http://localhost:8080

### Start Frontend

Navigate to the frontend directory in your project (=> angular-frontend directory)

Ensure Node.js and npm (Node Package Manager) is installed on your system.
Use min Node 16.20.0. 
Run the following command: npm i , then run the command: npm start

### Use the application

When both backend and frontend is started, you can access the application by
opening your web-browser and use http://localhost:4200

## Documenation

The application is using openapi (swagger) documentation for the backend.
1. start your backend and database
2. Open your browser http://localhost:8080/swagger-ui/index.html

Here you can explore the endpoints and dto-s of the application and try them.

## Others

The application's backend is fully tested with unit tests and integration tests.
The frontend is manually tested.

The frontend general graphic schema is fully defined in the css files for further
development.

