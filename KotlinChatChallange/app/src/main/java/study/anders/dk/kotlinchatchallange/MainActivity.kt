package study.anders.dk.kotlinchatchallange

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

data class Message(var value: String = "", var user: String = "")

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_register.setOnClickListener{
            register()
        }
        textview_already_have_account.setOnClickListener{
            val i = Intent(this,LoginActivity::class.java)


            startActivity(i)
        }

    }

    fun register(){
        val email = edittext_email.text.toString()
        val username = edittext_username.text.toString()
        val password = edittext_password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please provide email and password", Toast.LENGTH_LONG).show()
            return
        }

        //Firebase Auth
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener
                Log.d("MainActivity", "Created user with uid: ${it.result?.user?.uid}")
                Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show()

            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }
}