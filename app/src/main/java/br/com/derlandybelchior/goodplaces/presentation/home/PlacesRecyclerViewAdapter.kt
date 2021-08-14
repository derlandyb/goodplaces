package br.com.derlandybelchior.goodplaces.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.derlandybelchior.goodplaces.R
import br.com.derlandybelchior.goodplaces.databinding.GridItemBinding
import br.com.derlandybelchior.goodplaces.presentation.model.PlacePresentationModel
import com.squareup.picasso.Picasso

class PlacesRecyclerViewAdapter(
    private val data: List<PlacePresentationModel>,
    private val onClick: () -> Unit
) : RecyclerView.Adapter<PlacesRecyclerViewAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) = with(binding){
            val placePresentationModel = data[position]
            title.text = placePresentationModel.name
            type.text = placePresentationModel.type
            rating.rating = placePresentationModel.review.toFloat()

            loadImage(imageUrl = placePresentationModel.imageUrl)
        }

        private fun loadImage(imageUrl: String?) = with(binding) {
            imageUrl?.let {
                Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.place_image_placeholder)
                    .error(R.drawable.place_image_placeholder)
                    .into(placeImage)
            } ?: placeImage.setImageResource(R.drawable.place_image_placeholder)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(position)
        holder.itemView.setOnClickListener { onClick() }
    }

    override fun getItemCount(): Int = data.size
}