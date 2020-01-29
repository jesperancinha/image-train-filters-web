# Image Train Filters
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=informational)](http://itf.joaofilipesabinoesperancinha.nl/)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/image-train-filters-scala.svg)](#)
  
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b45a0bd9c3694401b78580b95c0b505b?)](https://www.codacy.com/app/jofisaes/image-train-filters-scala?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/image-train-filters-scala&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/93dcee14-b81d-4827-bd4f-9c7a3f56c752)](https://codebeat.co/projects/github-com-jesperancinha-image-train-filters-scala-master)
[![CircleCI](https://circleci.com/gh/jesperancinha/image-train-filters-scala.svg?style=svg)](https://circleci.com/gh/jesperancinha/image-train-filters-scala)
[![Build Status](https://travis-ci.org/jesperancinha/image-train-filters-scala.svg?branch=master)](https://travis-ci.org/jesperancinha/image-train-filters-scala)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/image-train-filters-scala?branch=master)](https://bettercodehub.com/)
[![Build status](https://ci.appveyor.com/api/projects/status/rmiwrpoo9hipj28b/branch/master?svg=true)](https://ci.appveyor.com/project/jesperancinha/image-train-filters-scala/branch/master)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/image-train-filters-scala/badge.svg)](https://snyk.io/test/github/jesperancinha/image-train-filters-scala)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/image-train-filters-scala.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/image-train-filters-scala.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/image-train-filters-scala.svg)](#)

## Description

Webservice which returns a filtered image after applying selectable filters

-   Using Image Contour filter:

From <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" width="200" /> To <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPantherOut.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPantherOut.jpg" width="200" />

-   Using Kuwahara filter:

From <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" width="200" /> To <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther1.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPantherOut.jpg" width="200" />

-   Using Chartizate filter:

From <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" width="200" /> To <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPantherCZ.png" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPantherCZ.png" width="200" />

Current running online implementation: [Image train filters](http://itf.joaofilipesabinoesperancinha.nl)

## How to use
If you are installing this locally, remember that currently the [chartizate](https://github.com/jesperancinha/itf-chartizate) libraries are not located in public repos, so please copy this file [settings.xml](./settings.xml) to your local maven repo in ~/.m2/settings.xml.

You need to provide two form-data parameters in a Multipart Formdata request.
Use curl for that please. An example follows bellow.

### filename
-   this is the where you load your image file in

### commands
-   this is where you set your commands. there are only two examples at the moment:

1.  Kuwahara filter:

> It smooths the image

2.  Image contour filter:

> Processes differences in color ranges
>
> These requests you can apply as many times as you like using a JSON request. Read following example for more.

### Command example

```text
{
  "commands": [
    {
      "filter": "imageKuwahara",
      "settings": [
        {
          "name": "square-size",
          "value": "2"
        },
        {
          "name": "iterations",
          "value": "2"
        }
      ]
    },
    {
      "filter": "imageContour",
      "settings": [
        {
          "name": "bgColor",
          "value": "0xFFFFFF"
        },
        {
          "name": "lnColor",
          "value": "0x000000"
        },
        {
          "name": "diffThreshold",
          "value": "800000"
        },
        {
          "name": "radius",
          "value": "2"
        }
      ]
    }
  ]
}
```

### Complete request

You can use tools like postman, but you can also use curl. This is an example of such request:
```text
$ curl -X POST --form filename=@testEye.png http://localhost:8080/images --form commands="{ \"commands\": [ { \"filter\": \"imageKuwahara\", \"settings\": [ { \"name\": \"square-size\", \"value\": \"2\"}, { \"name\": \"iterations\", \"value\": \"2\"} ]}, { \"filter\": \"imageContour\", \"settings\": [ { \"name\": \"bgColor\", \"value\": \"0xFFFFFF\"}, { \"name\": \"lnColor\", \"value\": \"0x000000\"}, { \"name\": \"diffThreshold\", \"value\": \"800000\"}, { \"name\": \"radius\", \"value\": \"2\"} ]} ] }"
```

### Results

Your results are stored wherever you define in your application.conf folder:

```text
akka {
  loglevel = DEBUG
  event-handlers = ["akka.event.slf4j.Slf4jEventHandler"]
}

service {
  host = "localhost"
  port = 8080
}

image-train-filters {
  image-source-path = "/tmp/image-train-filters/sources"
  image-destination-path = "/tmp/image-train-filters/destination"
}
```

Only important to note here that:

*   image-source-path -> This is where your original file is saved

*   image-destination-path -> This is where the resulting file is saved after applying the train of filters.

## Planning

*   Noise reducer

## Status

[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=informational)](http://itf.joaofilipesabinoesperancinha.nl/)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/image-train-filters-scala.svg)](#)

## Change log

2019/06/23:
-   Image Countour filter is selectable

2019/06/20:
-   DevOps innovatiomns started
-   Foundations of site [itf.joaofilipesabinoesperancinha.nl](http://itf.joaofilipesabinoesperancinha.nl)
-   Immediate return of generated image

2019/06/19:
-   Simple front end creation

## DevOps
```text
$ npm i -g npm  
$ npm install -g @angular/cli  
$ alias ng="/usr/local/lib/node_modules/@angular/cli/bin/ng"  
$ npm cache clean -f  
$ npm install -g n  
$ n stable  
$ echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list  
$ apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823  
$ apt-get update  
$ apt-get install sbt 
$ apt-get install default-jre 
```
---
##### Deprecated
```text
$ add-apt-repository ppa:webupd8team/java  
$ echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee /etc/apt/sources.list.d/webupd8team-java.list
$ echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
$ apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
$ apt install oracle-java8-installer  
```

### Git

```text
$ apt install gitk  
$ apt install git-gui
```

### image-train-filters-fe Module

#### Make a build

```text
$ npm install
$ ng build --aot=true
$ service nginx reload
```

### image-train-filters-service

#### Start services

$ sbt

## References

-   [nginx: multiple websites on one server](https://serverfault.com/questions/845739/nginx-multiple-websites-on-one-server)
-   [Set up multiple websites on a Digital Ocean droplet running nginx and node.js](https://coderwall.com/p/rldrxa/set-up-multiple-websites-on-a-digital-ocean-droplet-running-nginx-and-node-js)
-   [Fixing 413 Request Entity Too Large Errors](https://www.keycdn.com/support/413-request-entity-too-large)
-   [Postman](https://www.getpostman.com/)
-   [Update all the Node dependencies to their latest version](https://flaviocopes.com/update-npm-dependencies/)
-   [How to fix 'Unsupported platform for fsevents@1.2.9: wanted {“os”:“darwin”,“arch”:“any”} (current: {“os”:“win32”,“arch”:“x64”})](https://stackoverflow.com/questions/56103865/how-to-fix-unsupported-platform-for-fsevents1-2-9-wanted-osdarwin-arch)

## About me

[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social)](https://twitter.com/joaofse)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social)](https://github.com/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=BitBucket&message=jesperancinha&color=navy)](https://bitbucket.org/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitLab&message=jesperancinha&color=navy)](https://gitlab.com/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=6495ED)](http://joaofilipesabinoesperancinha.nl)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=6495ED)](http://tds.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=6495ED)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=DEV&message=Profile&color=green)](https://dev.to/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Medium&message=@jofisaes&color=green)](https://medium.com/@jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackernoon&message=@jesperancinha&color=green)](https://hackernoon.com/@jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Free%20Code%20Camp&message=jofisaes&color=008000)](https://www.freecodecamp.org/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackerrank&message=jofisaes&color=008000)](https://www.hackerrank.com/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Forces&message=jesperancinha&color=008000)](https://codeforces.com/profile/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Coder%20Byte&message=jesperancinha&color=008000)](https://coderbyte.com/profile/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Wars&message=jesperancinha&color=008000)](https://www.codewars.com/users/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Acclaim%20Badges&message=joao-esperancinha&color=red)](https://www.youracclaim.com/users/joao-esperancinha/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Badges.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Status.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Google%20Apps&message=Joao+Filipe+Sabino+Esperancinha&color=orange)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Pen&message=jesperancinha&color=orange)](https://codepen.io/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Android&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-android)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Java&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-modules/tree/master/itf-chartizate-java)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20API&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate/tree/master/itf-chartizate-api)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Core&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-core)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Filter&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-filter)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Docker%20Images&message=jesperanciha&color=099CEC)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/DockerImages.md)
