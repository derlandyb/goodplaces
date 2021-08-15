package br.com.derlandybelchior.goodplaces.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.derlandybelchior.goodplaces.databinding.CommentListItemBinding
import br.com.derlandybelchior.goodplaces.presentation.imagetool.LoadImage
import br.com.derlandybelchior.goodplaces.presentation.model.CommentPresentationModel

class CommentsAdapter(private val comments: List<CommentPresentationModel>) : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    override fun getItemCount(): Int = comments.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val binding = CommentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class CommentsViewHolder(private val binding: CommentListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(position: Int) {
            val item = comments[position]
            binding.profileImagePlaceholder.visibility = View.GONE
            binding.commentAuthor.text = item.author
            binding.commentTitle.text = item.title
            binding.commentContent.text = item.comment
            binding.commentRating.rating = item.review

            LoadImage.loadImageByUrl(item.photoUrl, binding.profileImage) {
                binding.profileImagePlaceholder.text = item.author.substring(0, 1).uppercase()
                binding.profileImagePlaceholder.visibility = View.VISIBLE
            }
        }
    }
}