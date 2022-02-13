package studio.daris.aplikasi_uas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import studio.daris.aplikasi_uas.databinding.ItemSlideBinding

class ImageSliderAdapter(private val items: List<ImageData>): RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(itemview: ItemSlideBinding) : RecyclerView.ViewHolder(itemview.root){
        private val binding = itemview
        fun bind(data: ImageData){
            with(binding){
                Glide.with(itemView)
                    .load(data.imageUrl)
                    .into(ivSlider)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemSlideBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}