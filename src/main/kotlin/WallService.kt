import Exceptions.CommentNotFoundException
import Exceptions.PostNotFoundException

object WallService {
    private var nextId: Int = 1
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reports = emptyArray<Report>()

    fun add(post: Post): Post{
        post.id = nextId++
        posts += post
        return posts.last()
    }

    fun print(){
        println("--- posts ---")
        for (post in posts)
            println(post)
        println("--- comments ---")
        for (comment in comments)
            println(comment)
    }

    fun update (post: Post): Boolean{
        var ind : Int = -1;

        for ((index, p) in posts.withIndex()){
            if (p.id == post.id){
                ind = index
                break
            }
        }

        if (ind < 0)
            return false

        val postNew = post.copy(ownerId = posts[ind].ownerId, date = posts[ind].date);
        posts[ind] = postNew;
        return true
    }

    fun clear(){
        nextId = 1;
        posts = emptyArray<Post>()
    }

    fun createComment(comment: Comment) : Boolean {
        for (post in posts){
            if (post.id == comment.postId){
                comments += comment;
                return true;
            }
        }
        throw PostNotFoundException();
    }

    fun reportComment(owner_id: Int, comment_id: Int, reason: Int) : Boolean{
        if (reason !in 0..8)
            throw IndexOutOfBoundsException("Value of 'reason' argument is out of range");

        for (comment in comments){
            if (comment.id == comment_id && comment.owner_id == owner_id){
                reports += Report(owner_id = owner_id, comment_id = comment_id, reason = reason)
                return true;
            }
        }
        throw CommentNotFoundException("Comment with id = ${comment_id} and owner_id = ${owner_id} was not found")
    }

}