package com.example.a2022_q2_osovskoy.presentation.phone_book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.PersonViewHolderBinding
import com.example.a2022_q2_osovskoy.domain.model.Person

class PersonAdapter(
    private val update: (person: Person) -> Unit,
    private val delete: (person: Person) -> Unit,
) : ListAdapter<Person, PersonViewHolder>(PersonListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.person_view_holder, parent, false))

    override fun onBindViewHolder(holderList: PersonViewHolder, position: Int) {
        holderList.bind(getItem(position), update, delete)
    }
}

class PersonViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(PersonViewHolderBinding::bind)

    fun bind(person: Person, update: (person: Person) -> Unit, delete: (person: Person) -> Unit) {
        with(viewBinding) {
            personName.text = person.name
            personPhone.text = person.number
            menuButton.setOnClickListener {
                val popupmenu = PopupMenu(itemView.context, menuButton)
                popupmenu.menuInflater.inflate(R.menu.phone_menu, popupmenu.menu)
                popupmenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menuActionUpdate -> {
                            update(person)
                        }
                        R.id.menuActionDelete -> {
                            delete(person)
                        }
                        R.id.menuActionHello -> {
                            Toast.makeText(itemView.context,
                                "HELLO ${person.name}",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
                }
                popupmenu.show() // Leak here, what to do?
            }
        }
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
