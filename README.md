# image-train-filters

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b45a0bd9c3694401b78580b95c0b505b)](https://www.codacy.com/app/jofisaes/image-train-filters-scala?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/image-train-filters-scala&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/93dcee14-b81d-4827-bd4f-9c7a3f56c752)](https://codebeat.co/projects/github-com-jesperancinha-image-train-filters-scala-master)
[![CircleCI](https://circleci.com/gh/jesperancinha/image-train-filters-scala.svg?style=svg)](https://circleci.com/gh/jesperancinha/image-train-filters-scala)
[![Build Status](https://travis-ci.org/jesperancinha/image-train-filters-scala.svg?branch=master)](https://travis-ci.org/jesperancinha/image-train-filters-scala)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/image-train-filters-scala?branch=master)](https://bettercodehub.com/)
---
## Description

Webservice which returns a filtered image after applying selectable filters

*   Using Image Contour filter:

From <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" width="200" /> To <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPantherOut.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPantherOut.jpg" width="200" />

*   Using Kuwahara filter:

From <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther.jpg" width="200" /> To <img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPanther1.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testPantherOut.jpg" width="200" />

## How to use

You need to provide two form-data parameters in a Multipart Formdata request.
Use curl for that please. An example follows bellow.

### filename
*   this is the where you load your image file in

### commands
*   this is where you set your commands. there are only two examples at the moment:

1.  Kuwahara filter:

> It smooths the image

2.  Image contour filter:

> Processes differences in color ranges
>
> These requests you can apply as many times as you like using a JSON request. Read following example for more.

### Command example

```
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
```
$ curl -X POST --form filename=@testEye.png http://localhost:8080/images --form commands="{ \"commands\": [ { \"filter\": \"imageKuwahara\", \"settings\": [ { \"name\": \"square-size\", \"value\": \"2\"}, { \"name\": \"iterations\", \"value\": \"2\"} ]}, { \"filter\": \"imageContour\", \"settings\": [ { \"name\": \"bgColor\", \"value\": \"0xFFFFFF\"}, { \"name\": \"lnColor\", \"value\": \"0x000000\"}, { \"name\": \"diffThreshold\", \"value\": \"800000\"}, { \"name\": \"radius\", \"value\": \"2\"} ]} ] }"
```

### Results

Your results are stored wherever you define in your application.conf folder:

```
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

## Status

**Under development**

## Change log:

2019/06/23:
-   Image Countour filter is selectable

2019/06/20:
-   DevOps innovatiomns started
-   Foundations of site [itf.joaofilipesabinoesperancinha.nl](http://itf.joaofilipesabinoesperancinha.nl)
-   Immediate return of generated image

2019/06/19:
-   Simple front end creation

## DevOps

>$ npm i -g npm  
>$ npm install -g @angular/cli  
>$ alias ng="/usr/local/lib/node_modules/@angular/cli/bin/ng"  
>$ npm cache clean -f  
>$ npm install -g n  
>$ n stable  
>$ echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list  
>$ apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823  
>$ apt-get update  
>$ apt-get install sbt 
>$ apt-get install default-jre 
---
##### Deprecated
>$ add-apt-repository ppa:webupd8team/java  
>$ echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee /etc/apt/sources.list.d/webupd8team-java.list
>$ echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
>$ apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
>$ apt install oracle-java8-installer  

### Git

>$ apt install gitk  
>$ apt install git-gui

### image-train-filters-fe Module

#### Make a build

>$ npm install
>$ ng build --aot=true
>$ service nginx reload

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

-   Twitter [@jofisaes](https://twitter.com/jofisaes)
-   GitHub [jesperancinha](https://github.com/jesperancinha)
-   Free Code Camp [jofisaes](https://www.freecodecamp.org/jofisaes)
-   Hackerrank [jofisaes](https://www.hackerrank.com/jofisaes)
-   Acclaim Badges [joao-esperancinha](https://www.youracclaim.com/users/joao-esperancinha/badges)
-   Home Page [joaofilipesabinoesperancinha](http://joaofilipesabinoesperancinha.nl)

## License

```
Copyright 2016-2019 João Esperancinha (jesperancinha)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

