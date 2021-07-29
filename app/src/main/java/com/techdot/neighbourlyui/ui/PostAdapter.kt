package com.techdot.neighbourlyui.ui

import android.content.Context
import android.content.Intent
import android.graphics.Path
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techdot.neighbourlyui.MainActivity
import com.techdot.neighbourlyui.R
import com.techdot.neighbourlyui.databinding.ImagePostLayoutBinding
import com.techdot.neighbourlyui.databinding.TextPostLayoutBinding
import com.techdot.neighbourlyui.model.Post
import com.techdot.neighbourlyui.ui.post_details.DetailsFragment
import com.techdot.neighbourlyui.ui.post_details.DetailsFragmentArgs

class PostAdapter(context: Context, posts: List<Post>, manager: FragmentManager): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val postList = posts
    val context = context
    val manager = manager

    companion object {
        const val TAG = "POST_ADAPTER"
        const val VIEW_TYPE_TEXT_POST = 52
        const val VIEW_TYPE_IMAGE_POST = 53
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_TEXT_POST) {
            val view = inflater.inflate(R.layout.text_post_layout, parent, false)
            val binding = TextPostLayoutBinding.bind(view)
            return TextViewHolder(binding)
        } else {
            val view = inflater.inflate(R.layout.image_post_layout, parent, false)
            val binding = ImagePostLayoutBinding.bind(view)
            return ImageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = postList[position]
        if (post.postImage != null) {
            (holder as ImageViewHolder).bind(post)
        } else {
            (holder as TextViewHolder).bind(post)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun getItemViewType(position: Int): Int {
        val post = postList[position]

        if (post.postImage != null) {
            return VIEW_TYPE_IMAGE_POST
        } else {
            return VIEW_TYPE_TEXT_POST
        }
    }



    inner class TextViewHolder(
        private val binding: TextPostLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.userName.text = post.userName
            binding.userImage.setImageResource(post.userImage)
            binding.userLocation.text = post.userLocation
            binding.userQuestion.text = post.question

            binding.numberOfAnswers.text = "${post.numberOfAnswers} Answers"

            binding.commenterName.text = post.commenterName
            binding.commenterImage.setImageResource(post.commenterImage)
            binding.commenterReply.text = post.answer

            binding.userQuestion.setOnClickListener {
                DetailsFragment.newInstance(post).show(manager, "DETAILS_FRAGMENT")
            }
        }
    }

    inner class ImageViewHolder(
        private val binding: ImagePostLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.imagePost.setImageResource(post.postImage!!)
            binding.userImage.setImageResource(post.userImage)
            binding.userName.text = post.userName
            binding.userLocation.text = post.userLocation
            binding.userCaption.text = post.question
        }
    }
}