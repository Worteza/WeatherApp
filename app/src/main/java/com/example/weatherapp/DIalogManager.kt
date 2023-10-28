package com.example.weatherapp

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

object DIalogManager {
    fun locationSettingsDialog(context: Context, listener: Listener){
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        dialog.setTitle("Enable location")
        dialog.setMessage("Location is disabled." +
                "\n" +
                "Do you want to enable location?")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes"){_,_ ->
            dialog.dismiss()
            listener.onClick(null)
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No"){_,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }
    fun searchByNameDialog(context: Context, listener: Listener){
        val builder = AlertDialog.Builder(context)
        val edName = EditText(context)
        builder.setView(edName)
        val dialog = builder.create()
        dialog.setTitle("Write down desired location:")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok"){_,_ ->
            dialog.dismiss()
            listener.onClick(edName.text.toString())
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel"){_,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }
    interface  Listener{
        fun onClick(name: String?)
    }
}
