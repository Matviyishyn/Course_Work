1. Create a MongoDB database. I advise you to use the MongoDB cluster(You can create it for free on [cloud.mongodb.com](https://cloud.mongodb.com/). Just follow the tips).
2. Replace placeholders in `src/main/resources` with your database URL and credentials.
3. Run: `./gradlew build` if you are on Linux or `./gradlew.bat build` if you are on Windows to build this project.
4. Run `java -jar build/libs/sweetgift-0.0.1-SNAPSHOT.jar` to start the server
5. Open [localhost:8080](http://localhost:8080)