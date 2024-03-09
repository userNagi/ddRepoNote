package com.nagi.ddreponote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nagi.ddreponote.adapter.NoteListAdapter.AdapterHolder
import com.nagi.ddreponote.data.NoteList.Note
import com.nagi.ddreponote.databinding.ListNoteListBinding

class NoteListAdapter(private val data: MutableList<Note>) : RecyclerView.Adapter<AdapterHolder>() {
    fun updateData(newData: List<Note>) {
        val diffCallback = NoteListDiffCallback(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        return AdapterHolder(
            ListNoteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(h: AdapterHolder, p: Int) = h.bind(data[p])


    override fun getItemCount() = data.size
    class AdapterHolder(private val binding: ListNoteListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.noteTitle.text = note.title
            binding.noteContent.text = note.contentSimple
            binding.noteTime.text = note.createTime.toString()
        }
    }
    class NoteListDiffCallback(
        private val oldList: List<Note>,
        private val newList: List<Note>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}