package com.example.a2022_q2_osovskoy.presentation.content_provider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.domain.content_provider.model.Person

class PersonAdapter : ListAdapter<Person, PersonViewHolder>(PersonListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.person_view_holder, parent, false)
        )

    override fun onBindViewHolder(holderList: PersonViewHolder, position: Int) {
        holderList.bind(getItem(position))
    }
}

class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(person: Person) {
        itemView.findViewById<TextView>(R.id.personName).text = person.name
        itemView.findViewById<TextView>(R.id.personPhone).text = person.number
    }
}

class PersonListCallBack : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}