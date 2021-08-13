package br.com.derlandybelchior.goodplaces.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.derlandybelchior.goodplaces.R
import br.com.derlandybelchior.goodplaces.databinding.DetailScreenBinding

class DetailFragment : Fragment() {

    private lateinit var binding: DetailScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_screen, container, false)
    }
}