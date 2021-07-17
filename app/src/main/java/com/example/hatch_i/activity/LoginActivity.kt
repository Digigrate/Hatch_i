package com.example.hatch_i.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.hatch_i.activity.MainActivity
import com.example.hatch_i.R
import com.example.hatch_i.apiclient.ApiInterface
import com.example.hatch_i.model.Login
import com.example.hatch_i.model.LoginResponse
import com.example.hatch_i.storageHelpers.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    lateinit var btn_signin: AppCompatButton
    lateinit var ed_username: AppCompatEditText
    lateinit var ed_password: AppCompatEditText
    lateinit var tv_forget: AppCompatTextView
    lateinit var tv_Request: AppCompatTextView
    lateinit var progressBar: ProgressBar
    var eye_click: Boolean = false
    lateinit var show_pass_btn: AppCompatImageView
    var token: String? = null
    var username: String? = null
    var useremail: String? = null
    var created_at: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        btn_signin = findViewById(R.id.btn_signin)
        show_pass_btn = findViewById(R.id.show_pass_btn)
        ed_username = findViewById(R.id.ed_username)
        ed_password = findViewById(R.id.ed_password)
        tv_forget = findViewById(R.id.tv_forget)
        // tv_Request = findViewById(R.id.tv_Request)

        progressBar = findViewById(R.id.progressBar)
        val first = "<font color='#626262'>Forgot Password ?</font>"
        val next = "<font color='#0071CA'> Request New Password</font>"
        tv_forget.setText(Html.fromHtml(first + next))

        btn_signin.setOnClickListener(View.OnClickListener {

            if (ed_username.text.isNullOrBlank() || ed_password.text.isNullOrBlank()) {
                toast("Please Enter Valid Username And Password")
            } else {
                signin(ed_username.text.toString(), ed_password.text.toString())
                progressBar.visibility = View.VISIBLE
            }

        })

//        tv_Request.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this, ForgetPassword::class.java)
//                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            startActivity(intent)
//        })

        tv_forget.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ForgetPassword::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        })

        show_pass_btn.setOnClickListener(View.OnClickListener {
            if (!ed_password.text.isNullOrBlank()) {
                if (!eye_click) {
                    eye_click = true
                    ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    eye_click = false
                    ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            } else {
                toast("Please Enter Password")
            }

        })

    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun signin(email: String, password: String) {
        val retIn =
            ApiInterface.RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val signInInfo = Login(email, password)
        retIn.login(signInInfo).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
                progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.code() == 200) {
                    progressBar.visibility = View.GONE
                    val responseBody: LoginResponse? = response.body()
                    if (responseBody != null) {
                        token = responseBody.token.toString()
                        username = responseBody.user!!.name.toString()
                        useremail = responseBody.user!!.email.toString()
                    }


                    PreferenceHelper.setStringPreference(
                        this@LoginActivity,
                        "token",
                        token
                    )
                    PreferenceHelper.setStringPreference(
                        this@LoginActivity,
                        "username",
                        username
                    )
                    PreferenceHelper.setStringPreference(
                        this@LoginActivity,
                        "useremail",
                        useremail
                    )
                    startact()
                    Toast.makeText(this@LoginActivity, "Login successfully!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun startact() {
        val intent = Intent(this, MainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

}