package com.nagi.ddreponote.data.NoteList

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val contentSimple:String,
    val createTime: Long,
    val updateTime: Long,
    val textSize: Int
)
