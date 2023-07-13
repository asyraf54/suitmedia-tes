package com.example.suitmedia.presentation.screen3



import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmedia.data.response.User
import com.example.suitmedia.databinding.ItemUserBinding
import com.example.suitmedia.presentation.screen2.UserActivity


class ListAdapter(private val nameScreenOne: String?) : PagingDataAdapter<User, ListAdapter.MyViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    class MyViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if(user!=null){
            holder.binding.tvName.text = "${user.first_name} ${user.last_name}"
            holder.binding.tvEmail.text = user.email
            Glide.with(holder.itemView.context)
                .load(user.avatar)
                .into(holder.binding.profileImage)

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, UserActivity::class.java)
                intent.putExtra(UserActivity.NAME_SCREEN_ONE, nameScreenOne)
                intent.putExtra(UserActivity.NAME_SCREEN_TWO, "Selected ${user.first_name} ${user.last_name}")
                holder.itemView.context.startActivity(intent)
            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }
}