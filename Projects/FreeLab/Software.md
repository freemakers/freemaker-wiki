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

### Avahi

To install and enable Avahi:

    $ sudo aptitude -y install avahi-daemon avahi-utils
    $ sudo update-rc.d avahi-daemon enable

### DHCPd

DHCPd is a service that dynamically allocates IP addresses. In the absence of some other authority to hand out IP addresses, the FreeLab will have to provide this function in order to provide truly plugin-and-play behavior.

The Raspbian distribution of Linux uses BusyBox `udhcpd` as its DHCP service. To install:

    $ sudo aptitude -y install udhcpd

Once installed, I still had to configure the IP address range that the service can allocate. I changed the top few lines in the `/etc/udhcpd.conf` file:

    start           10.0.0.10       #default: 192.168.0.20
    end             10.0.0.100      #default: 192.168.0.254
    
    
    # The interface that udhcpd will use
    
    interface       wlan0           #default: eth0    

Then, I need to enable the service. To do this, change the first non-comment line of `/etc/default/udhcpd` file to this:

    DHCPD_ENABLED="yes"

It's probably also a good idea to specifically enable the startup script too:

    $ sudo update-rc.d udhcpd enable
    

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

Next, enable the `hostapd` service. Edit the `/etc/default/hostapd` file and add the line:

    DAEMON_CONF="/etc/hostapd/hostapd.conf"

Finally, you'll need to setup your wifi connection to use a static IP address. Edit `/etc/network/interfaces`, commenting out the existing `wlan0` and `wpa_supplicant` lines in the configuration and adding this:

    iface wlan0 inet static
        address 10.0.0.1
        netmask 255.255.255.0

**NOTE:** You'll want preserve the old `wlan0` configurations so you can restore them in order to re-establish internet access for system updates.

Now reboot, and your RPi should come up as a wifi access point.

### Git

Git is a simple install. There is no service to enable, or configuration to fine tune:

    $ sudo aptitude -y install git

### Java

When using the wiki on the Raspberry Pi, it's critical to use Oracle Java 7 instead of OpenJDK 7. It's unfortunate, but the performance of OpenJDK 7 is terrible. This machine will never win any speed awards, but this will make it nearly unusable. It's what initially made me think that a RPi Model A wouldn't be big enough for this device (I was wrong).

To install:

    $ sudo aptitude update
    $ sudo aptitude -y install oracle-java7-jdk

## Services

### Wiki

Of course, the main service initially planned for this appliance is [Freeki](/wiki/Projects/Freeki/Overview#).

### File Sharing

It may also be a good idea to expose a few locations on the RPi via [Samba](http://en.wikipedia.org/wiki/Samba_%28software%29). 

- The wiki content directory, to enable non-web editors.
- A dead-drop style share for files that aren't destined for the wiki (as a free-form collaboration feature)



