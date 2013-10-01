<!-- Freeki metadata. Do not remove this section!
TITLE: Software
-->
#Software

## Operating System / Infrastructure Services

The base system will be the Raspbian flavor of Debian linux, which has been built with the RPi hardware requirements in mind. 

On top of this, the system will use:

- [Avahi](http://en.wikipedia.org/wiki/Avahi_%28software%29) for mDNS announcements
- [hostap](http://en.wikipedia.org/wiki/HostAP) for hosting its own WiFi network
- [dhcpd](http://en.wikipedia.org/wiki/DHCPD) for allocating IP addresses to clients
- [git](http://www.git-scm.com) for operating-system-level automation scripts, etc.
- Java 1.7

### Java

When using the wiki on the Raspberry Pi, it's critical to use Oracle Java 7 instead of OpenJDK 7. 

To install:

    $ aptitude update
    $ aptitude -y install oracle-java7-jdk


## Services

### Wiki

Of course, the main service initially planned for this appliance is [Freeki](/wiki/Projects/Freeki/Overview#).

### File Sharing

It may also be a good idea to expose a few locations on the RPi via [Samba](http://en.wikipedia.org/wiki/Samba_%28software%29). 

- The wiki content directory, to enable non-web editors.
- A dead-drop style share for files that aren't destined for the wiki (as a free-form collaboration feature)



