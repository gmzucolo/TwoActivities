package com.example.twoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var mMessageEditText: EditText? = null
    private var mReplyHeadTextView: TextView? = null
    private var mReplyTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "-----")
        Log.d(LOG_TAG, "onCreate")

        mMessageEditText = findViewById(R.id.editText_main)
        mReplyHeadTextView = findViewById(R.id.text_header_reply)
        mReplyTextView = findViewById(R.id.text_message_reply)

        if (savedInstanceState != null) {
            val isVisible = savedInstanceState.getBoolean("reply_visible")
            if (isVisible) {
                mReplyHeadTextView?.visibility = View.VISIBLE
                mReplyTextView?.text = savedInstanceState.getString("reply_text")
                mReplyTextView?.visibility = View.VISIBLE
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState!!)
        if (mReplyHeadTextView?.visibility == View.VISIBLE) {
            outState.putBoolean("reply_visible", true)
            outState.putString("reply_text", mReplyTextView?.text.toString())
        }
    }

    fun launchSecondActivity(view: View?) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        val message = mMessageEditText!!.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TEXT_REQUEST) {

            if (resultCode == RESULT_OK) {
                val reply = data!!.getStringExtra(EXTRA_REPLY)


                mReplyHeadTextView!!.visibility = View.VISIBLE

                mReplyTextView!!.text = reply
                mReplyTextView!!.visibility = View.VISIBLE
            }
        }
    }

    companion object {

        private val LOG_TAG = MainActivity::class.java.simpleName
        const val EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE"
        const val TEXT_REQUEST = 1
    }
}