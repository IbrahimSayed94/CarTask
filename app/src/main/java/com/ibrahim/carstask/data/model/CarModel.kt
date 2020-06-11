package com.ibrahim.carstask.data.model

data class CarModel(
    val ID: Int,
    val FirstRegistration: String,
    val AccidentFree: Boolean,
    val Images: List<String>,
    val PowerKW: Int,
    val Address: String,
    val Price: Double,
    val Mileage: Double,
    val Make: String,
    val FuelType: String
)