package com.berangkat.kerja.model

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

data class Responses(val total: Int,
                     val data: Any? = null) {

    override fun toString(): String {
        val mapper = ObjectMapper()
        var jsonString = ""
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT)
            jsonString = mapper.writeValueAsString(this)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }

        return jsonString
    }
}