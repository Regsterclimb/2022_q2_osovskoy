package com.example.a2022_q2_osovskoy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.AnimalViewHolderBinding
import com.example.a2022_q2_osovskoy.domain.entities.MyAnimal

class AnimalAdapter : ListAdapter<MyAnimal, AnimalViewHolder>(PersonListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder =
        AnimalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.animal_view_holder, parent, false))

    override fun onBindViewHolder(holderList: AnimalViewHolder, position: Int) =
        holderList.bind(getItem(position))
}

class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(AnimalViewHolderBinding::bind)

    fun bind(animal: MyAnimal) {
        with(viewBinding) {
            animalName.text = animal.name
            animalAge.text = animal.age
            animalPrice.text = animal.price.toString()
        }
    }
}

class PersonListCallBack : DiffUtil.ItemCallback<MyAnimal>() {

    override fun areItemsTheSame(oldItem: MyAnimal, newItem: MyAnimal): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: MyAnimal, newItem: MyAnimal): Boolean =
        oldItem == newItem

}