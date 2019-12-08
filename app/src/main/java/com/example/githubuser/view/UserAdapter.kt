package com.example.githubuser.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.R
import com.example.githubuser.model.Items
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(
    private val user: List<Items>,
    private val context: Context?
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item, parent, false))

    override fun getItemCount(): Int = user.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindItem(user[position])
    }

    class UserViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(items: Items) {
            itemView.name.text = items.login
            items.avatar.let { Picasso.get().load(it).fit().into(itemView.avatar) }

        }
    }
}