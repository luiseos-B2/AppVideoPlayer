import androidx.recyclerview.widget.DiffUtil
import com.freecast.thatmovieapp.home.domain.model.Media

class MediaComparator : DiffUtil.ItemCallback<Media>() {
    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}