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

- Freeki software is largely stable, but it's still missing features for the content fork/push sync actions that will be critical to this project.
    - See [Simplified Collaboration Feature](http://localhost:8080/wiki/Projects/Freeki/Planning/Simplified%20Collaboration%20Feature#).
- Tested on RPi Model A.
    - Model A doesn't have enough RAM to run Freeki + Debian, much less Avahi + hostap + DHCPd
    - Testing on RPi model B next, which shouldn't have any problem
- Waiting on parts to assemble a prototype case + battery circuit to take the RPi off-grid

## ToDo's

1. Test Freeki 0.5-SNAPSHOT on the RPi model B
2. Create a working `hostap` configuration that exposes the RPi as an open access point (*NOTE: Is it worthwhile to look at locking it down?*)
3. Create a simple Avahi service configuration for Freeki
4. Create a simple DHCPd configuration
5. Implement the **Simplified Collaboration Feature** in Freeki (see above)
6. Assemble the battery circuit, demo uptime for the RPi running all services
7. Assemble the prototype case

## Design Documents

- [Hardware Design](Hardware%20Design)
- [Software](Software)
