package com.sstudio.thebadminton.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sstudio.thebadminton.Common
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.menu3.DetailVideoActivity
import com.sstudio.thebadminton.model.Comment
import kotlinx.android.synthetic.main.item_comment.view.*
import java.util.*

class CommentAdapter(private val context: Context) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    var comments: List<Comment> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_comment, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.txtName.text = comment.name
        holder.txtComment.text = comment.comment

        holder.cvComment.setOnLongClickListener {
            if (Common.isCoach) {
                (context as DetailVideoActivity).commentOnLongClick(comment.id)
            }
            true
        }
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.tv_name_comment
        val txtComment = itemView.tv_comment
        val cvComment = itemView.cv_item_comment
    }
}