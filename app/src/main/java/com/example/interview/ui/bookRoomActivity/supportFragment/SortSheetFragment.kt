package com.example.interview.ui.bookRoomActivity.supportFragment


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.interview.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_sort_sheet.*
import kotlinx.android.synthetic.main.fragment_sort_sheet.view.*
import javax.inject.Inject


// bottom sheet fragment
class SortSheetFragment @Inject constructor(): BottomSheetDialogFragment() {
    private var listener: ButtonClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sort_sheet, container, false)

        view.reset.setOnClickListener {
            listener?.onReset()
            dismiss()
        }
        view.apply.setOnClickListener {
            val sortType = getSortType()
            listener?.onChooseSortType(sortType)
            dismiss()
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ButtonClickListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun getSortType(): SortType {
        return when (sortType.checkedRadioButtonId) {
            R.id.locationType -> SortType.LOCATION
            R.id.capacityType -> SortType.CAPACITY
            else -> SortType.AVAILABILITY
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        // setup expanded state after open sheet
        dialog.setOnShowListener { sheetDialog ->
            val d = sheetDialog as BottomSheetDialog
            val bottomSheet =
                d.findViewById(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    // interface communicate outter class
    interface ButtonClickListener {
        fun onChooseSortType(type: SortType)
        fun onReset()
    }

}
