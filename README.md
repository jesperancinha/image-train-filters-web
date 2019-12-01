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

## License

```text
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

## About me

-   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=informational)](http://joaofilipesabinoesperancinha.nl)

-   [![Generic badge](https://img.shields.io/static/v1.svg?label=DEV&message=Profile&color=informational)](https://dev.to/jofisaes)

-   [![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social)](https://github.com/jesperancinha)

-   Medium [@jofisaes](https://medium.com/@jofisaes)

-   Free Code Camp [jofisaes](https://www.freecodecamp.org/jofisaes)
 
-   Hackerrank [jofisaes](https://www.hackerrank.com/jofisaes)

-   Acclaim Badges [joao-esperancinha](https://www.youracclaim.com/users/joao-esperancinha/badges)

---

-   Projects:

    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=informational)](http://tds.joaofilipesabinoesperancinha.nl/)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=informational)](http://itf.joaofilipesabinoesperancinha.nl/)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=informational)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Google%20Apps&message=Joao+Filipe+Sabino+Esperancinha&color=informational)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
---

-   [![GitHub Logo](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/JEsperancinhaOrg-32.png)](https://github.com/JEsperancinhaOrg)

    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Android&color=informational)](https://github.com/JEsperancinhaOrg/itf-chartizate-android)[![Maven Central](https://img.shields.io/maven-central/v/org.jesperancinha.itf/itf-chartizate-android)](https://search.maven.org/search?q=itf.itf-chartizate-android)[![Download](https://api.bintray.com/packages/jesperancinha/maven/itf-chartizate-android/images/download.svg)](https://bintray.com/jesperancinha/maven/itf-chartizate-android/_latestVersion)[![GitHub release](https://img.shields.io/github/release-pre/JEsperancinhaOrg/itf-chartizate-android.svg)](https://github.com/JEsperancinhaOrg/itf-chartizate-android/releases)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Java&color=informational)](https://github.com/JEsperancinhaOrg/itf-chartizate-modules/tree/master/itf-chartizate-java)[![Maven Central](https://img.shields.io/maven-central/v/org.jesperancinha.itf/itf-chartizate-java)](https://search.maven.org/search?q=itf.itf-chartizate-java)[![GitHub release](https://img.shields.io/github/release-pre/JEsperancinhaOrg/itf-chartizate-modules.svg)](https://github.com/JEsperancinha/itf-chartizate-modules/releases)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20API&color=informational)](https://github.com/JEsperancinhaOrg/itf-chartizate/tree/master/itf-chartizate-api)[![Maven Central](https://img.shields.io/maven-central/v/org.jesperancinha.itf/itf-chartizate-api)](https://search.maven.org/search?q=itf.itf-chartizate-api)[![GitHub release](https://img.shields.io/github/release-pre/JEsperancinhaOrg/itf-chartizate.svg)](https://github.com/JEsperancinhaOrg/itf-chartizate/releases)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Core&color=informational)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-core)[![Maven Central](https://img.shields.io/maven-central/v/org.jesperancinha.parser/markdowner-core)](https://search.maven.org/search?q=parser.markdowner-core)[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/markdowner.svg)](https://github.com/jesperancinha/markdowner/releases)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Filter&color=informational)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-filter)[![Maven Central](https://img.shields.io/maven-central/v/org.jesperancinha.parser/markdowner-filter)](https://search.maven.org/search?q=parser.markdowner-filter)[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/markdowner.svg)](https://github.com/jesperancinha/markdowner/releases)

-   Status page:
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Project%20Status&color=informational)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Status.md)

---

-   Badges:

[![Oracle Certified Professional, Java SE 8 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-8-programmer.png)](https://www.youracclaim.com/badges/92e036f5-4e11-4cff-9935-3e62266d2074/public_url)
[![Oracle Certified Associate, Java SE 8 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-associate-java-se-8-programmer.png)](https://www.youracclaim.com/badges/a206436d-6fd8-4ca1-8feb-38a838446ee7/public_url)
[![Oracle Certified Associate, Java SE 7 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-associate-java-se-7-programmer.png)](https://www.youracclaim.com/badges/f4c6cc1e-cb52-432b-904d-36d266112225/public_url)
[![Deep Learning](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/deep-learning.png)](https://www.youracclaim.com/badges/8d27e38c-869d-4815-8df3-13762c642d64/public_url)
[![Scala Programming for Data Science - Level 2](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/scala-programming-for-data-science-level-2.png)](https://www.youracclaim.com/badges/4e26d062-6587-4bcf-b111-04f039318888/public_url)
[![Scala Programming for Data Science - Level 1](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/scala-programming-for-data-science-level-1.png)](https://www.youracclaim.com/badges/2471e6a1-05f9-40a1-9f13-92806875b690/public_url)
[![Spark - Level 2](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/spark-level-2.png)](https://www.youracclaim.com/badges/11c9792f-3e3d-4d01-89e9-3a9c1567e76c/public_url)
[![Spark - Level 2](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/spark-level-1.png)](https://www.youracclaim.com/badges/8de731f7-60f7-4fef-8748-5888168c76cb/public_url)
[![Applied Data Science with Python - Level 2](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/applied-data-science-with-python-level-2.png)](https://www.youracclaim.com/badges/ef84482b-c3cd-47d2-8d04-d3dd2b0c8aa3/public_url)
[![Python for Data Science](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/python-for-data-science.png)](https://www.youracclaim.com/badges/3cedbc65-f74b-4d17-81a2-121f214f811f/public_url)
[![Big Data Foundations - Level 2](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/big-data-foundations-level-2.png)](https://www.youracclaim.com/badges/d5de47d2-156c-4864-8605-13531b0df62e/public_url)
[![Big Data Foundations - Level 1](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/big-data-foundations-level-1.png)](https://www.youracclaim.com/badges/d216b767-fe19-4e83-b54b-4e1ffd937111/public_url)
[![Hadoop Foundations - Level 1](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/hadoop-foundations-level-1.png)](https://www.youracclaim.com/badges/3f7d55b3-b070-4fd1-a733-30c33c64e9a0/public_url)
[![Data Science Foundations - Level 1](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/data-science-foundations-level-1.png)](https://www.youracclaim.com/badges/51deb903-266a-4cc6-a401-ea80a8aaaeaf/public_url)
[![Streaming Analytics Basics for Python Developers](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/streaming-analytics-basics-for-python-developers.png)](https://www.youracclaim.com/badges/0a76635a-e612-4fee-9c44-ccfc54e02d77/public_url)
[![IBM Cloud Essentials](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/ibm-cloud-essentials.png)](https://www.youracclaim.com/badges/4e19b1b7-034c-4406-ac36-0fcbe9c46db7/public_url)
[![IBM Blockchain Essentials](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/ibm-blockchain-essentials.png)](https://www.youracclaim.com/badges/41c2b74c-668f-4d3a-a98c-8d6f5ce2757d/public_url)
