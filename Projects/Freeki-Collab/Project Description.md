<!-- Freeki metadata. Do not remove this section!
TITLE: Project Description
-->
# Project Description

The Freeki-Collab project is a hybrid hardware-software design that aims to create a device to provide note-taking and other collaboration software that doesn't require an AC outlet, an internet connection, or any other kind of infrastructural support besides the client machines used to access it.

## Goals

- No power outlet required
- At least 4 hours of continuous uptime without recharge
- No wifi / internet access required; provide a wifi network that grants access to services on the system
- No DNS / other naming infrastructure required; use mDNS to announce wiki, other services on the system
- Facilitate note-taking with storage in a local Git repository clone that can be merged with a master branch when reconnected to the internet

## Hardware Design

![hardware design diagram](/static/images/freeki-collab/hardware-design.png)

The hardware design basically revolves around the Raspberry Pi, model A. Other hardware pieces are only necessary to provide off-grid power for the RPi. This includes a large LiPo cell with charger and DC-DC boost converter. While it's possible to use two LiPo cells and avoid the boost converter, this drastically complicates the circuit required to charge the system. So, there is a trade-off and it's not clear which is the better approach.

## Software

The base system will be the Raspbian flavor of Debian linux, which has been built with the RPi hardware requirements in mind. On top of this, the system will use [Avahi](http://en.wikipedia.org/wiki/Avahi_%28software%29) for mDNS announcements, [hostap](http://en.wikipedia.org/wiki/HostAP) for hosting its own WiFi network, and [Freeki](/wiki/Projects/Freeki/Project%20Description) for the wiki.

This is the basic setup; it's certainly possible that other software (such as <code>dhcpd</code>) will be required in order to support these three essential services.