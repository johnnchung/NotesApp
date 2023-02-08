module net.codebot.client {
    requires net.codebot.shared;
    requires kotlin.stdlib;
    requires kotlinx.serialization.core;
    requires java.net.http;
    requires kotlinx.serialization.json;
    exports net.codebot.client;
}
