# TRACKING SERVER UDP

### Main function
> TrackingServerUDP
### List Project File
```
BaseServerEventReceive
MessageAnalysisProcess
Server
TrackingServerUDP
```

```
Luồng sử lý 
--------------.
.             .    đẩy dữ liệu nhận dc
. SERVER.java .   --------------------> BaseServerEventReceive.Java 
.             .                                     |
--------------                                      | triger và đưa dữ liệu qua MesageAnalysisProcess 
   Thread 1                                         | để tiến hành sử lý dữ liệu 
                                            -------------------------
                                            |                       |
              Response        <-------------| MesageAnalysisProcess |
                                            |                       |
                                             ------------------------
                                                    Thread 2
```