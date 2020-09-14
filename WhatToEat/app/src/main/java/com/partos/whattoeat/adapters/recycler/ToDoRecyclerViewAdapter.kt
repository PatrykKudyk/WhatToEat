package com.partos.whattoeat.adapters.recycler

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.models.ToDo
import kotlinx.android.synthetic.main.row_to_do.view.*

class ToDoRecyclerViewAdapter(var todoList: ArrayList<ToDo>) :
    RecyclerView.Adapter<ToDoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_to_do, parent, false)
        return ToDoViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val db = DataBaseHelper(holder.itemView.context)
        if (todoList[position].isDone == 1) {
            holder.itemView.row_to_do_image.setImageDrawable(holder.itemView.context.getDrawable(R.drawable.ic_done))
            holder.itemView.row_to_do_card.setCardBackgroundColor(
                holder.itemView.context.getColorStateList(
                    R.color.colorRed
                )
            )
        } else {
            holder.itemView.row_to_do_image.setImageDrawable(holder.itemView.context.getDrawable(R.drawable.ic_not_done))
            holder.itemView.row_to_do_card.setCardBackgroundColor(
                holder.itemView.context.getColorStateList(
                    R.color.colorRedLightLight
                )
            )
        }

        if (position == todoList.size - 1) {
//            holder.itemView.row_to_do_save.visibility = View.VISIBLE
            holder.itemView.row_to_do_edit.visibility = View.VISIBLE
            holder.itemView.row_to_do_text.visibility = View.GONE
        } else {
            holder.itemView.row_to_do_save.visibility = View.GONE
            holder.itemView.row_to_do_edit.visibility = View.GONE
            holder.itemView.row_to_do_text.visibility = View.VISIBLE
            holder.itemView.row_to_do_text.text = todoList[position].text
        }
        holder.itemView.row_to_do_save.setOnClickListener {
            db.addToDo(holder.itemView.row_to_do_edit.text.toString())
            holder.itemView.row_to_do_edit.setText("")
            todoList = db.getToDoList()
            todoList.add(ToDo(-1, "no", 0))
            notifyDataSetChanged()
        }
        holder.itemView.row_to_do_card.setOnClickListener {
            if (position != todoList.size - 1) {
                if (todoList[position].isDone == 1) {
                    holder.itemView.row_to_do_card.setCardBackgroundColor(
                        holder.itemView.context.getColorStateList(
                            R.color.colorRedLightLight
                        )
                    )
                    holder.itemView.row_to_do_image.setImageDrawable(
                        holder.itemView.context.getDrawable(
                            R.drawable.ic_not_done
                        )
                    )
                    todoList[position].isDone = 0
                } else {
                    holder.itemView.row_to_do_card.setCardBackgroundColor(
                        holder.itemView.context.getColorStateList(
                            R.color.colorRed
                        )
                    )
                    holder.itemView.row_to_do_image.setImageDrawable(
                        holder.itemView.context.getDrawable(
                            R.drawable.ic_done
                        )
                    )
                    todoList[position].isDone = 1
                }
                db.updateToDo(todoList[position])
            }
        }
        holder.itemView.row_to_do_edit.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (holder.itemView.row_to_do_edit.text.toString() != "") {
                    holder.itemView.row_to_do_save.visibility = View.VISIBLE
                } else {
                    holder.itemView.row_to_do_save.visibility = View.GONE
                }
            }
        })
    }

}

class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view)