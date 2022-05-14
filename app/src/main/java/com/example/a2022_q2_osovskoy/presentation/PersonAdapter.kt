package com.example.a2022_q2_osovskoy.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.PersonViewHolderBinding
import com.example.a2022_q2_osovskoy.domain.entity.Person

class PersonAdapter(private val onMenuClick: () -> Unit) :
    ListAdapter<Person, PersonViewHolder>(PersonListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.person_view_holder, parent, false))

    override fun onBindViewHolder(holderList: PersonViewHolder, position: Int) =
        holderList.bind(getItem(position), onMenuClick)
}

class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(PersonViewHolderBinding::bind)

    fun bind(person: Person, onMenuClick: () -> Unit) = with(viewBinding) {
        personName.text = person.name
        personPhone.text = person.number
        menuButton.setOnClickListener {
            onMenuClick()
        }
    }
}

class PersonListCallBack : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem

}
