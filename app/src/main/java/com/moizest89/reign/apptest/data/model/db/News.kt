package com.moizest89.reign.apptest.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String? = "",
    var url: String? = "",
    var author: String = "",
    var commentText: String = "",
    var numComments: Int = 0,
    var objectID: String = "",
    var parentId: Int = 0,
    var storyText: String = "",
    var createdAt: String = "",
    var createdAtI: Int = 0,
)