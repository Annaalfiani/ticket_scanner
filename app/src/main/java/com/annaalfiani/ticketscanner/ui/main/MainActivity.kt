package com.annaalfiani.ticketscanner.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.annaalfiani.ticketscanner.R
import com.annaalfiani.ticketscanner.ui.scanner.ScannerActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        mainViewModel.listenToState().observer(this, Observer { handleUiState(it) })
        scan()
    }

    private fun handleUiState(it: MainState) {
        when(it){
            is MainState.ShowAlert -> {
                popup(it.message)
                fab.isEnabled = true
            }
        }
    }

    private fun popup(message: String){
        AlertDialog.Builder(this).apply {
            setMessage(message)
            setPositiveButton("oke"){dialog, _ ->
                dialog.dismiss()
            }
        }.show()
    }

    private fun scan(){
        fab.setOnClickListener { _ ->
            startActivityForResult(Intent(this@MainActivity, ScannerActivity::class.java), 100)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == Activity.RESULT_OK && data != null){
            val scanResut = data.getStringExtra("CODE")
            fab.isEnabled = false
            scanResut?.let { mainViewModel.checkTicket(it) }
            //scanResut?.let { ashiap_textView.text = it }
        }
    }

    private fun toast(message : String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
