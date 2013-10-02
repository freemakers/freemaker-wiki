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

### 2013-09-30

- Freeki software is largely stable, but it's still missing features for the content fork/push sync actions that will be critical to this project.
    - See [Simplified Collaboration Feature](http://localhost:8080/wiki/Projects/Freeki/Planning/Simplified%20Collaboration%20Feature#).
- Tested on RPi Model A.
    - Model A doesn't have enough RAM to run Freeki + Debian, much less Avahi + hostap + DHCPd
    - Testing on RPi model B next, which shouldn't have any problem
- Waiting on parts to assemble a prototype case + battery circuit to take the RPi off-grid

### 2013-10-01

- Tested on RPi Model B, without much improvement.
    - Determined that the performance problem was in the version of Java installed; OpenJDK isn't useful on the RPi for whatever reason. Switched to Oracle Java 7, and things are reasonably usable even on the Model A.
- Developed initial configurations for operating system services:
    - dhcpd
    - avahi
    - hostapd
- Further (very minor) tweaks to Freeki.
- Received prototype case; RPi and battery fit with more room to spare than I expected.

## ToDo's

1. <s>Test Freeki 0.5-SNAPSHOT on the RPi model B</s>
2. <s>Create a working `hostap` configuration that exposes the RPi as an open access point (*NOTE: Is it worthwhile to look at locking it down?*)</s>
3. <s>Create a simple Avahi service configuration for Freeki</s>
4. <s>Create a simple DHCPd configuration</s>
5. Develop a method to switch from AP mode to network client mode, to enable pushing content changes up when the device is reconnected
    - Drive this via hardware button?? Could have it switch, sync, and switch back...
6. Implement the **Simplified Collaboration Feature** in Freeki (see above)
7. Assemble the battery circuit, demo uptime for the RPi running all services
8. Assemble the prototype case

## Design Documents

- [Hardware Design](Hardware%20Design)
- [Software](Software)
