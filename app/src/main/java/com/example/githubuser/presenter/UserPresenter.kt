package com.example.githubuser.presenter

import com.example.githubuser.api.ApiClient
import com.example.githubuser.api.ApiInterface
import com.example.githubuser.model.Users
import com.example.githubuser.view.UserView
import retrofit2.Call
import retrofit2.Callback


class UserPresenter(private val initView: UserView.InitView) : UserView.GetUsers {

    override fun getUserList(keyword: String) {
        initView.showLoading()
        val apiInterface = ApiClient.getApiClient()?.create(ApiInterface::class.java)
        val call = apiInterface?.getUsers(keyword)
        call?.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: retrofit2.Response<Users>) {
                initView.hideLoading()
                initView.userList(response.body()?.list)
                val totalCount = response.body()?.total

                if (!response.isSuccessful || response.body()?.list == null || totalCount == 0) {
                    initView.userListFailure(
                        "No result '$keyword'",
                        "Try searching for another users"
                    )
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                initView.userListFailure("Error loading '$keyword'", t.toString())
                initView.hideLoading()
                t.printStackTrace()
            }
        })
    }
}