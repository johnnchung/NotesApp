module application {
    requires kotlin.stdlib;
    requires javafx.controls;
    requires kotlinx.coroutines.core.jvm;
    requires shared;
    requires javafx.web;
    requires kotlinx.serialization.core;
    requires kotlinx.serialization.json;
    requires org.controlsfx.controls;
    requires java.sql;
    requires org.jsoup;
    requires java.net.http;
    exports net.codebot.application;
}