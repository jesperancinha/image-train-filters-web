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