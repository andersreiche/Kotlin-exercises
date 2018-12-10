package study.anders.dk.kotlinchatchallange

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ArrayAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : AppCompatActivity() {

    val MESSAGES_REF = "instantMessage"

    var listKeys = ArrayList<String>();
    var persistenceEnabled = false

    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var firebaseDBref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)



        //Initialize arrayAdapter
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<String>())
        listView.adapter = arrayAdapter

        //Initialize database reference
        if (!persistenceEnabled) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)
            persistenceEnabled = true;
        }
        firebaseDBref = FirebaseDatabase.getInstance().reference

        val messageRef = firebaseDBref.child(MESSAGES_REF)

        messageRef.addChildEventListener(object: ChildEventListener {

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val message = p0.getValue(Message::class.java)

                if(message != null) {
                    arrayAdapter.add(message.value)
                    listKeys.add(p0.key.toString())
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
                val message = p0.getValue(Message::class.java)
                arrayAdapter.remove(message?.value)
            }

        })
        listView.setOnItemLongClickListener { parent, view, position, id ->
            val alert = AlertDialog.Builder(this)
            alert.setTitle(getString(R.string.alertDeleteConfirmationTitle))
            alert.setMessage(getString(R.string.alertDeleteConfirmationText))
            alert.setPositiveButton(getString(R.string.alertSetPositiveButtonText)) { dialog, which ->
                //Delete list entry
                firebaseDBref.child(MESSAGES_REF).child(listKeys[position]).removeValue()
                listKeys.removeAt(position)
            }
            alert.setNegativeButton(getString(R.string.alertSetNegativeButtonText)){ dialog, which ->
                //Do nothing
            }
            val dialog: AlertDialog = alert.create()
            dialog.show()
            true
        }
    }

    fun sendChatMessage(view: View) {
        val text = editText.text.toString()
        val extras = intent.extras ?: return
        val email = extras.get("email").toString()

        if (!text.isEmpty()) {
            val message = Message(email + ": " + text, email)
            firebaseDBref.child(MESSAGES_REF).push().setValue(message)
            editText.setText("")
        }
    }
}
