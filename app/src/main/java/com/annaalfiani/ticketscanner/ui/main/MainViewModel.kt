package com.annaalfiani.ticketscanner.ui.main

import androidx.lifecycle.ViewModel
import com.annaalfiani.ticketscanner.models.OrderDetails
import com.annaalfiani.ticketscanner.repositories.OrderDetailRepository
import com.annaalfiani.ticketscanner.utils.SingleLiveEvent
import com.annaalfiani.ticketscanner.utils.SingleResponse

class MainViewModel (private val orderDetailRepository: OrderDetailRepository) : ViewModel(){
    private val state : SingleLiveEvent<MainState> = SingleLiveEvent()

    private fun setLoading(){ state.value = MainState.Loading(true) }
    private fun hideLoading(){ state.value = MainState.Loading(false) }
    private fun alert(message: String){ state.value = MainState.ShowAlert(message) }

    fun checkTicket(id : String){
        setLoading()
        orderDetailRepository.checkTicket(id, object : SingleResponse<OrderDetails>{
            override fun onSuccess(data: OrderDetails?) {
                hideLoading()
                alert("Scan Tiket Berhasil")
            }

            override fun onFailure(err: Error) {
                hideLoading()
                alert(err?.message.toString())
            }

        })
    }

    fun listenToState() = state
}

sealed class MainState{
    data class Loading(var state : Boolean = false) : MainState()
    data class ShowAlert(var message : String) : MainState()
}