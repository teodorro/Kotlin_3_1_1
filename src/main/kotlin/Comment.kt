import attachments.Attachment

data class Comment (
    val owner_id : Int,
    val postId : Int,
    val from_group : Int,
    val message : String,
    val reply_to_comment : Int,
    val attachments : Attachment,
    val sticker_id: Int,
    val id : Int // он же guid
)
//data class Comment (
//    val id : Int,
//    val postId : Int,
//    val from_id : Int,
//    val date : Int,
//    val text : String,
//    val reply_to_user : Int,
//    val reply_to_comment : Int,
//    val attachments : Attachment,
//    val parents_stack : Array<Comment>,
//    val thread: PostsThread
//)