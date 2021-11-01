package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.io.File

private const val ARG_PHOTO_FILE = "photoFile"

class CrimePhotoDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val photoFile = arguments?.getSerializable(ARG_PHOTO_FILE) as File
        val bitmap = getScaledBitmap(photoFile.path, requireActivity())

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.fragment_crime_photo_dialog, null)
            val photoView = dialogView.findViewById(R.id.crime_dialog_photo) as ImageView
            photoView.setImageBitmap(bitmap)
            builder.setView(dialogView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        fun newInstance(photoFile: File): CrimePhotoDialogFragment {
            val args = Bundle().apply {
                putSerializable(ARG_PHOTO_FILE, photoFile)
            }
            return CrimePhotoDialogFragment().apply {
                arguments = args
            }
        }
    }
}