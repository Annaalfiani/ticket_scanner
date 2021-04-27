package com.annaalfiani.ticketscanner.repositories

import com.annaalfiani.ticketscanner.models.OrderDetails
import com.annaalfiani.ticketscanner.utils.SingleResponse
import com.annaalfiani.ticketscanner.webservices.ApiService
import com.annaalfiani.ticketscanner.webservices.WrappedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface OrderDetailContract{
    fun checkTicket(id : String, listener : SingleResponse<OrderDetails>)
}

class OrderDetailRepository (private val api : ApiService) : OrderDetailContract {
    override fun checkTicket(id: String, listener: SingleResponse<OrderDetails>) {
        api.checkTicket(id.toInt()).enqueue(object : Callback<WrappedResponse<OrderDetails>>{
            override fun onFailure(call: Call<WrappedResponse<OrderDetails>>, t: Throwable) {
                listener.onFailure(Error(t.message))
            }

            override fun onResponse(call: Call<WrappedResponse<OrderDetails>>, response: Response<WrappedResponse<OrderDetails>>) {
                when {
                    response.isSuccessful -> {
                        val body = response.body()
                        if (body?.status!!){
                            listener.onSuccess(body.data)
                        }else{
                            listener.onFailure(Error(body.message))
                        }
                    }
                    !response.isSuccessful -> listener.onFailure(Error(response.message()))
                }
            }

        })
    }

}