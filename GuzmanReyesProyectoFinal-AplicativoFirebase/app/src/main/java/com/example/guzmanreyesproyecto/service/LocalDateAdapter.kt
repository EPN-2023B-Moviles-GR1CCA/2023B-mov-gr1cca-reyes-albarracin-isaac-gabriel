package com.example.guzmanreyesproyecto.service

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.lang.reflect.Type

class LocalDateAdapter : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun serialize(src: LocalDate, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDate {
        return LocalDate.parse(json.asString, DateTimeFormatter.ISO_LOCAL_DATE)
    }
}
