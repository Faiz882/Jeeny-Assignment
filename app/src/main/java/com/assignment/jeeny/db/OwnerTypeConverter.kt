package com.assignment.jeeny.db

import androidx.room.TypeConverter
import com.assignment.jeeny.model.Owner
import org.json.JSONObject

class OwnerTypeConverter {
    @TypeConverter
    fun fromSource(owner: Owner): String {
        return JSONObject().apply {
            put("avatar_url", owner.avatarUrl)
        }.toString()
    }

    @TypeConverter
    fun toSource(owner: String): Owner {
        val json = JSONObject(owner)
        return Owner(json.get("avatar_url").toString())
    }
}