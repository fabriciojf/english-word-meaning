# English Word Meaning

This project is a Spring Boot Model with web view, Repository, PostgreSQL connection, security system with Spring Security and Bootstrap integration. This system allow the words register and your meanings, for documentation and future researchs during english learning journey. Is a small project, but presents how simple is create a project with Spring Boot Framework.

* Spring boot
* Spring Security
* Thymeleaf
* PostgreSQL
* Bootstrap

### Usage

With eclipse 

* Clone the project 
* Eclipse: File -> Import -> Maven -> Existing Maven Projects 
* Select the project folder
* Run
* Access at address http://localhost:3031

#### Ports

For change a new port see the file application.properties

* [application.properties](https://github.com/fabriciojf/english-word-meaning/blob/main/src/main/resources/application.properties)

#### Users allowed

* [SecurityConfig.java](https://github.com/fabriciojf/english-word-meaning/blob/main/src/main/java/com/fabriciojf/english/configuration/SecurityConfig.java)


#### Database Configuration

* [DataConfiguration.java](https://github.com/fabriciojf/english-word-meaning/blob/main/src/main/java/com/fabriciojf/english/config/DataConfiguration.java)


### Running as a Linux Service

Create a new file in /etc/systemd/system directory

```console
$ nano /etc/systemd/system/english.service
```

Insert a service content editing the file path and the description

```console
[Unit]
Description=English Words
After=syslog.target

[Service]
User=root
ExecStart=/opt/apps/english.jar SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
```

Save the file and run a service with the command

```console
$ sudo service english start
```

Other commands

```console
$ sudo service english
Usage: /etc/init.d/english {start|stop|force-stop|restart|force-reload|status|run}
```

### About the Author

Fabricio S Costa - fabriciojf@gmail.com

[![Linkedin: fabriciojf](https://img.shields.io/badge/-Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/fabricioscosta/)](https://www.linkedin.com/in/fabricioscosta/)
[![Site: fabriciojf](https://img.shields.io/badge/-PersonalSite-blue?style=flat-square&logo=wordpress&logoColor=white&link=https://fabriciojf.com)](https://fabriciojf.com)
