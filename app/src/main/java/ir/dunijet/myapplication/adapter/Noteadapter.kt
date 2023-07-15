package ir.dunijet.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ir.dunijet.myapplication.R
import ir.dunijet.myapplication.database.Notedbadapter
import ir.dunijet.myapplication.model.Note


open class Noteadapter(mcontext: Context,list:MutableList<Note>):RecyclerView.Adapter<Noteadapter.NoteVH>(){
    var noteslist=list
    val context: Context=mcontext
  var notedbadapter: Notedbadapter
    init {
       notedbadapter=Notedbadapter(context)
    }

    class NoteVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txttitle=itemView.findViewById<AppCompatTextView>(R.id.txt_title)
        val txtdescription=itemView.findViewById<AppCompatTextView>(R.id.txt_description)
        val imgshare=itemView.findViewById<AppCompatImageView>(R.id.img_share)
        val imgdelet=itemView.findViewById<AppCompatImageView>(R.id.img_delet)
        val imgedit=itemView.findViewById<AppCompatImageView>(R.id.img_edit)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        val view:View=LayoutInflater.from(context).inflate(R.layout.notes_row,null)
        return NoteVH(view)

    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        var note=noteslist.get(position)
        holder.txttitle.setText(note.title)
        holder.txtdescription.setText(note.description)
        holder.imgdelet.setOnClickListener({
            note.id?.let { it1 -> notedbadapter.deletnote(it1) }
            noteslist.removeAt(position)
            notifyItemRemoved(position)
            notifyItemChanged(position,noteslist.size)
        })
        holder.imgshare.setOnClickListener({
            var intent=Intent(android.content.Intent.ACTION_SEND)
            val shareBody = note.description
            intent.setType("text/plain")
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT,note.title)
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            context.startActivity(Intent.createChooser(intent, "share"));
        })
    }

    override fun getItemCount(): Int {
        return noteslist.size

    }

}