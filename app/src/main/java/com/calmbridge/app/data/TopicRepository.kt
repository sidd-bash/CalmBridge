package com.calmbridge.app.data

import android.content.Context
import com.calmbridge.app.model.Topic
import org.json.JSONArray


object TopicRepository {

    fun loadTopics(context: Context): List<Topic> {
        val jsonString = context.assets.open("topics.json")
            .bufferedReader()
            .use { it.readText() }

        val jsonArray = JSONArray(jsonString)
        val topics = mutableListOf<Topic>()

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)

            topics.add(
                Topic(
                    title = obj.getString("title"),
                    summary = obj.getString("summary"),
                    count = obj.getInt("count")
                )
            )
        }

        return topics
    }
}