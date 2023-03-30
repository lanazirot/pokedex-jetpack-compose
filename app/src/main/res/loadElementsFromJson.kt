suspend fun loadElementsFromJson(context: Context): List<Element> {
    val jsonString = context.assets.open("elements.json").bufferedReader().use { it.readText() }
    val gson = Gson()
    val listType = object : TypeToken<List<Element>>() {}.type
    return gson.fromJson(jsonString, listType)
}
