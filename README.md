# Image Train Filters

---

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=%20Checkout%20this%20%40github%20repo%20by%20%40joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB%3A%20https%3A//github.com/jesperancinha/image-train-filters-scala)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Image%20Train%20Filters%20Services&color=informational)](https://github.com/jesperancinha/image-train-filters-scala)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=informational)](http://itf.joaofilipesabinoesperancinha.nl/)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/image-train-filters-scala.svg)](#)
  
[![CircleCI](https://circleci.com/gh/jesperancinha/image-train-filters-scala.svg?style=svg)](https://circleci.com/gh/jesperancinha/image-train-filters-scala)
[![Build Status](https://travis-ci.com/jesperancinha/image-train-filters-scala.svg?branch=master)](https://travis-ci.com/jesperancinha/image-train-filters-scala)
[![Build status](https://ci.appveyor.com/api/projects/status/rmiwrpoo9hipj28b/branch/master?svg=true)](https://ci.appveyor.com/project/jesperancinha/image-train-filters-scala/branch/master)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b45a0bd9c3694401b78580b95c0b505b?)](https://www.codacy.com/app/jofisaes/image-train-filters-scala?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/image-train-filters-scala&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/93dcee14-b81d-4827-bd4f-9c7a3f56c752)](https://codebeat.co/projects/github-com-jesperancinha-image-train-filters-scala-master)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/image-train-filters-scala?branch=master)](https://bettercodehub.com/)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/image-train-filters-scala/badge.svg)](https://snyk.io/test/github/jesperancinha/image-train-filters-scala)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/b45a0bd9c3694401b78580b95c0b505b)](https://www.codacy.com/gh/jesperancinha/image-train-filters-scala/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/image-train-filters-scala&utm_campaign=Badge_Coverage)
[![codecov](https://codecov.io/gh/jesperancinha/image-train-filters-scala/branch/master/graph/badge.svg?token=uFSrf1eF3l)](https://codecov.io/gh/jesperancinha/image-train-filters-scala)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/image-train-filters-scala/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/image-train-filters-scala?branch=master)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/image-train-filters-scala.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/image-train-filters-scala.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/image-train-filters-scala.svg)](#)

---

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/angular-50.png "Angular")](https://angular.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/typescript-50.png "Typescript")](https://www.typescriptlang.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/scala-50.png "Scala")](https://www.scala-lang.org/)

## Description

Let's start with this image:

<img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarket.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarket.jpg" width="200" />
<br/>

Using Image Contour filter:

<img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarket.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarket.jpg" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketR.png" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketR.png" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketG.png" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketG.png" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketB.png" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketB.png" width="200" />
<br/>

Using Kuwahara filter:

<img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarket.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarket.jpg" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketSmallBlur.png" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketSmallBlur.png" width="200" />
<br/>

Using Chartizate filter:

<img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarket.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarket.jpg" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketDarkBackground.png" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketDarkBackground.png" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketLightBackground.png" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-scala/master/image-train-filters-service/src/test/resources/testMarketLightBackground.png" width="200" />

Current running online implementation: [Image train filters](http://itf.joaofilipesabinoesperancinha.nl)

## NodeJs Multiproject

```bash
yarn config set workspaces-experimental true
```

## How to use
We need to provide two form-data parameters in a Multipart Formdata request.
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

## Docker images

This project makes use of the following docker images:

[![dockeri.co](https://dockeri.co/image/jesperancinha/je-all-build)](https://hub.docker.com/r/jesperancinha/je-all-build)

All source code for the [JE](https://bitbucket.org/jesperancinha/docker-images) images reside in repo [Docker images](https://bitbucket.org/jesperancinha/docker-images).

## Planning

*   Noise reducer

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

### image-train-filters-fe Module

#### Make a build

```text
$ yarn install
$ yarn build
$ service nginx reload
```

## References

-   [nginx: multiple websites on one server](https://serverfault.com/questions/845739/nginx-multiple-websites-on-one-server)
-   [Set up multiple websites on a Digital Ocean droplet running nginx and node.js](https://coderwall.com/p/rldrxa/set-up-multiple-websites-on-a-digital-ocean-droplet-running-nginx-and-node-js)
-   [Fixing 413 Request Entity Too Large Errors](https://www.keycdn.com/support/413-request-entity-too-large)
-   [Postman](https://www.getpostman.com/)
-   [Update all the Node dependencies to their latest version](https://flaviocopes.com/update-npm-dependencies/)
-   [How to fix 'Unsupported platform for fsevents@1.2.9: wanted {“os”:“darwin”,“arch”:“any”} (current: {“os”:“win32”,“arch”:“x64”})](https://stackoverflow.com/questions/56103865/how-to-fix-unsupported-platform-for-fsevents1-2-9-wanted-osdarwin-arch)

## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/credly-20.png "Credly")](https://www.credly.com/users/joao-esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=6495ED "João Esperancinha Homepage")](https://joaofilipesabinoesperancinha.nl/)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)   
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=Across%20The%20Web&color=purple)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Articles.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Webapp&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Info.md)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)   
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/github-20.png "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bintray-20.png "BinTray")](https://bintray.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeforces-20.png "Code Forces")](https://codeforces.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)

## Achievements

[![Oracle Certified Professional, JEE 7 Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-ee-7-application-developer-100.png "Oracle Certified Professional, JEE7 Developer")](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![Oracle Certified Professional, Java SE 11 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-11-developer-100.png "Oracle Certified Professional, Java SE 11 Programmer")](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![Oracle Certified Professional, Java SE 8 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-8-programmer-100.png "Oracle Certified Professional, Java SE 8 Programmer")](https://www.credly.com/badges/92e036f5-4e11-4cff-9935-3e62266d2074)
[![Oracle Certified Associate, Java SE 8 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-associate-java-se-8-programmer-100.png "Oracle Certified Associate, Java SE 8 Programmer")](https://www.credly.com/badges/a206436d-6fd8-4ca1-8feb-38a838446ee7)
[![Oracle Certified Associate, Java SE 7 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-associate-java-se-7-programmer-100.png "Oracle Certified Associate, Java SE 7 Programmer")](https://www.credly.com/badges/f4c6cc1e-cb52-432b-904d-36d266112225)
[![Oracle Certified Junior Associate](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-foundations-associate-java-100.png "Oracle Certified Foundations Associate")](https://www.credly.com/badges/6db92c1e-7bca-4856-9543-0d5ed0182794)
