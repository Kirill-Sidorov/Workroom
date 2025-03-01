# Workroom

Example of a web application with a database (university work).

**Industrial use of the project is possible after the testing and unit test coverage!**

Available languages:
* english.

### Required software:
* Openjdk version 11.0.0.1;
* Apache Maven 3.9.6;
* Docker version 27.5.1;
* Docker Compose version v2.32.4;
* *For Windows users - Mintty 3.7.6 (Git Bash) (Git for Windows v2.47.0).

### Run project
1. Clone the repository.
2. Go to root project folder and create a **.env** file, file example:
```
db_username=<postgre_user_name>
db_password=<postgre_password>
```

3. Start a command line (Git Bash for Windows) in the project directory and run the command (./start.sh launch web-app and db both as containers):
```console
./start.sh
```

4. Open [http://localhost:8080](http://localhost:8080/) address in your browser. You can find test login data in the file: "dbscripts/schema.sql".

5. Stop web-app and db containers:
```console
./stop.sh
```

**Additional launch option**

1. When developing, it is recommended to run the web server locally (dev maven profile) (with help "jetty-maven-plugin"):
```console
./start-dev.sh
```

2. Stop dev launch:
```console
./stop-dev.sh
```

**Checkstyle launch**
```console
./start-checkstyle.sh
```

### Possible problems and how to fix it!

1. If you have a Postgres database installed and running locally, you need to stop it (otherwise, when you start the web application, it will not be able to connect to the database in the container).

### Brief description of the web application

The **administrator** of the "workroom" web application performs the following actions: registers new users; edits records of existing users; views all users of the system; deletes blocked users.

The **moderator** of the web application "workroom" performs the following actions: checks and, if necessary, edits new customer orders for repair of things; deletes cancelled orders; blocks and unblocks users of the system; views the list of orders awaiting verification; views the list of orders for deletion; views all users of the web application "workroom".

Users of the "workroom" web application: **customers**, **master** and **storekeepers**.

The **customer** performs the following actions: creates a new order for repairs; views the list of their orders; edits repair orders; cancels their orders; views the total cost of repairs.

The **master** performs the following actions: views the list of orders available for execution; selects a suitable order for execution; compiles and edits the list of necessary spare parts for repair; sets the cost of repair; views the lists of completed and unfinished orders.

The **storekeeper** performs the following actions: views the list of spare parts in the warehouse; edits the spare part record; changes the quantity of spare parts in the warehouse; removes a spare part from the warehouse; adds a new spare part to the warehouse; views the list of orders awaiting receipt of spare parts; confirms the availability of spare parts required to repair the order.

### Images