package study.anders.dk.kotlinchatchallange

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

data class Message(var value: String = "", var user: String = "", var id: Int = 0)

class MainActivity : AppCompatActivity() {

    val MESSAGES_REF = "instantMessage"

    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var firebaseDBref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize arrayAdapter
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<String>())
        listView.adapter = arrayAdapter

        //Initialize database reference
        firebaseDBref = FirebaseDatabase.getInstance().reference

        val messageRef = firebaseDBref.child(MESSAGES_REF)

        messageRef.addChildEventListener(object: ChildEventListener{

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val message = p0.getValue(Message::class.java)
                if(message != null) {
                    arrayAdapter.add(message.value)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun sendChatMessage(view: View) {
        val text = editText.text.toString()

        if (!text.isEmpty()) {
            val message = Message(text, "Anonymous")
            firebaseDBref.child(MESSAGES_REF).push().setValue(message)
            editText.setText("")
        }
    }
}
