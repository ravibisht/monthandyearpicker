package com.stark.monthandyearpicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.util.*

class MonthYearPickerDialog(val date: Date = Date()) : DialogFragment() {

    companion object {
        private const val MAX_YEAR = 2099
    }

    private lateinit var pickerYear : NumberPicker
    private lateinit var pickerMonth : NumberPicker


    private var listener: DatePickerDialog.OnDateSetListener? = null

    fun setListener(listener: DatePickerDialog.OnDateSetListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cal: Calendar = Calendar.getInstance().apply { time = date }

      val inflater = activity?.layoutInflater
      val dialogView =  inflater?.inflate(R.layout.dialog_month_year_picker,null)

        if (dialogView != null) {
            pickerMonth=dialogView.findViewById(R.id.pickerMonth)
            pickerYear=dialogView.findViewById(R.id.pickerYear)
        }


        var alertDialog = AlertDialog.Builder(requireContext())
                .setTitle("Please Select View Month")
                .setView(dialogView)
                .setPositiveButton("Ok") { _, _ -> listener?.onDateSet(null, pickerYear.value, pickerMonth.value, 1) }
                .setNegativeButton("Cancel") { _, _ -> dialog?.cancel() }
                .create()


        pickerMonth.run {
            minValue = 0
            maxValue = 11
            value = cal.get(Calendar.MONTH)
            displayedValues = arrayOf("Jan","Feb","Mar","Apr","May","June","July",
                    "Aug","Sep","Oct","Nov","Dec")
        }

        pickerYear.run {
            val year = cal.get(Calendar.YEAR)
            minValue = year
            maxValue = MAX_YEAR
            value = year
        }

        return alertDialog
    }
}