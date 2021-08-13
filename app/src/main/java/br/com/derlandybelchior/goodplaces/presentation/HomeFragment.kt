package br.com.derlandybelchior.goodplaces.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.derlandybelchior.goodplaces.R
import br.com.derlandybelchior.goodplaces.databinding.GridItemBinding
import br.com.derlandybelchior.goodplaces.databinding.HomeScreenBinding

class HomeFragment : Fragment() {

    private var _binding: HomeScreenBinding? = null
    private val binding get() = _binding

    private val images = arrayOf(
        R.drawable.istockphoto,
        R.drawable.istockphoto2,
        R.drawable.istockphoto3,
        R.drawable.istockphoto4,
        R.drawable.istockphoto,
        R.drawable.istockphoto2,
        R.drawable.istockphoto3,
        R.drawable.istockphoto4,
        R.drawable.istockphoto,
        R.drawable.istockphoto2,
        R.drawable.istockphoto3,
        R.drawable.istockphoto4,
        R.drawable.istockphoto,
        R.drawable.istockphoto2,
        R.drawable.istockphoto3,
        R.drawable.istockphoto4,
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeScreenBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding) {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        this?.contentList?.layoutManager = staggeredGridLayoutManager
        this?.contentList?.adapter = GridViewAdapter(images) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment2()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class GridViewAdapter(private val images: Array<Int>, private val onClick: () -> Unit) : RecyclerView.Adapter<GridViewAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) = with(binding){
            placeImage.setImageResource(images[position])
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

    override fun getItemCount(): Int = images.size
}