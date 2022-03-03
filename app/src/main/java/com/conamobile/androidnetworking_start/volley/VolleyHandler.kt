package com.conamobile.androidnetworking_start.volley

interface VolleyHandler {
    fun onSuccess(response: String?)
    fun onError(error: String?)
}