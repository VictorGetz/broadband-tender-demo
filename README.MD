# Broadband Tender Demo

[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Broadband Tender Demo is the Backend Project for a Web Application which 
is a cloud-enabled, mobile-ready, offline-storage, AngularJS powered HTML5 Markdown editor.

  - Type some Markdown on the left
  - See HTML in the right
  - Magic

# Goal
Develop a Spring Boot Demo Application within a day.

# New Features!

  - Import a HTML file and watch it magically convert to Markdown
  - Drag and drop images (requires your Dropbox account be linked)


You can also:
  - Import and save files from GitHub, Dropbox, Google Drive and One Drive
  - Drag and drop markdown and HTML files into Dillinger
  - Export documents as Markdown, HTML and PDF

### Tech Stack

Broadband Tender Demo uses a number of technologies to work properly:

* Java
* [Flyway](https://flywaydb.org/) - For Database Migration
* [Lombok](https://projectlombok.org/) - Reduces Boilerplate Code 
* [Spring Boot](http://spring.io/projects/spring-boot) - Reduces Boilerplate Code and helps setting up a App Quickly
* Rest
* Azure Cloud - Database and App Hosting with Docker
* Docker - Lightweight Container Solution for the Deployment
* [Gradle](https://gradle.org/) - Build Tool for this Project
* mssql - Azure Cloud Database
* H2 - Embedded Database for local Development
* [JPA/Hibernate](http://spring.io/projects/spring-data-jpa) - Database Access Layer
* Azure Cli - Infrastructure as Code for setting up the Infrastructure automated and to have a history of changes

### IDE Installation

Broadband Tender Demo requires for developing the following Tools:
* Java 1.8 (Because Azure Web App currently just accepts Java 1.8 and nothing higher) 
* Gradle
* Azure Cloud if you dont want to use H2

I would also recommend to use the following Technologies/Tooles:
* Linux or MacOS System
* Intellij
* Docker
* [sdkmann](https://sdkman.io/) for Java and Gradle Installation 

These are the Steps for the Setup

```sh
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk install java 8.0.181-oracle
# Test Java Version
$ java -version
$ sdk install gradle
# Test Gradle Version
$ gradle -version
```
Download and install Intellij and Docker 

### Development

Want to contribute? Great!

Dillinger uses Gulp + Webpack for fast developing.
Make a change in your file and instantanously see your updates!

Open your favorite Terminal and run these commands.

First Tab:
```sh
$ node app
```

Second Tab:
```sh
$ gulp watch
```

(optional) Third:
```sh
$ karma test
```
#### Building for source
For production release:
```sh
$ gulp build --prod
```
Generating pre-built zip archives for distribution:
```sh
$ gulp build dist --prod
```
### Docker
Broadband Tender Demo is very easy to install and deploy in a Docker container.

First login into the private docker repository
```sh
# Replace privateRepository with your Repository
$ docker login privateRepository
```
Now build the Container with Intellij or console

By default, the Docker will expose port 8080, so change this within the Dockerfile if necessary. When ready, simply use the Dockerfile to build the image.

```sh
cd dillinger
docker build -t joemccann/dillinger:${package.json.version} .
```
This will create the dillinger image and pull in the necessary dependencies. Be sure to swap out `${package.json.version}` with the actual version of Dillinger.

Once done, run the Docker image and map the port to whatever you wish on your host. In this example, we simply map port 8000 of the host to port 8080 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 8000:8080 --restart="always" <youruser>/dillinger:${package.json.version}
```

Verify the deployment by navigating to your server address in your preferred browser.

```sh
127.0.0.1:8000
```

#### Kubernetes + Google Cloud

See [KUBERNETES.md](https://github.com/joemccann/dillinger/blob/master/KUBERNETES.md)


### Todos

 - Write MORE Tests
 - Add Night Mode

License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
