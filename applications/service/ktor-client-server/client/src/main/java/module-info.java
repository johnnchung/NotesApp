module net.codebot.client {
    requires kotlin.stdlib;
    requires io.ktor.client.core;
    requires io.ktor.client.cio;
    exports net.codebot.client;
    requires net.codebot.shared;
    requires javafx.graphics;
    requires javafx.controls;
    requires kotlinx.coroutines.core.jvm;
}
