package cn.staynoob.ys

import okhttp3.FormBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class YsUtilsTest {
    @Test
    @DisplayName("formBodyIterator")
    fun formBodyIteratorTest100() {
        val formBody = FormBody.Builder()
                .add("foo", "foo")
                .add("bar", "bar")
                .build()
        val res = formBody.iterator()
                .asSequence()
                .toMap()
        assertThat(res)
                .containsAllEntriesOf(mapOf(
                        "foo" to "foo",
                        "bar" to "bar"
                ))
    }
}