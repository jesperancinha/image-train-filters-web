## image-train-filters


#### Description

Webservice which returns a filtered image after applying selectable filters

* Using image contour:

From <img src="https://raw.githubusercontent.com/jesperancinha/image-contour/master/src/test/resources/testPanther.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-contour/master/src/test/resources/testPanther.jpg" width="200" /> To <img src="https://raw.githubusercontent.com/jesperancinha/image-contour/master/src/test/resources/testPantherOut.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-contour/master/src/test/resources/testPantherOut.jpg" width="200" />

* Using Kuwahara:

From <img src="https://raw.githubusercontent.com/jesperancinha/image-contour/master/src/test/resources/testPanther.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-contour/master/src/test/resources/testPanther.jpg" width="200" /> To <img src="https://raw.githubusercontent.com/jesperancinha/image-contour/master/src/test/resources/testPanther1.jpg" alt="" data-canonical-src="https://raw.githubusercontent.com/jesperancinha/image-contour/master/src/test/resources/testPantherOut.jpg" width="200" />

### How to use

You need to provide two form-data parameters in a Multipart Formdata request.
Use curl for that please. An example follows bellow.

#### filename
* this is the where you load your image file in

#### commands
* this is where you set your commands. there are only two examples at the moment:

1. Kuwahara filter:

> It smooths the image

2. Image contour filter:

> Processes differences in color ranges

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

* image-source-path -> This is where your original file is saved

* image-destination-path -> This is where the resulting file is saved after applying the train of filters.

### License

```
Copyright 2016-2017 João Esperancinha (jesperancinha)

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

### Status

** Under development **


### Notes

* https://www.getpostman.com/


### Location:

[GitHub location](https://github.com/jesperancinha/image-train-filters-scala)

* Twitter [@jofisaes](https://twitter.com/jofisaes)
* GitHub [jesperancinha](https://github.com/jesperancinha)
