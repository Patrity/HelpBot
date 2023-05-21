import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors


val knownCommon = arrayOf(
    "8bitjeff",
    "bigrat",
    "jeff",
    "BeyondFireship",
    "Fireship",
    "angular",
    "aws",
    "git",
    "googlecloud",
    "kotlinold",
    "css",
    "fauna",
    "firebase",
    "flutter",
    "hugo",
    "ionic",
    "nextjs",
    "node",
    "prisma",
    "react",
    "redis",
    "rxjs",
    "stripe",
    "supabase",
    "svelte",
    "tauri",
    "vite",
    "vscode",
    "vue",
    "youtube",
)

val knownNames: Map<String, String> = mapOf(
    "c_" to "C",
    "cpp" to "C++",
    "csharp" to "C#",
    // "css" to "CSS",
    "dart~1" to "Dart",
    "elixir" to "Elixir",
    "fortran" to "Fortran",
    "golang" to "Go",
    "haskell" to "Haskell",
    "java" to "Java",
    "javascript" to "JavaScript",
    "kotlin" to "Kotlin",
    "lua" to "Lua",
    "php" to "PHP",
    "python" to "Python",
    "ruby" to "Ruby",
    "rust" to "Rust",
    "swift" to "Swift",
    "typescript" to "TypeScript",

)

val guildId = args[0]
val token = args[1]
val url = "https://discord.com/api/v10/guilds/$guildId/emojis"
val authorizationHeader = "Bot $token"

try {
    val apiUrl = URL(url)
    val connection = apiUrl.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.setRequestProperty("Authorization", authorizationHeader)
    val responseCode = connection.responseCode
    if (responseCode == HttpURLConnection.HTTP_OK) {
        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        val jsonResponse =
            reader.lines().collect(Collectors.joining()).replace("\\[|\\]|\\{|\\}".toRegex(), "").split(",")
        println(jsonResponse)

        val emojis: ArrayList<Pair<String, String>> = arrayListOf()
        for (i in 1 until jsonResponse.size) {
            val prev = jsonResponse[i - 1]
            val current = jsonResponse[i]

            if (prev.contains("\"id\"") && current.contains("\"name\"")) {
                emojis.add(
                    Pair(
                        prev.replace(" \"id\": \"", "").dropLast(1),
                        current.replace(" \"name\": \"", "").dropLast(1)
                    )
                )
            }
        }

        for (emojiNode in emojis) {
            println(", ")
            val displayName = knownNames.get(emojiNode.second) ?: emojiNode.second.replace("\\~1|\\_|".toRegex(), "")
            val id = emojiNode.first
            val indentifier = emojiNode.second
            val name = displayName.replace(" ", "_").uppercase()

            val type = if (knownCommon.contains(indentifier)) "EmojiType.ROLE" else "EmojiType.MEME"

            print("$name(\"$displayName\", \"$indentifier\",\"$id\", $type)")
        }
        println(";")
        reader.close()
    } else {
        println("Error: $responseCode")
    }
    connection.disconnect()
} catch (e: IOException) {
    e.printStackTrace()
}

