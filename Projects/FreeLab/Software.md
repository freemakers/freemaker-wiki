<!-- Freeki metadata. Do not remove this section!
TITLE: Software
-->
<h1>Software</h1>

<h2>Operating System / Infrastructure Services</h2>

<p>The base system will be the Raspbian flavor of Debian linux, which has been built with the RPi hardware requirements in mind. </p>

<p>On top of this, the system will use:</p>

<ul>
<li><a href="http://en.wikipedia.org/wiki/Avahi_%28software%29">Avahi</a> for mDNS announcements</li>
<li><a href="http://en.wikipedia.org/wiki/HostAP">hostap</a> for hosting its own WiFi network</li>
<li><a href="http://en.wikipedia.org/wiki/DHCPD">dhcpd</a> for allocating IP addresses to clients</li>
<li><a href="http://www.git-scm.com">git</a> for operating-system-level automation scripts, etc.</li>
<li>Java 1.7</li>
</ul>

<h3>Avahi</h3>

<p>To install and enable Avahi:</p>

<pre><code>$ sudo aptitude -y install avahi-daemon avahi-utils
$ sudo update-rc.d avahi-daemon enable
</code></pre>

<h3>DHCPd</h3>

<p>DHCPd is a service that dynamically allocates IP addresses. In the absence of some other authority to hand out IP addresses, the FreeLab will have to provide this function in order to provide truly plugin-and-play behavior.</p>

<p>The Raspbian distribution of Linux uses BusyBox <code>udhcpd</code> as its DHCP service. To install:</p>

<pre><code>$ sudo aptitude -y install udhcpd
</code></pre>

<p>Once installed, I still had to configure the IP address range that the service can allocate. I changed the top few lines in the <code>/etc/udhcpd.conf</code> file:</p>

<pre><code>start           10.0.0.10       #default: 192.168.0.20
end             10.0.0.100      #default: 192.168.0.254


# The interface that udhcpd will use

interface       wlan0           #default: eth0    
</code></pre>

<p>Then, I need to enable the service. To do this, change the first non-comment line of <code>/etc/default/udhcpd</code> file to this:</p>

<pre><code>DHCPD_ENABLED="yes"
</code></pre>

<p>It's probably also a good idea to specifically enable the startup script too:</p>

<pre><code>$ sudo update-rc.d udhcpd enable
</code></pre>

<h3>Hostapd</h3>

<p>This one is a little trickier, depending on the wifi dongle you're using. I'm using an Edimax, which means I need to use the <code>rtl871xdrv</code> driver. Unfortunately, the <code>hostapd</code> that comes with Raspbian (the RPi Debian distro) doesn't support this driver. I found a blog post that covers it, though:</p>

<p><a href="http://www.daveconroy.com/turn-your-raspberry-pi-into-a-wifi-hotspot-with-edimax-nano-usb-ew-7811un-rtl8188cus-chipset/">http://www.daveconroy.com/turn-your-raspberry-pi-into-a-wifi-hotspot-with-edimax-nano-usb-ew-7811un-rtl8188cus-chipset/</a></p>

<p>You still need to install hostapd from Raspbian:</p>

<pre><code>$ sudo aptitude -y install hostapd
</code></pre>

<p>After this installs, download and update the <code>hostapd</code> executable:</p>

<pre><code>$ sudo su -
$ wget http://www.daveconroy.com/wp3/wp-content/uploads/2013/07/hostapd.zip
$ unzip hostapd.zip 
$ mv /usr/sbin/hostapd /usr/sbin/hostapd.bak
$ mv hostapd /usr/sbin/hostapd
$ chown root.root /usr/sbin/hostapd 
$ chmod 755 /usr/sbin/hostapd
</code></pre>

<p>Then, you'll need to configure it. I'm using the following <code>/etc/hostapd/hostapd.conf</code> file:</p>

<pre><code>interface=wlan0
ssid=freelab0
wpa=0
channel=1
</code></pre>

<p>Now, a lot of the more traditional settings are missing from this configuration. First, it's completely wide open...which supports an environment of transparency and collaboration. Still, it may not be right for all use cases. Besides that, I'm not configuring a bridge for network connectivity to the internet. This is because the FreeLab is meant to operate in isolation, without the benefit of an internet uplink.</p>

<p>Next, enable the <code>hostapd</code> service. Edit the <code>/etc/default/hostapd</code> file and add the line:</p>

<pre><code>DAEMON_CONF="/etc/hostapd/hostapd.conf"
</code></pre>

<p>Finally, you'll need to setup your wifi connection to use a static IP address. Edit <code>/etc/network/interfaces</code>, commenting out the existing <code>wlan0</code> and <code>wpa_supplicant</code> lines in the configuration and adding this:</p>

<pre><code>iface wlan0 inet static
    address 10.0.0.1
    netmask 255.255.255.0
</code></pre>

<p><strong>NOTE:</strong> You'll want preserve the old <code>wlan0</code> configurations so you can restore them in order to re-establish internet access for system updates.</p>

<p>Now reboot, and your RPi should come up as a wifi access point.</p>

<h3>Java</h3>

<p>When using the wiki on the Raspberry Pi, it's critical to use Oracle Java 7 instead of OpenJDK 7. </p>

<p>To install:</p>

<pre><code>$ sudo aptitude update
$ sudo aptitude -y install oracle-java7-jdk
</code></pre>

<h2>Services</h2>

<h3>Wiki</h3>

<p>Of course, the main service initially planned for this appliance is <a href="/wiki/Projects/Freeki/Overview#">Freeki</a>.</p>

<h3>File Sharing</h3>

<p>It may also be a good idea to expose a few locations on the RPi via <a href="http://en.wikipedia.org/wiki/Samba_%28software%29">Samba</a>. </p>

<ul>
<li>The wiki content directory, to enable non-web editors.</li>
<li>A dead-drop style share for files that aren't destined for the wiki (as a free-form collaboration feature)</li>
</ul>