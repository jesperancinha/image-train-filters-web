# Image Train Filters

---


[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Image%20Train%20Filters%20Web&color=informational)](https://github.com/jesperancinha/image-train-filters-web)

[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=informational)](http://itf.joaofilipesabinoesperancinha.nl/)

![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/image-train-filters-web.svg)
  
[![CircleCI](https://circleci.com/gh/jesperancinha/image-train-filters-web.svg?style=svg)](https://circleci.com/gh/jesperancinha/image-train-filters-web)
[![Build status](https://ci.appveyor.com/api/projects/status/7ahn4oacknhu7ax3?svg=true)](https://ci.appveyor.com/project/jesperancinha/image-train-filters-web)
[![Build image-train-filters-web](https://github.com/jesperancinha/image-train-filters-web/actions/workflows/image-train-filters.yml/badge.svg)](https://github.com/jesperancinha/image-train-filters-web/actions/workflows/image-train-filters.yml)
[![E2E e2e-image-train-filters](https://github.com/jesperancinha/image-train-filters-web/actions/workflows/image-train-filters-e2e.yml/badge.svg)](https://github.com/jesperancinha/image-train-filters-web/actions/workflows/image-train-filters-e2e.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/b7df04087bed40f1ad0ddd47b2def16d)](https://www.codacy.com/gh/jesperancinha/image-train-filters-web/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/image-train-filters-web&amp;utm_campaign=Badge_Grade)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/image-train-filters-web/badge.svg)](https://snyk.io/test/github/jesperancinha/image-train-filters-web)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/b7df04087bed40f1ad0ddd47b2def16d)](https://www.codacy.com/gh/jesperancinha/image-train-filters-web/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/image-train-filters-web&utm_campaign=Badge_Coverage)
[![codecov](https://codecov.io/gh/jesperancinha/image-train-filters-web/branch/master/graph/badge.svg?token=uFSrf1eF3l)](https://codecov.io/gh/jesperancinha/image-train-filters-web)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/image-train-filters-web/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/image-train-filters-web?branch=master)

![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/image-train-filters-web.svg)
![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/image-train-filters-web.svg)
![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/image-train-filters-web.svg)

---

## Description

Let's start with this image:

<img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarket.jpg" alt="testMarket" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarket.jpg" width="200" />
<br/>

Using Image Contour filter:

<img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarket.jpg" alt="testMarket" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarket.jpg" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketR.png" alt="testMarketR" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketR.png" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketG.png" alt="testMarketG" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketG.png" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketB.png" alt="testMarketB" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketB.png" width="200" />
<br/>

Using `Kuwahara` filter:

<img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarket.jpg" alt="testMarket" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarket.jpg" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketSmallBlur.png" alt="testMarketSmallBlur" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketSmallBlur.png" width="200" />
<br/>

Using `Chartizate` filter:

<img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarket.jpg" alt="testMarket" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarket.jpg" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketDarkBackground.png" alt="testMarketDarkBackground" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketDarkBackground.png" width="200" /><img src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketLightBackground.png" alt="testMarketLightBackground" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-train-filters-web/master/service/src/test/resources/testMarketLightBackground.png" width="200" />

Current running online implementation: [Image train filters](http://itf.joaofilipesabinoesperancinha.nl)

## NodeJs Multiproject

```bash
yarn config set workspaces-experimental true
```

## How to use
We need to provide two form-data parameters in a Multipart Formdata request.
Use curl for that, please. An example follows bellow.

### filename
-   this is where you load your image file in

### commands
-   this is where you set your commands. there are only two examples at the moment:

1.  `Kuwahara` filter:

> It smooths the image

2.  `Image contour` filter:

> Processes differences in color ranges
>
> These requests you can apply as many times as you like using a JSON request. Read the following example for more.

### Command example

```json
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

You can use tools like postman, but you can also use curl. This is an example of such a request:
```shell
curl -X POST --form filename=@testEye.png http://localhost:8080/images --form commands="{ \"commands\": [ { \"filter\": \"imageKuwahara\", \"settings\": [ { \"name\": \"square-size\", \"value\": \"2\"}, { \"name\": \"iterations\", \"value\": \"2\"} ]}, { \"filter\": \"imageContour\", \"settings\": [ { \"name\": \"bgColor\", \"value\": \"0xFFFFFF\"}, { \"name\": \"lnColor\", \"value\": \"0x000000\"}, { \"name\": \"diffThreshold\", \"value\": \"800000\"}, { \"name\": \"radius\", \"value\": \"2\"} ]} ] }"
```

### Results

Your results are stored wherever you define in your application.conf folder:

```conf
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

Only important to note here is that:

*   image-source-path → This is where your original file is saved

*   image-destination-path → This is where the resulting file is saved after applying the train of filters.

## DevOps
```shell
npm i -g npm  
npm install -g @angular/cli  
alias ng="/usr/local/lib/node_modules/@angular/cli/bin/ng"  
npm cache clean -f  
npm install -g n  
n stable  
echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list  
apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823  
apt-get update  
apt-get install sbt 
apt-get install default-jre 
```

### image-train-filters-fe Module

#### Make a build

```shell
yarn install
yarn build
service nginx reload
```

## Resources

-   [Akka Configuration Reference](https://doc.akka.io/docs/akka/current/general/configuration-reference.html)
-   [SBT release 1.5.0](https://eed3si9n.com/sbt-1.5.0)
-   [nginx: multiple websites on one server](https://serverfault.com/questions/845739/nginx-multiple-websites-on-one-server)
-   [Set up multiple websites on a Digital Ocean droplet running nginx and node.js](https://coderwall.com/p/rldrxa/set-up-multiple-websites-on-a-digital-ocean-droplet-running-nginx-and-node-js)
-   [Fixing 413 Request Entity Too Large Errors](https://www.keycdn.com/support/413-request-entity-too-large)
-   [Postman](https://www.getpostman.com/)
-   [Update all the Node dependencies to their latest version](https://flaviocopes.com/update-npm-dependencies/)
-   [How to fix 'Unsupported platform for fsevents@1.2.9: wanted {“os”:“darwin”,“arch”:“any”} (current: {“os”:“win32”,“arch”:“x64”})](https://stackoverflow.com/questions/56103865/how-to-fix-unsupported-platform-for-fsevents1-2-9-wanted-osdarwin-arch)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
