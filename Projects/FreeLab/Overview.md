<!-- Freeki metadata. Do not remove this section!
TITLE: Project Overview
-->
# Project Overview

The FreeLab project is a hybrid hardware-software design that aims to create a device to provide note-taking and other collaboration software that doesn't require an AC outlet, an internet connection, or any other kind of infrastructural support besides the client machines used to access it.

## Goals

- No power outlet required
- At least 4 hours of continuous uptime without recharge
- No wifi / internet access required; provide a wifi network that grants access to services on the system
- No DNS / other naming infrastructure required; use mDNS to announce wiki, other services on the system
- Facilitate note-taking with storage in a local Git repository clone that can be merged with a master branch when reconnected to the internet

## Status

- Freeki software is largely stable, but it's still missing features for the content fork/push sync actions that will be critical to this project. See [Simplified Collaboration Feature](http://localhost:8080/wiki/Projects/Freeki/Planning/Simplified%20Collaboration%20Feature#).
- Established that the RPi model A doesn't have enough RAM to run Freeki + Debian, much less Avahi + hostap + DHCPd. Testing on RPi model B now, which shouldn't have any problem.
- Waiting on parts to assemble a prototype case + battery circuit to take the RPi off-grid

## ToDo's

1. Test Freeki 0.5-SNAPSHOT on the RPi model B
2. Create a working `hostap` configuration that exposes the RPi as an open access point (*NOTE: Is it worthwhile to look at locking it down?*)
3. Create a simple Avahi service configuration for Freeki
4. Create a simple DHCPd configuration
5. Implement the **Simplified Collaboration Feature** in Freeki (see above)
6. Assemble the battery circuit, demo uptime for the RPi running all services
7. Assemble the prototype case

## Hardware Design

![hardware design diagram](/static/images/freelab/hardware-design.png)

The hardware design basically revolves around the Raspberry Pi, model A. Other hardware pieces are only necessary to provide off-grid power for the RPi. This includes a large LiPo cell with charger and DC-DC boost converter. While it's possible to use two LiPo cells and avoid the boost converter, this drastically complicates the circuit required to charge the system. So, there is a trade-off and it's not clear which is the better approach.

### Prototype Case + Battery Circuit

#### Parts List

- [Hammond 1590XXOR Aluminum case](http://amzn.com/B0080GWL8A)
- [Waterproof Blue LED On/Off Switch](http://www.adafruit.com/products/915)
- [Tenergy LiPo 3.7V 4500 mAh Battery](http://is.gd/XldCev)
- [DC Voltage Booster Transformer](http://is.gd/CIk0p2)
- [USB Micro-B connector](www.adafruit.com/products/1390)
- [LiPo Charger](https://www.sparkfun.com/products/10217)
- [Raspberry Pi Model B](http://is.gd/SJAygC)
- [Sugru](http://www.adafruit.com/products/436) (for sealing holes, sticking things down, etc.)

## Software

The base system will be the Raspbian flavor of Debian linux, which has been built with the RPi hardware requirements in mind. On top of this, the system will use [Avahi](http://en.wikipedia.org/wiki/Avahi_%28software%29) for mDNS announcements, [hostap](http://en.wikipedia.org/wiki/HostAP) for hosting its own WiFi network, and [Freeki](/wiki/Projects/Freeki/Project%20Description) for the wiki.

This is the basic setup; it's certainly possible that other software (such as `dhcpd`) will be required in order to support these three essential services.
