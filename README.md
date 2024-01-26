
![Add a heading](https://github.com/sxflynn/sideprojectstarter/assets/2034081/da0d5583-cbe4-46f6-a274-79ae250b63d3)

# Full Stack Side Project Starter

Kickstart your full stack side-project with a Java Spring web server, Postgres database and Vue.js multi-container setup.

## Instructions

### 1. Download and Install Docker

- Download and install Docker for your operating system from the [Docker Downloads](https://www.docker.com/get-started/) page.
- You can test that docker was successfully installed by opening your Terminal and typing `docker -v` and it should tell you the version.

### 2. Clone the Repository Template

- Click on `Use this template` and click `Create a new repository`
- Choose any name for your project repo, or `sideproject` by default
- `git clone` your new side project repo
- Optionally, if you prefer to download this repo as a zip file, [click here](https://github.com/sxflynn/sideprojectstarter/archive/refs/heads/main.zip).

### 3. Start Containers

Change your working directory to the new project folder name:
  
  ```
  cd [ProjectFolderName]
  ```
You can verify you are in the correct location by typing `ls` and it should be this:
   ```
  README.md  docker-compose.yml  vue  database  java
  ```
Start the Docker containers using `docker compose`:
  ```
  docker compose up --build
  ```

### 4. Access the Vue Project

Once the containers are up and running, your Vue.js project should be accessible at [http://localhost:5173](http://localhost:5173) in your web browser. You can also view the website on another device on your network, such as a phone, by going to the local IP of your computer. For example `http://192.168.100.4:5178`. 

Now, you have successfully set up your full stack side project with Spring Boot, PostgreSQL, and Vue.js using Docker containers. You can start developing your application by editing the code in the project directory.

### 5. Push your changes
Stage all your changes
```
git add -A
```
Commit all your changes
```
git commit -m "Your commit message"
```
Push your changes to GitHub
```
git push origin main
```

## Docker Documentation

Start the containers in detached mode, where it won't take over your terminal. Remove the `-d` to view the output and run the containers in the foreground.
```
docker compose up -d
```

Rebuild the images due to config changes and start the containers.
```
docker compose up --build
```
Stop and remove the containers
```
docker compose down
```
**If you didn't use the `-d` flag, press `Ctrl+C` in your terminal to stop the containers.**

Restart the containers
```
docker compose restart
```

### Advanced Docker Commands

View logs
```
docker compose logs
```
List docker services running
```
docker compose ps
```
Start a specific service
```
docker compose up -d <service_name>
```
Restart a specific service
```
docker compose restart -d <service_name>
```
Force containers to be recreated even if their configuration and image haven't changed.
```
docker compose up -d --force-recreate
```
Delete all docker images, great for debugging and troubleshooting npm dependencies. **Warning, this will delete any custom Postgres data, such as user data.**
```
docker volume prune -a
```

## Postgres data management
The data in your Postgres database will persist between docker container restarts and rebuilds. The `.sql` files in the `/database` directory will be run only once if no Postgres storage volume is detected.

### Updating SQL tables and schemas
If you want to change the table structure and publish those in your repo, and have those persist through container rebuilds, you will need to delete the Postgres volume. **Warning, this will delete any custom Postgres data, such as user data.**
To list the volumes:
```
docker volume ls
```
To remove the Postgres volume, delete the one with the name `postgres-data` in it. For example:
```
docker volume rm sideprojectstarter_postgres-data
```
You could also just run the `prune` command to delete *all* named and anonymous docker images.
```
docker volume prune -a
```
The next time you run `docker compose up` it will rebuild your database structure according to the contents of your `.sql` files in the `/database` directory.

### Customize the Postgres database name and login credentials
The username/password combo for Postgres is initialized in two locations: 
```
./docker-compose.yml
./java/src/main/resources/application.properties
```
You can change the database name, user and password in these two files:
```
# application.properties
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5433}/sideprojectdb
spring.datasource.username=postgres
spring.datasource.password=sideprojectpassword
spring.datasource.driver-class-name=org.postgresql.Driver
```
```
# docker-compose.yml
   environment:
      POSTGRES_DB: sideprojectdb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: sideprojectpassword
```
If you make any changes to the above, you must refer to the instructions above on removing the Postgres volume and starting over. **All user data will be deleted.**

### Connect to Postgres with PgAdmin
To connect to the database with PgAdmin, right-click on Server, Register - Server.
|                    |                     |
|--------------------|---------------------|
| "General" tab          |
| Name | Docker Database           |
| "Connection" tab          |
| Host name/address: | `localhost`           |
| Port:              | `5433`                |
| Username:          | `postgres`            |
| Password:          | `sideprojectpassword` |

The database is called `sideprojectdb` by default, unless you change the name in the instructions above. Make sure to use that instead of `postgres`.

## Adding Vue.js dependencies
If you add dependencies to the Vue.js portion of the project, you may have to delete the Docker image and rebuild it.

To list the volumes:
```
docker volume ls
```
To remove the node volume, delete the one with the name `web-node_modules` in it. For example:
```
docker volume rm sideprojectstarter_vue-node_modules
```
You could also just run the `prune` command to delete *all* named and anonymous docker images.
```
docker volume prune -a
```
The next time you run `docker compose up` it will run `npm install` and ensure that the new dependencies are included in your `node_modules` directory.

## Tips for Active Development
If you are actively developing a project, you will likely want to only use Docker for Postgres and Vue, and actively develop the Java application in a dedicated IDE.

To start your `db` and `vue-web` services only without Java, use this command:
```
docker compose up db vue-web
```
This will start the database and Vue containers, leaving you to develop and run the Java service in a dedicated IDE like IntelliJ.

### Using IntelliJ to develop your Java server
The IntelliJ IDEA Community Edition is [free to download](https://www.jetbrains.com/idea/download/) and recommended for actively developing this side project.

1. Open IntelliJ and go to File -> Open
1. Select `pom.xml` in the to open the project
1. Navigate to `/src/main/java/com.sideproject.starter/` and double click `SideProjectApplication` to open it.
1. If it says "Project JDK is not defined" then click on "Setup SDK", click Download JDK... and select version 11.
1. Click on the run button in the top right corner to run the Java server.
