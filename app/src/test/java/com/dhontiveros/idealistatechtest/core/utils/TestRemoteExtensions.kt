package com.dhontiveros.idealistatechtest.core.utils

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import retrofit2.Response

// ------------------------------------------------------------------------------------------------

fun<T> Response<T>.assertResponseOK(){
    assertTrue(code() in 200..399)
    headers()["content-type"]?.let { assertTrue(it.contains("application/json")) }
    assertThat(errorBody(), `is`(CoreMatchers.nullValue()))
}