package com.example.mini7thumcapplication.Detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mini7thumcapplication.databinding.ItemProfileRecyclerviewBinding

class ProfileAdapter(private val Profiles: List<ProfileData>) :
    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(val binding: ItemProfileRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = Profiles[position]

        holder.binding.profileImage.setImageResource(profile.p_image)
        holder.binding.profileName.text = profile.name
        holder.binding.profileRole.text = profile.role
    }

    override fun getItemCount(): Int = Profiles.size
}
