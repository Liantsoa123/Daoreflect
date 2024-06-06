# DAOREFLECT

## Introduction

CRUD Generic for Java programing langage

## Getting Started

**Directory Structure:**

* **src/**: Contains the source code for the project.
* **build.bat**: Script for building the project into JAR file.

**Prerequisites**

* Java Development Kit (JDK)
* JDBC driver

### Building the Project

The repository contains a batch script file to build the project. Run the following command in the terminal:

```bash

    ./build.bat

```

The script will create a JAR file named **DAOREFLECT.jar**. Add the file to your project's libraries and it will be ready to use for your web application.

**Note:** You need to have a xml context file for your database setup.

```xml
    <dao>
        <driver>database driver class</driver>
        <url>url</url>
        <database>database name</database>
        <user>username</user>
        <password>password</password>
        <bdd>database engine type</bdd>
    </dao>
```
add
## Example

```java
    // Here is the class used on our test
    @Table("user")
    public class User {
        @Column("id")
        String id;
        @Column("name")
        String name ;
    }
```

```java
    DaoReflect daoReflect = new DaoReflect("path\\to\\xml-file.xml");
    ArrayList<Object> users = daoReflect.findAll(new User() , false);
    for (Object object : users) {
        User user = ((User)object);

        System.out.println(User.getName());
    }
```

## Feature

* Save
* FindAll ( Class , False without criteria )
* FindAll ( Class , Ture with criteria )
* FindBetween
* pagin

## Contributing

Contributions are welcome. Please feel free to fork the project and submit your pull requests by :

* Implementing additional features based on the Spring framework.
* Improving the existing code.
* Adding documentation and comments.

## License

This project is licensed under the MIT License. This license grants you permission to freely use, modify, and distribute this software under certain conditions. Please refer to the [LICENSE](./LICENSE) file for more details.

## Contact

You can reach me at: [rakotonanaharyLiantsoaFan@gmail.com](mailto:rakotonanaharyLiantsoaFan@gmai.com)
