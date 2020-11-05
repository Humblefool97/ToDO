package com.example.cleanarchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.framework.NoteViewModel
import com.example.core.data.Note
import kotlinx.android.synthetic.main.fragment_note.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment() {
    private var noteId = 0L
    private lateinit var noteViewModel: NoteViewModel
    private var currentNote: Note = Note("", "", creationTime = 0L, updateTime = 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }
        if (noteId != 0L) {
            noteViewModel.getNote(noteId)
        }
        doneButton.setOnClickListener {
            if (editTextTitle.text.isNotBlank() || contentEditText.text.isNotBlank()) {
                val time = System.currentTimeMillis()
                with(currentNote) {
                    title = editTextTitle.text.toString()
                    content = contentEditText.text.toString()
                    updateTime = time
                    if (creationTime != 0L) {
                        creationTime = time
                    }
                    noteViewModel.saveNote(currentNote)
                }
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observerViewModel()
    }

    private fun observerViewModel() {
        noteViewModel.saveNotesLiveData.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "Notes Saved!!", Toast.LENGTH_LONG).show()
                Navigation.findNavController(editTextTitle).popBackStack()
            } else {
                Toast.makeText(context, "Notes not Saved!!", Toast.LENGTH_LONG).show()
            }
        })

        noteViewModel.currentNoteLiveData.observe(this, Observer { note ->
            note?.let {
                currentNote = it
                editTextTitle.setText(it.title, TextView.BufferType.EDITABLE)
                contentEditText.setText(it.content, TextView.BufferType.EDITABLE)
            }
        })
    }
}