import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {



    @Test
    fun add() {
        //WallService.clear()
        val res = WallService.add(getPost1(0))
        assertNotEquals(0, res.id);
    }

    @Test
    fun updateIdExists() {
        //WallService.clear()
        WallService.add(getPost1(0))

        val deleted = WallService.update(getPost2(1));

        assertTrue(deleted)
    }

    @Test
    fun updateIdNotExists() {
        //WallService.clear()
        WallService.add(getPost1(0))

        val deleted = WallService.update(getPost2(33));

        assertFalse(deleted)
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_PostNotExists(){
        WallService.add(getPost1(0))

        var created = WallService.createComment(getComment1(getPost2(123)))
    }

    @Test()
    fun createComment_PostExists(){
        val post = getPost1(0)
        WallService.add(post)

        var created = WallService.createComment(getComment1(post))

        assertTrue(created)
    }
}