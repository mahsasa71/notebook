package ir.dunijet.myapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import ir.dunijet.myapplication.adapter.Noteadapter
import ir.dunijet.myapplication.database.Notedatabase
import ir.dunijet.myapplication.database.Notedbadapter
import ir.dunijet.myapplication.model.Note
import ui.AddNoteActivity
import ui.Dialogutility

class MainActivity : AppCompatActivity() {
    lateinit var notedbadapter: Notedbadapter
    lateinit var recyclerNotes : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var database =Notedatabase(applicationContext)
        notedbadapter=Notedbadapter(applicationContext)
        val btnfab=findViewById<FloatingActionButton>(R.id.btn_fab)
        val toobar=findViewById<Toolbar>(R.id.toolbar)
        val navmenu=findViewById<NavigationView>(R.id.nav_menu)
        val drawer=findViewById<DrawerLayout>(R.id.drawer)
        recyclerNotes=findViewById<RecyclerView>(R.id.recycler_notes)
        setSupportActionBar(toobar)



        btnfab.setOnClickListener(object :View.OnClickListener{

            override fun onClick(v: View?) {
                val intent=Intent(applicationContext,AddNoteActivity::class.java)
            startActivity(intent)

            }
        })
        navmenu.setNavigationItemSelectedListener {
            Log.e("","")
            when(it.itemId){
                R.id.item_addnote->{
                    Toast.makeText(applicationContext,"add your note",Toast.LENGTH_LONG).show()
                    false
                }
                R.id.item_settings->{
                    val dialog=Dialogutility()
                    dialog.showdialog(this@MainActivity)
                    false
                }
                else->true
            }
        }
       val actionbartoggle=ActionBarDrawerToggle(this@MainActivity,drawer,toobar,R.string.open,R.string.close)
        actionbartoggle.syncState()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
        R.id.item_about->{
            val intent=Intent(applicationContext,aboutus::class.java)
            startActivity(intent)

        }
         R.id.item_contact->{
             val intentcall=Intent(Intent.ACTION_VIEW, Uri.parse("tel:09371794050"))
             startActivity(intentcall)
         }
         R.id.item_exit->{
           //  finishAffinity()
             val dialog=AlertDialog.Builder(this@MainActivity)
             dialog.setTitle("Exit!")
             dialog.setMessage("Are you sure?")
             dialog.setIcon(android.R.drawable.ic_delete)
             dialog.setPositiveButton("No",object :DialogInterface.OnClickListener{
                 override fun onClick(dialog: DialogInterface?, which: Int) {
                 }

             })
             dialog.setNeutralButton("yes",object :DialogInterface.OnClickListener{
                 override fun onClick(dialog: DialogInterface?, which: Int) {
                    finishAffinity()
                 }

             })
             dialog.show()


         }
         R.id.item_web->{
             val intentweb=Intent(Intent.ACTION_VIEW,Uri.parse("https://nirasystem.com/"))
             startActivity(intentweb)
         }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        val noteslist=notedbadapter.showallNotes()
        var adapter=Noteadapter(this@MainActivity,noteslist)
        recyclerNotes.adapter=adapter
        var manager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        recyclerNotes.layoutManager=manager

    }
}
