data class PostsThread (
    val count : Int,
    val items : Array<Comment>,
    val can_post : Boolean,
    val show_reply_button : Boolean,
    val groups_can_post : Boolean
){}