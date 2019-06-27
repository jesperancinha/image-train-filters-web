# Image Train Filters

## Deploy

### Make an assembly or package

1. >$ sbt assembly
   
2. >$ sbt package

### Run your package (notice the relationship)

1. >$ java -jar <package.jar>
   
2. >$ scala <package.jar>

### Run with extra memory locally

$ nohup java -Xmx2048m -jar image-train-filters-service-assembly-1.0.jar &

## Run as a service

>$ sudo vim /etc/systemd/system/itf.service  
>$ sudo vim itf-service  
>$ sudo chmod +x itf-service  
>$ sudo systemctl daemon-reload  
>$ sudo systemctl enable itf  
>s udo systemctl start itf  
>$ sudo systemctl status itf  

## References

-   [SBT: How to deploy a single, executable Jar file](https://alvinalexander.com/scala/sbt-how-build-single-executable-jar-file-assembly)
-   [Run Your Java App as a Service on Ubuntu](https://dzone.com/articles/run-your-java-application-as-a-service-on-ubuntu)