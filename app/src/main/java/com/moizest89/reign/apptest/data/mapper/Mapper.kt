package com.moizest89.reign.apptest.data.mapper

import com.moizest89.reign.apptest.data.model.hits.Hit
import com.moizest89.reign.apptest.data.model.hits.Hits
import com.moizest89.reign.apptest.domain.model.NewsItem

fun MutableList<Hit>.toNewsListItem(): MutableList<NewsItem> {
    return this.map { it.toNewsItem() }.toMutableList()
}

fun Hits.toNewsListItem(): MutableList<NewsItem> {
    return this.hits.map { it.toNewsItem() }.toMutableList()
}

fun Hit.toNewsItem(): NewsItem {
    return NewsItem(
        id = this.storyId,
        title = this.storyTitle ?: run { this.title },
        author = this.author,
        createdAt = this.createdAt,
        createdAtI = this.createdAtI,
        url = this.storyUrl,
        commentText = this.commentText?:"",
        numComments = this.numComments,
        objectID = this.objectID,
        parentId = this.parentId,
        storyText = this.storyText?:""
    )
}