package br.com.derlandybelchior.goodplaces.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.derlandybelchior.goodplaces.databinding.ItemPhotoGalleryBinding
import br.com.derlandybelchior.goodplaces.presentation.imagetool.LoadImage

class PhotosAdapter(private val photos: List<String>) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun getItemCount(): Int = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = ItemPhotoGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class PhotosViewHolder(private val binding: ItemPhotoGalleryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(position: Int) {
            LoadImage.loadImageByUrl(photos[position], binding.galleryItem)
        }
    }
}