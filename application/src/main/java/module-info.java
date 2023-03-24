module application {
    requires kotlin.stdlib;
    requires javafx.controls;
    requires kotlinx.coroutines.core.jvm;
    requires shared;
    requires javafx.web;
    requires kotlinx.serialization.core;
    requires kotlinx.serialization.json;
    requires java.sql;
    requires exposed.core;
    requires org.jsoup;
    exports net.codebot.application;
}