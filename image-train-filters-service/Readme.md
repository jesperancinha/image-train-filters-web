# Image Train Filters

## Deploy

### Make an assembly or package

```text
$ sbt assembly 
$ sbt package
```

### Run your package (notice the relationship)

```text
$ java -jar <package.jar>
$ scala <package.jar>
```

### Run with extra memory locally

$ nohup java -Xmx2048m -jar image-train-filters-service-assembly-1.0.jar &

## Run as a service

```text
$ sudo vim /etc/systemd/system/itf.service  
$ sudo vim itf-service  
$ sudo chmod +x itf-service  
$ sudo systemctl daemon-reload  
$ sudo systemctl enable itf  
$ sudo systemctl start itf  
$ sudo systemctl status itf  
```

## Increase virtual memory

Login as root:

```text
$ fallocate -l 5G /swapfile  
$ dd if=/dev/zero of=/swapfile bs=4500 count=1048576  
$ chmod 600 /swapfile  
$ mkswap /swapfile  
$ swapon /swapfile  
```

-   [How to Increase Virtual Memory in Linux](https://www.techwalla.com/articles/how-to-increase-virtual-memory-in-linux)
-   [Create a Linux Swap File](https://linuxize.com/post/create-a-linux-swap-file/)

## References

-   [SBT: How to deploy a single, executable Jar file](https://alvinalexander.com/scala/sbt-how-build-single-executable-jar-file-assembly)
-   [Run Your Java App as a Service on Ubuntu](https://dzone.com/articles/run-your-java-application-as-a-service-on-ubuntu)
-   [How To Configure a Linux Service to Start Automatically After a Crash or Reboot – Part 1: Practical Examples](https://www.digitalocean.com/community/tutorials/how-to-configure-a-linux-service-to-start-automatically-after-a-crash-or-reboot-part-1-practical-examples)

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

-   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha&color=informational)](http://joaofilipesabinoesperancinha.nl)

-   [![Twitter Follow](https://img.shields.io/twitter/follow/jofisaes.svg?label=%40jofisaes&style=social)](https://twitter.com/intent/follow?screen_name=jofisaes)

-   [![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social)](https://github.com/jesperancinha)

-   Free Code Camp [jofisaes](https://www.freecodecamp.org/jofisaes)

-   Hackerrank [jofisaes](https://www.hackerrank.com/jofisaes)

-   Acclaim Badges [joao-esperancinha](https://www.youracclaim.com/users/joao-esperancinha/badges)

-   Projects:

    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=informational)](http://tds.joaofilipesabinoesperancinha.nl/)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=informational)](http://itf.joaofilipesabinoesperancinha.nl/)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=informational)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Google%20Apps&message=Joao+Filipe+Sabino+Esperancinha&color=informational)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)

-   Releases:
    -   itf-chartizate-android:   
        [![Maven Central](https://img.shields.io/maven-central/v/org.jesperancinha.itf/itf-chartizate-android)](https://search.maven.org/search?q=a:itf-chartizate-android) 
        [![Download](https://api.bintray.com/packages/jesperancinha/maven/itf-chartizate-android/images/download.svg)](https://bintray.com/jesperancinha/maven/itf-chartizate-android/_latestVersion)

    -   itf-chartizate-java:   
        [![Maven Central](https://img.shields.io/maven-central/v/org.jesperancinha.itf/itf-chartizate)](https://search.maven.org/search?q=a:itf-chartizate)

    -   itf-chartizate-api:  
        [![Maven Central](https://img.shields.io/maven-central/v/org.jesperancinha.itf/itf-chartizate-api)](https://search.maven.org/search?q=a:itf-chartizate-api)
