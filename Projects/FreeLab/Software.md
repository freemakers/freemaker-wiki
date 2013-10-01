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

    $ sudo aptitude update
    $ sudo aptitude -y install oracle-java7-jdk

### Avahi

To install and enable Avahi:

    $ sudo aptitude -y install avahi-daemon avahi-utils
    $ sudo update-rc.d avahi-daemon enable

### Hostapd

This one is a little trickier, depending on the wifi dongle you're using. I'm using an Edimax, which means I need to use the `rtl871xdrv` driver. Unfortunately, the `hostapd` that comes with Raspbian (the RPi Debian distro) doesn't support this driver. I found a blog post that covers it, though:

[http://www.daveconroy.com/turn-your-raspberry-pi-into-a-wifi-hotspot-with-edimax-nano-usb-ew-7811un-rtl8188cus-chipset/](http://www.daveconroy.com/turn-your-raspberry-pi-into-a-wifi-hotspot-with-edimax-nano-usb-ew-7811un-rtl8188cus-chipset/)

You still need to install hostapd from Raspbian:

    $ sudo aptitude -y install hostapd

After this installs, download and update the `hostapd` executable:

    $ sudo su -
    $ wget http://www.daveconroy.com/wp3/wp-content/uploads/2013/07/hostapd.zip
    $ unzip hostapd.zip 
    $ mv /usr/sbin/hostapd /usr/sbin/hostapd.bak
    $ mv hostapd /usr/sbin/hostapd
    $ chown root.root /usr/sbin/hostapd 
    $ chmod 755 /usr/sbin/hostapd

Then, you'll need to configure it. I'm using the following `/etc/hostapd/hostapd.conf` file:

    interface=wlan0
    ssid=freelab0
    wpa=0
    channel=1

Now, a lot of the more traditional settings are missing from this configuration. First, it's completely wide open...which supports an environment of transparency and collaboration. Still, it may not be right for all use cases. Besides that, I'm not configuring a bridge for network connectivity to the internet. This is because the FreeLab is meant to operate in isolation, without the benefit of an internet uplink.

Finally, you'll need to setup your wifi connection to use a static IP address. Edit `/etc/network/interfaces`, commenting out the existing `wlan0` and `wpa_supplicant` lines in the configuration and adding this:

    iface wlan0 inet static
        address 10.0.0.1
        netmask 255.255.255.0

**NOTE:** You'll want preserve the old `wlan0` configurations so you can restore them in order to re-establish internet access for system updates.

Now reboot, and your RPi should come up as a wifi access point.


## Services

### Wiki

Of course, the main service initially planned for this appliance is [Freeki](/wiki/Projects/Freeki/Overview#).

### File Sharing

It may also be a good idea to expose a few locations on the RPi via [Samba](http://en.wikipedia.org/wiki/Samba_%28software%29). 

- The wiki content directory, to enable non-web editors.
- A dead-drop style share for files that aren't destined for the wiki (as a free-form collaboration feature)



