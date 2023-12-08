import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.retrofit.Preference


class PreferenceViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private var pIdView: Int = 0
    private val pNameView: TextView = view.findViewById(R.id.pref_item_tv)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun bind(preference: Preference, isSelected: Boolean) {
        pIdView = preference.id
        pNameView.text = preference.name
        itemView.isSelected = isSelected

        val color = if (isSelected) Color.BLUE else Color.GRAY
        pNameView.backgroundTintList = ColorStateList.valueOf(color)
    }
}

class PreferenceAdapter(
        private var preferenceList: List<Preference>
) : RecyclerView.Adapter<PreferenceViewHolder>() {

    private val selectedItems = HashSet<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferenceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_pref_item, parent, false)
        return PreferenceViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: PreferenceViewHolder, position: Int) {
        val preference = preferenceList[position]
        val isSelected = selectedItems.contains(preference.id)
        holder.bind(preference, isSelected)

        holder.itemView.setOnClickListener {
            if (isSelected) {
                selectedItems.remove(preference.id)
            } else {
                selectedItems.add(preference.id)
            }
            notifyItemChanged(position)
        }

    }

    override fun getItemCount(): Int {
        return preferenceList.size
    }

    fun updateData(preferenceList: List<Preference>) {
        this.preferenceList = preferenceList
        notifyDataSetChanged()
    }

    fun getSelectedItems(): Set<Int> {
        return selectedItems
    }

}

