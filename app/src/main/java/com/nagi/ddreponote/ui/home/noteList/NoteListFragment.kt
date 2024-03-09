package com.nagi.ddreponote.ui.home.noteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nagi.ddreponote.adapter.NoteListAdapter
import com.nagi.ddreponote.adapter.ViewPagerAdapter
import com.nagi.ddreponote.data.NoteList.Note
import com.nagi.ddreponote.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {
    private val viewModel: NoteListViewModel by viewModels()
    private var _binding: FragmentNoteListBinding? = null
    private lateinit var noteListAdapter: NoteListAdapter
    private val binding get() = _binding!!

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentNoteListBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        noteListAdapter = NoteListAdapter(mutableListOf())
        binding.noteList.adapter = noteListAdapter
    }

    private fun initViewModel() {
        viewModel.noteData.observe(viewLifecycleOwner) { noteListAdapter.updateData(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

