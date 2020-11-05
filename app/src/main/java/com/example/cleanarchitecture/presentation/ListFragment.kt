package com.example.cleanarchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.framework.NotesListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment(),ListAction {
    val notesListAdapter = NotesListAdapter(arrayListOf(),this)
    private lateinit var notesListViewModel: NotesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesListView.apply {
            adapter = notesListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        notesListViewModel = ViewModelProviders.of(this).get(NotesListViewModel::class.java)
        observeViewModel()
        addNoteButton.setOnClickListener {
            gotToNoteDetails()
        }
    }

    override fun onResume() {
        super.onResume()
        notesListViewModel.getAllNotes()
    }

    private fun observeViewModel() {
        notesListViewModel.notesListLiveData.observe(this, Observer {
            if (it.isNotEmpty()) {
                loadingView.isVisible = false
                notesListView.isVisible = true
                notesListAdapter.updateNotes(it.sortedByDescending { it.updateTime})
            }
        })
    }

    private fun gotToNoteDetails(id: Long = 0L) {
        val action = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(notesListView).navigate(action)
    }

    override fun onClick(id: Long) {
       gotToNoteDetails(id)
    }
}