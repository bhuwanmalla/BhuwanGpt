package bhuwan.example.bhuwangpt.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bhuwan.example.bhuwangpt.Models.ChatClass
import bhuwan.example.bhuwangpt.databinding.GptItemBinding
import bhuwan.example.bhuwangpt.databinding.UserItemBinding

class Adapter(private val response: MutableList<ChatClass>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (response[position].isFromApi) {
            0
        } else {
            1
        }
    }

    inner class UserViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class GptViewHolder(val binding: GptItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val newView = response[position]
        if (holder is UserViewHolder) {
            holder.binding.userDesc.text = newView.data
        } else if (holder is GptViewHolder) {
            holder.binding.gptDesc.text = newView.data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val newView = GptItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GptViewHolder(newView)
        } else {
            val newView =
                UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UserViewHolder(newView)
        }
    }

    override fun getItemCount(): Int {
        return response.size
    }
}

//data class DataClass(
//    val data: String,
//    val isFromApi: Boolean
//)
