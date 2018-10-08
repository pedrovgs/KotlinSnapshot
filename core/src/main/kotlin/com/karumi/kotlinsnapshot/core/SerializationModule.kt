package com.karumi.kotlinsnapshot.core

import com.google.gson.GsonBuilder
import com.karumi.kotlinsnapshot.core.serializers.RuntimeClassNameTypeAdapterFactory

interface SerializationModule {

    fun serialize(value: Any?): String
}

class KotlinSerialization : SerializationModule {

    companion object {
        private const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

        private val gson = GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat(ISO_8601)
            .registerTypeAdapterFactory(RuntimeClassNameTypeAdapterFactory.of(Object::class.java))
            .create()
    }

    override fun serialize(value: Any?): String =
        gson.toJson(value)
}
