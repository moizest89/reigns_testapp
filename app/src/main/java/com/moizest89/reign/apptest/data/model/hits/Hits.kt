package com.moizest89.reign.apptest.data.model.hits
import com.google.gson.annotations.SerializedName


data class Hits(
    @SerializedName("exhaustiveNbHits")
    var exhaustiveNbHits: Boolean = false,
    @SerializedName("hits")
    var hits: MutableList<Hit> = mutableListOf(),
    @SerializedName("hitsPerPage")
    var hitsPerPage: Int = 0,
    @SerializedName("nbHits")
    var nbHits: Int = 0,
    @SerializedName("nbPages")
    var nbPages: Int = 0,
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("params")
    var params: String = "",
    @SerializedName("processingTimeMS")
    var processingTimeMS: Int = 0,
    @SerializedName("query")
    var query: String = "",
    @SerializedName("renderingContent")
    var renderingContent: RenderingContent = RenderingContent()
)

data class Hit(
    @SerializedName("author")
    var author: String = "",
    @SerializedName("comment_text")
    var commentText: String? = "",
    @SerializedName("created_at")
    var createdAt: String = "",
    @SerializedName("created_at_i")
    var createdAtI: Int = 0,
    @SerializedName("_highlightResult")
    var highlightResult: HighlightResult = HighlightResult(),
    @SerializedName("num_comments")
    var numComments: Int = 0,
    @SerializedName("objectID")
    var objectID: String = "",
    @SerializedName("parent_id")
    var parentId: Int = 0,
    @SerializedName("points")
    var points: Any? = Any(),
    @SerializedName("story_id")
    var storyId: Int = 0,
    @SerializedName("story_text")
    var storyText: String? = "",
    @SerializedName("story_title")
    var storyTitle: String? = "",
    @SerializedName("story_url")
    var storyUrl: String = "",
    @SerializedName("_tags")
    var tags: List<String> = listOf(),
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("url")
    var url: Any? = Any()
)

class RenderingContent

data class HighlightResult(
    @SerializedName("author")
    var author: Author = Author(),
    @SerializedName("comment_text")
    var commentText: CommentText = CommentText(),
    @SerializedName("story_text")
    var storyText: StoryText = StoryText(),
    @SerializedName("story_title")
    var storyTitle: StoryTitle = StoryTitle(),
    @SerializedName("story_url")
    var storyUrl: StoryUrl = StoryUrl(),
    @SerializedName("title")
    var title: Title = Title()
)

data class Author(
    @SerializedName("matchLevel")
    var matchLevel: String = "",
    @SerializedName("matchedWords")
    var matchedWords: List<Any> = listOf(),
    @SerializedName("value")
    var value: String = ""
)

data class CommentText(
    @SerializedName("fullyHighlighted")
    var fullyHighlighted: Boolean = false,
    @SerializedName("matchLevel")
    var matchLevel: String = "",
    @SerializedName("matchedWords")
    var matchedWords: List<String> = listOf(),
    @SerializedName("value")
    var value: String = ""
)

data class StoryText(
    @SerializedName("fullyHighlighted")
    var fullyHighlighted: Boolean = false,
    @SerializedName("matchLevel")
    var matchLevel: String = "",
    @SerializedName("matchedWords")
    var matchedWords: List<String> = listOf(),
    @SerializedName("value")
    var value: String = ""
)

data class StoryTitle(
    @SerializedName("matchLevel")
    var matchLevel: String = "",
    @SerializedName("matchedWords")
    var matchedWords: List<Any> = listOf(),
    @SerializedName("value")
    var value: String = ""
)

data class StoryUrl(
    @SerializedName("matchLevel")
    var matchLevel: String = "",
    @SerializedName("matchedWords")
    var matchedWords: List<Any> = listOf(),
    @SerializedName("value")
    var value: String = ""
)

data class Title(
    @SerializedName("matchLevel")
    var matchLevel: String = "",
    @SerializedName("matchedWords")
    var matchedWords: List<Any> = listOf(),
    @SerializedName("value")
    var value: String = ""
)