# Druidia

Druidia is a really simple HTTP endpoint for logging (JSON) messages.

Make an HTTP POST to the URL `/:type` and it logs the message to a file
with a name that matches `type`

## Build & Run

After cloning the repo

    $ cd <REPODIR>

To build an executable jar:

    $ sbt clean assembly

To run it:

    $ java -jar target/scala/2.10/druidia-assembly-<VERSION>.jar

Druidia uses [logback](http://logback.qos.ch) to do all actual 
work. You can configure logback through the `logback.xml` file.

Log files are automatically rotated into an archive directory after a
default 10 minutes. This property can be configured in `logback.xml` by
changing the `interval` property.
