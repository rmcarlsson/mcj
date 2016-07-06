# Grainbrain - a Grainfather control system

Grainbrain is a simple control system for Grainfather. 

### System overview

* Runs on Raspberry PI
* Uses cheap and standard 1-wire sensor, DS1802
* Provides gRPC interface
* Based on Google protocol buffers, hence possible to easy implement clients in on Android, IPhone, Windows, Linux or any other platform that support Java, C#, Objective-C, C++.


### Excluded features
* No control provided for pump. As most users of Grainfather have removed the saftey ball valve in the overflow pipe automatic control of pump can hence lead to injuries. Therefor Grainbrain does not include support for pump control. The pump must hence be controlled manually by the Grainfather operator.

### gRPC services

* Load brew profile.
** Loads mash step profile, boil time and boil hop additions.
* Start and stop the brew process. Stop is only intended for brew process abort. There is no "pause" service.
* Mash sparge done notification. The brew system must be informed, from the brewer, that the sparge is complete and the boil can be started.
* Counter flow chiller sanitized. The counter flow chiller must be sanitized during or after the boil prior to chilling the wort. The brewer must hence notify the brew system that the chiller has been sanitized before the power during boil shall be switched off. Note, the boil will continue until this notification has been sent to the 


## Roadmap

* Introduce post-boil hop additions. This feature should provides support for temperature control for post-boil hop additions. Different temperatures have different IBU contributions, this requires a profile type very similar to a mash profile, possibly with hop addition information.
* 

