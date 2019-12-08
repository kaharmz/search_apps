package com.example.githubuser

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.model.Items
import com.example.githubuser.presenter.UserPresenter
import com.example.githubuser.view.UserAdapter
import com.example.githubuser.view.UserView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), UserView.InitView {

    private var user: MutableList<Items> = mutableListOf()
    private lateinit var userPresenter: UserPresenter
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userPresenter = UserPresenter(this)
        userAdapter = UserAdapter(user, this)
    }

    override fun showLoading() {
        main_progressbar.visibility = View.VISIBLE
        main_recycler.visibility = View.GONE
    }

    override fun hideLoading() {
        main_progressbar.visibility = View.INVISIBLE
        main_recycler.visibility = View.VISIBLE
    }

    override fun userList(users: List<Items>?) {
        if (users != null) user.addAll(users)
        main_recycler.layoutManager = LinearLayoutManager(this)
        main_recycler.adapter = users?.let { UserAdapter(it, this) }
        userAdapter.notifyDataSetChanged()
    }

    override fun userListFailure(errorMessage: String, keyword: String) {
        Toast.makeText(applicationContext, "Oops! Keyword not found", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView?
        searchView?.queryHint = resources.getString(R.string.search_event_text)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                userPresenter.getUserList(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        return true
    }
}
