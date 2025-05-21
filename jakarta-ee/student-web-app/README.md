# Open Liberty Ant Project

This project is configured for Open Liberty with the following features enabled:
- localConnector-1.0
- servlet-4.0
- jsp-2.3
- transportSecurity-1.0
- jaxws-2.2
- jdbc-4.3
- jndi-1.0
- javaMail-1.6
- appSecurity-3.0
- bells-1.0

Architecture:

!["Architecture"](assets/arch.png)

## Build War

To build the project, run:

```
ant clean
ant war
```

## Prepare Database

- Create database with your way
- Update your database configuration on `servers/defaultServer/server.xml`
    - JDBC URL
    - User name
    - Password
- Run database/create_table.sql to create tables
- Update <library id="mysql-lib"> from server.xml, move mysql connector to this directory

## Deploy

1. Create open liberty server: `.\server.bat create {yourservername}`
2. Copy servers/defaultServer/server.xml to your open liberty server's directory. For example, that's `D:\open-liberty\openliberty-25.0.0.4\wlp\usr\servers\jimmyserver` in my local.
3. Build war package
4. Copy war to your open liberty server's apps directory. For example, that's `D:\open-liberty\openliberty-25.0.0.4\wlp\usr\servers\jimmyserver\apps` in my local.
5. Run `.\server.bat start {yourservername}` to startup your server

## Student Profiles

1. View localhost:9080/studentProfileList to watch student profiles list

!["Architecture"](assets/student_profiles_list.png)

2. Add new student

```bash
curl -XPOST http://localhost:9080/addStudent?name=test&major=test&email=test@microsoft.com
```