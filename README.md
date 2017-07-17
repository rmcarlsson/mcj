# Grainbrain - a Grainfather control system

Grainbrain is a simple control system for Grainfather. It has no graphical user interface and relies fully on control over gRPC, see below. 

### System overview

* Runs on Raspberry PI as a service
* Uses cheap and standard 1-wire sensor, DS1802
* Provides gRPC interface
* Based on Google protocol buffers, hence possible to easy implement clients in on Android, IPhone, Windows, Linux or any other platform that support Java, C#, Objective-C, C++. A fully working client implementation can be found in the Windows application Grainsim.


### Excluded features
* No control provided for pump. As most users of Grainfather have removed the safety ball valve in the overflow pipe automatic control of pump can hence lead to injuries. Therefore Grainbrain does not include support for pump control. The pump must hence be controlled manually by the Grainfather operator.



### gRPC services
* LoadBrewProfile - Load brew profile. Loads mash step profile, boil time and boil hop additions.
* StartStopAbort - Start and stop the brew process. Stop is only intended for brew process abort. There is no "pause" service.
* GrainsAdded - Notofocation from brewer to grainbrain that grains have been added and mash schedule may begin.
* SpargeDone - Mash sparge done notification. The brew system must be informed, from the brewer, that the sparge is complete and the boil can be started.
* WortChillerSanitizedDone - Counter flow chiller sanitized. The counter flow chiller must be sanitized during or after the boil prior to chilling the wort. The brewer must hence notify the brew system that the chiller has been sanitized before the power during boil shall be switched off. Note, the boil will continue until this notification has been sent to the 
* GetStatus - Get brew system status. Provides information about remaining ateps and time in mamsh profile, remaining boil time and overall progress.

## Roadmap

* Introduce post-boil hop additions. This feature should provides support for temperature control for post-boil hop additions. Different temperatures have different IBU contributions, this requires a profile type very similar to a mash profile, possibly with hop addition information.
* Support for multiple Grainbrains in the same subnetwork. Currently the behaviour when using two Grainbrains in the same subnetwork is undefined.
* Add support for ADS1115 to use for temperature measurement. Minor change as pi4j is already used. Mainly needs to documentation updates


# User instructions

## Software instruction

### Prerequisites
The application uses some predefined Google protocol buffer messages, like Empty.proto. These can not be fetched by Maven and must hence be installed manually. So just download Google protocol buffers from github. Next update the PROTOBUF_IMPORT path in build-proto.bat. 

### Building deployment

In Eclipse, make sure, Window->Preferences. Select Java->Installed JRE. Make sure you have a JDK-version of Java here. If not add one. Next, expand Installed JRE's and check the JDK you just selected in the previous step.

Build using Maven build "compile" and "install". 

## Hardware installation

### Temperature sensor connections
Connect GND and 3,3V from Raspberry PI to DS1820. Connect DQ to GPIO4. Connect a 4,7kohm resistor between 3,3V and GPIO4.

### Heater switch
The heater control is realized using a 5 second time base PWM. It is possible to use both a standrad rely or a solid state relay. Both solutions require a driver. To solve this use a ULN2003A driver circuit. I have use a standard Adruino step motor board. These are available in many different versions, for example, http://www.elecrow.com/wiki/index.php?title=ULN2003_Stepper_Motor_Driver.

Connect the input to GPIO23. Connect the driver output to negative connetion on the solid state circuit. Connect positive side of solid state connector to +5V.   

