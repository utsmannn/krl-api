package com.berangkat.kerja

import com.berangkat.kerja.model.*
import com.google.gson.Gson
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SpringBootApplication
class KerjaApplication

fun main(args: Array<String>) {
	runApplication<KerjaApplication>(*args)
}

@RestController
@RequestMapping("/api/v1")
class RestController {

	private var size = "0"
	private lateinit var originData: List<OriginData>
	private lateinit var stationNameData: List<StationName>
    private val restTemplate = RestTemplate()

	init {
		val url = "http://info.krl.co.id/FkVYAhREAUJcWxgHEg9RRlJHTgJNAQFHAAtaUApRUgYGTFcVQB1KGl8VFhhJDB1AVVUcAlFGXAJQEVMNHQwCDwYBCVcHCDo0MDMyOTI3OQ=="
		val originModel = restTemplate.getForObject(url, String::class.java)
		val model = Gson().fromJson(originModel, OriginModel::class.java)
		size = model.total.toString()

		val urlStation = "http://info.krl.co.id/BVYFGU1OWV1LWwJVA1ZBQgZMaAlMUFcRQRsEDQFGXhwHEl4fQF1VF1dWFxBHWRdeX1lNXAAEAFYNUAJROmMwY2M5YjYy"
		val stationResponses = restTemplate.getForObject(urlStation, String::class.java)
		val stationModel = Gson().fromJson(stationResponses, StationResponses::class.java)

		stationModel.data.forEachIndexed { index, stationName ->
			val raw = goodNameList[index].split(" ").map { it.capitalize() }
			val rawString = raw.toString()
					.replace(",","")
					.replace("[","")
					.replace("]","")
			stationName.good_name = rawString

			stationName.geometry = getInternalLocation(rawString.toLowerCase())
			stationNameData = stationModel.data
		}

		model.data.forEach { data ->
			data.good_name = stationNameData.find { it.name == data.sts }?.good_name.toString()
			originData = model.data
		}
	}

	private fun getInternalLocation(name: String): List<Double?>? {
		val token = "01ffb86dbff242588a9084c8e4ff8423"
		val retrofit = RetrofitInstance.create()
		val responses = retrofit.getLocation("stasiun $name", token).execute()

		return try {
			arrayListOf(responses.body()?.results?.get(0)?.geometry?.lat, responses.body()?.results?.get(0)?.geometry?.lng)
		} catch (e: IndexOutOfBoundsException) {
			emptyList()
		}
	}

	@RequestMapping(value = ["/location/{station_name}"], method = [RequestMethod.GET])
    fun getLocation(@PathVariable("station_name") name: String): List<Double>? {

		val url = "https://api.opencagedata.com/geocode/v1/json?q=stasiun%20${name.toLowerCase()}&key=01ffb86dbff242588a9084c8e4ff8423"
		val dataResponses = restTemplate.getForObject(url, String::class.java)
		val dataModel = Gson().fromJson(dataResponses, OpenCageData::class.java)

		return try {
			arrayListOf(dataModel.results[0].geometry.lat, dataModel.results[0].geometry.lng)
		} catch (e: IndexOutOfBoundsException) {
			emptyList()
		}
    }

	@RequestMapping(value = ["/"], method = [RequestMethod.GET])
	fun getSize(): String {
		return originData.size.toString()
	}

	@RequestMapping(value = ["/station"], method = [RequestMethod.GET])
	fun getStationList(): List<StationName> {
		return stationNameData
	}

	@RequestMapping(value = [""], method = [RequestMethod.GET])
	fun searchStation(@RequestParam("search") stationName: String): Responses {
		val data = stationNameData.filter { it.good_name.toLowerCase().contains(stationName) }
		return Responses(data.size, data)
	}

	@RequestMapping(value = ["/station_name"], method = [RequestMethod.GET])
	fun getStationStringList(): List<String> {
		return stationNameData.map { it.good_name }
	}

	@RequestMapping(value = ["/{station_name}"], method = [RequestMethod.GET])
	fun getStation(@PathVariable("station_name") stationName: String): Responses {
		val data = originData.filter { it.sts.toLowerCase() == stationName.toLowerCase() }
		return Responses(data.size, data)
	}
}