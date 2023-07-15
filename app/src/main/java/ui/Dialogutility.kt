package ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import ir.dunijet.myapplication.R

class Dialogutility {

    fun showdialog(activity: Activity) {
        val dialog = AlertDialog.Builder(activity)
        var inflator = LayoutInflater.from(activity)
        val view:View = inflator.inflate(R.layout.login, null)
        dialog.setView(view)
        val btnlogin = view.findViewById<AppCompatButton>(R.id.btn_login)
        val btnpassword = view.findViewById<AppCompatEditText>(R.id.edt_password)
        val btnusername = view.findViewById<AppCompatEditText>(R.id.edt_username)
        dialog.show()


        btnlogin.setOnClickListener({
            var username: String = btnusername.text.toString()
            var password: String = btnpassword.text.toString()
            if (username == "hamidnajafi" && password == "1368.9.22") {
                Toast.makeText(activity,"welcome Dear hamid",Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(activity,"error!check your username and password!! ",Toast.LENGTH_LONG).show()
            }


        })
    }
}