package kg.tabiyat.db.converter

import androidx.room.TypeConverter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.tabiyat.data.model.Description
import kg.tabiyat.data.model.FavoriteModel
import kg.tabiyat.data.model.Name
import kg.tabiyat.db.entity.Abundance
import kg.tabiyat.db.entity.PlantsEntity
import java.lang.reflect.Type


class Converters {

    @TypeConverter
    fun fromJsonName(json: String): Name? {
        return initMapper()
            .readValue(json, Name::class.java)
    }

    @TypeConverter
    fun toJsonName(iconData: Name?): String? {
        return initMapper().writeValueAsString(iconData)
    }

    @TypeConverter
    fun fromJsonAbundance(json: String): Abundance? {
        return initMapper()
            .readValue(json, Abundance::class.java)
    }

    @TypeConverter
    fun toJsonAbundance(data: Abundance?): String? {
        return initMapper().writeValueAsString(data)
    }

    @TypeConverter
    fun fromJsonDescription(json: String): Description? {
        return initMapper()
            .readValue(json, Description::class.java)
    }

    @TypeConverter
    fun toJsonDescription(data: Description?): String? {
        return initMapper().writeValueAsString(data)
    }

    @TypeConverter
    fun fromJsonFavourite(json: String): FavoriteModel? {
        return initMapper()
            .readValue(json, FavoriteModel::class.java)
    }

    @TypeConverter
    fun toJsonFavourite(data: FavoriteModel?): String? {
        return initMapper().writeValueAsString(data)
    }


    fun initMapper(): ObjectMapper {
        val mapper = jacksonObjectMapper()
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        return mapper
    }

}