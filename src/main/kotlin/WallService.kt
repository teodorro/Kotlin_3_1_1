object WallService {
    private var nextId: Int = 1
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

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

}