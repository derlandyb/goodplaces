package br.com.derlandybelchior.goodplaces.presentation.imagetool

import android.widget.ImageView
import br.com.derlandybelchior.goodplaces.R
import com.squareup.picasso.Picasso

object LoadImage {

    fun loadImageByUrl(imageUrl: String?, imageView: ImageView, onError: () -> Unit = {}) {

        val errorPlaceholder = arrayOf(
            R.drawable.image_placeholder_duck_egg_blue,
            R.drawable.image_placeholder_creme,
            R.drawable.image_placeholder_light_pink
        )

        errorPlaceholder.shuffle()

        if(imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.image_placeholder_duck_egg_blue)
                .error(errorPlaceholder.first())
                .into(imageView)
        } else {
            onError()
            imageView.setImageResource(errorPlaceholder.first())
        }
    }
}