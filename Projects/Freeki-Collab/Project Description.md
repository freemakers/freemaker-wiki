<!-- Freeki metadata. Do not remove this section!
TITLE: Project Description
-->
<h1>Project Description</h1>

<p>The Freeki-Collab project is a hybrid hardware-software design that aims to create a device to provide note-taking and other collaboration software that doesn't require an AC outlet, an internet connection, or any other kind of infrastructural support besides the client machines used to access it. </p>

<h2>Goals</h2>

<ul>
<li>No power outlet required</li>
<li>At least 4 hours of continuous uptime without recharge</li>
<li>No wifi / internet access required; provide a wifi network that grants access to services on the system</li>
<li>No DNS / other naming infrastructure required; use mDNS to announce wiki, other services on the system</li>
<li>Facilitate note-taking with storage in a local Git repository clone that can be merged with a master branch when reconnected to the internet</li>
</ul>

<h2>Hardware Design</h2>

<p><img src="/static/images/freeki-collab/hardware-design.png" alt="hardware design diagram" title=""></p>

<p>The hardware design basically revolves around the Raspberry Pi, model A. Other hardware pieces are only necessary to provide off-grid power for the RPi. This includes a large LiPo cell with charger and DC-DC boost converter. While it's possible to use two LiPo cells and avoid the boost converter, this drastically complicates the circuit required to charge the system. So, there is a trade-off and it's not clear which is the better approach.</p>

<h2>Software</h2>

<p>The base system will be the Raspbian flavor of Debian linux, which has been built with the RPi hardware requirements in mind. On top of this, the system will use <a href="http://en.wikipedia.org/wiki/Avahi_%28software%29">Avahi</a> for mDNS announcements, <a href="http://en.wikipedia.org/wiki/HostAP"><code>hostap</code></a> for hosting its own WiFi network, and <a href="/wiki/Projects/Freeki/Project%20Description">Freeki</a> for the wiki.</p>

<p>This is the basic setup; it's certainly possible that other software (such as <code>dhcpd</code>) will be required in order to support these three essential services.</p>