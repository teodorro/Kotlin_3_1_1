import Exceptions.CommentNotFoundException
import Exceptions.PostNotFoundException
import attachments.Audio
import attachments.AudioAttachment
import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    private fun getCommentTest(id: Int, post: Post): Comment {
        return Comment(id = id, postId = post.id, owner_id = 1, message = "my comment",
            reply_to_comment = 1, attachments = AudioAttachment("audio", Audio()),
            from_group = 1, sticker_id = 1)
    }

    private fun getPostTest(id: Int): Post{
        return Post(id, 2, 3, 4, 5, "asd", 6, 7, true,
            Comments(1, true, true, true, true),
            "asd",
            Likes(1, true, true, true),
            Views(2), "qw",
            PostSource("zxc", "xcv", "cvb", "vbn"),
            arrayOf(AudioAttachment("audio", Audio())),
            Geo("qwe", "1:2", Place(1, "ert", 2, 3, 4, "rty", 5, 6, 7, 8, 9, "tyu")),
            3,
            null,
            true, true, true, true, true, true, true)
    }

    @Test
    fun add() {
        //WallService.clear()
        val res = WallService.add(getPostTest(0))
        assertNotEquals(0, res.id);
    }

    @Test
    fun updateIdExists() {
        //WallService.clear()
        WallService.add(getPostTest(0))

        val deleted = WallService.update(getPost2(1));

        assertTrue(deleted)
    }

    @Test
    fun updateIdNotExists() {
        //WallService.clear()
        WallService.add(getPostTest(0))

        val deleted = WallService.update(getPost2(33));

        assertFalse(deleted)
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_PostNotExists(){
        WallService.add(getPostTest(0))

        WallService.createComment(getComment1(getPost2(123)))
    }

    @Test()
    fun createComment_PostExists(){
        val post = getPostTest(0)
        WallService.add(post)

        var created = WallService.createComment(getComment1(post))

        assertTrue(created)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun reportComment_ReasonOutOfRange(){
        WallService.reportComment(1, 2, 123);
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportComment_CommentIdNotFound(){
        val post = getPostTest(0)
        WallService.add(post)
        val comment = getCommentTest(12, post)
        WallService.createComment(comment)

        WallService.reportComment(1, 123, 1);
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportComment_OwnerIdNotFound(){
        val post = getPostTest(0)
        WallService.add(post)
        val comment = getCommentTest(12, post)
        WallService.createComment(comment)

        WallService.reportComment(123, 12, 1);
    }

    @Test
    fun reportComment_Added(){
        val post = getPostTest(0)
        WallService.add(post)
        val comment = getCommentTest(12, post)
        WallService.createComment(comment)

        var added = WallService.reportComment(1, 12, 1);

        assertTrue(added)
    }
}