package com.annaalfiani.ticketscanner.webservices

import com.annaalfiani.ticketscanner.models.OrderDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/check/ticket/{id}")
    fun checkTicket(@Path("id") id : Int) : Call<WrappedResponse<OrderDetails>>
}