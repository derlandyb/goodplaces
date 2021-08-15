package br.com.derlandybelchior.goodplaces.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.derlandybelchior.goodplaces.databinding.GridItemBinding
import br.com.derlandybelchior.goodplaces.presentation.imagetool.LoadImage
import br.com.derlandybelchior.goodplaces.presentation.model.PlacePresentationModel

class PlacesRecyclerViewAdapter(
    private val data: List<PlacePresentationModel>,
    private val onClick: (Int, String?) -> Unit
) : RecyclerView.Adapter<PlacesRecyclerViewAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) = with(binding){
            val placePresentationModel = data[position]
            title.text = placePresentationModel.name
            type.text = placePresentationModel.type
            rating.rating = placePresentationModel.review.toFloat()
            LoadImage.loadImageByUrl(placePresentationModel.imageUrl, placeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(position)
        holder.itemView.setOnClickListener { onClick(data[position].id, data[position].imageUrl) }
    }

    override fun getItemCount(): Int = data.size
}