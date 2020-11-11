package com.shapeecloudjsc.firebasecloudmessaging

data class TourList(
    val id: Int,
    val tourName: String,
    val oldPrice: Double,
    val price: Double,
    val vat: Double,
    val startTime: String,
    val time: String,
    val fromCity: String,
    val toCity: String,
    val seatQuantity: Int,
    val count: Int,
    val seatSelected: String
    )