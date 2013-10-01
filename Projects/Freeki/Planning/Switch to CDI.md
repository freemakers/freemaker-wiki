<!-- Freeki metadata. Do not remove this section!
TITLE: Switch to CDI
-->
#Switch to CDI

With newer features coming online, such as optional GitHub-specific add-ons, we are likely to need something less static than the current hard-coded initialization in the Main class. The simplest way to achieve this is using the JavaEE Configuration and Dependency Injection (CDI) standard - [Weld][1], specifically - to load the application as a collection of components. This allows interaction via interfaces and pluggable implementations more easily.


  [1]: http://docs.jboss.org/weld/reference/