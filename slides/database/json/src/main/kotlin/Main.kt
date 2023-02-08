import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Project(
    val name: String,
    val owner: Account,
    val group: String = "R&D"
)

@Serializable
data class Account(val userName: String)

val moonshot = Project("Moonshot", Account("Jane"))
val cleanup = Project("Cleanup", Account("Mike"), "Maint")

fun main() {
    val string = Json.encodeToString(listOf(moonshot, cleanup))
    println("String: $string")
    // [{“name":"Moonshot","owner":{"userName":"Jane"}},{"name":"Cleanup","owner" {"userName":"Mike"},"group":"Maint"}]

    val projectCollection = Json.decodeFromString<List<Project>>(string)
    println("JSON: $projectCollection")
    // [Project(name=Moonshot, owner=Account(userName=Jane), group=R&D), Project(name=Cleanup, owner=Account(userName=Mike), group=Maint)]
}
