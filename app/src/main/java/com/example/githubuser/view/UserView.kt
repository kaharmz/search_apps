package com.example.githubuser.view

import com.example.githubuser.model.Items


interface UserView {

    interface InitView {

        fun showLoading()
        fun hideLoading()
        fun userList(users: List<Items>?)
        fun userListFailure(errorMessage: String, keyword: String)

    }

    interface GetUsers {
        fun getUserList(keyword: String)
    }
}