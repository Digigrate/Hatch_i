package com.example.hatch_i.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.*
import com.example.hatch_i.R

class ForgetPassword : AppCompatActivity() {

    lateinit var btn_request : AppCompatButton
    lateinit var ed_email : AppCompatEditText
    lateinit var btn_cancel : AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpassword_activity)

        btn_request = findViewById(R.id.btn_request)
        ed_email = findViewById(R.id.ed_email)
        btn_cancel = findViewById(R.id.btn_cancel)

        btn_request.setOnClickListener(View.OnClickListener {
            if(ed_email.text.isNullOrEmpty()){
                toast("Please Enter Valid Username")
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
        })

        btn_cancel.setOnClickListener(View.OnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
        })

    }
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}