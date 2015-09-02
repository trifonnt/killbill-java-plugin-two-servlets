killbill-java-plugin-two-servlets
=================================

Kill Bill plugin in Java with two Servlets. It shows how to:

* Build an OSGI plugin using Maven
* Listen to Kill Bill events
* Call Kill Bill APIs from the plugin
* Register two custom HTTP servlets

To build, run `mvn clean install`. You can then install the plugin (`target/killbill-java-plugin-two-servlets-*.jar`) in `/var/tmp/bundles/plugins/java/two-servlets/1.0`.

End point for servlets:
http://127.0.0.1:8080/plugins/killbill-two-servlets_one
http://127.0.0.1:8080/plugins/killbill-two-servlets_two