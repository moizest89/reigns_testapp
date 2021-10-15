package com.moizest89.reign.apptest.data.mapper

import com.moizest89.reign.apptest.data.model.db.NewsEntity
import com.moizest89.reign.apptest.data.model.hits.Hit
import com.moizest89.reign.apptest.data.model.hits.Hits
import com.moizest89.reign.apptest.domain.model.NewsItem

fun Hits.toNewsEntities(): MutableList<NewsEntity> {
    return this.hits.map { it.toNewsEntity() }.toMutableList()
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
        commentText = this.commentText ?: "",
        numComments = this.numComments,
        objectID = this.objectID,
        parentId = this.parentId,
        storyText = this.storyText ?: ""
    )
}

fun Hit.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = this.storyId,
        title = this.storyTitle ?: run { this.title },
        author = this.author,
        createdAt = this.createdAt,
        createdAtI = this.createdAtI,
        url = this.storyUrl,
        commentText = this.commentText ?: "",
        numComments = this.numComments,
        objectID = this.objectID,
        parentId = this.parentId,
        storyText = this.storyText ?: ""
    )
}

fun MutableList<NewsEntity>.toNewsItems(): MutableList<NewsItem> {
    return this.map { it.toNewsItem() }.toMutableList()
}

fun NewsEntity.toNewsItem(): NewsItem {
    return NewsItem(
        id = this.id,
        title = this.title ?: run { this.title },
        author = this.author,
        createdAt = this.createdAt,
        createdAtI = this.createdAtI,
        url = this.url,
        commentText = this.commentText ?: "",
        numComments = this.numComments,
        objectID = this.objectID,
        parentId = this.parentId,
        storyText = this.storyText ?: ""
    )
}

fun NewsItem.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = this.id,
        title = this.title ?: run { this.title },
        author = this.author,
        createdAt = this.createdAt,
        createdAtI = this.createdAtI,
        url = this.url,
        commentText = this.commentText ?: "",
        numComments = this.numComments,
        objectID = this.objectID,
        parentId = this.parentId,
        storyText = this.storyText ?: ""
    )
}