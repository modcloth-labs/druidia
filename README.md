# Druidia

Druidia is a really simple HTTP endpoint for logging messages.

1. Make an HTTP POST of a JSON blob to the URL `/:type` 
2. Druidia logs the message to a file with `type` in the filename!!

## Build & Run

Druidia uses [sbt](http://www.scala-sbt.org/) as a build tool. After cloning the repo

    $ cd <REPODIR>

To build an executable jar:

    $ sbt assembly

To run it:

    $ java -jar target/scala/2.10/druidia-assembly-<VERSION>.jar

Druidia uses [logback](http://logback.qos.ch) to do all actual 
work. You can configure logback through the `logback.xml` file.

Log files are automatically rotated into an archive directory after a
default 10 minutes. This property can be configured in `logback.xml` by
changing the `interval` property.
