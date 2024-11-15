package com.dhontiveros.idealistatechtest.data.remote.api

import com.dhontiveros.idealistatechtest.core.utils.BaseTest
import com.dhontiveros.idealistatechtest.core.utils.TypeFileResponse
import com.dhontiveros.idealistatechtest.core.utils.assertResponseOK
import com.dhontiveros.idealistatechtest.core.utils.getEntityJson
import com.dhontiveros.idealistatechtest.data.remote.model.PropertyDetailDto
import com.dhontiveros.idealistatechtest.data.remote.model.PropertyListItemDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class ApiServiceRemoteTest : BaseTest(){

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService


    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setUpCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl("https://idealista.github.io/android-challenge/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setUp() {
        // Creation API
        apiService = retrofit.create(ApiService::class.java)
        // Creation MockWebServer and arrangement
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `check getPropertiesList() service -- Success`() = runTest {
        val response = apiService.getPropertiesList()
        response.assertResponseOK()

        val body = response.body()!!
        body.apply {
            assertThat(this, `is`(notNullValue()))
            assertTrue(this.isNotEmpty())

            val mockData = TypeFileResponse.LIST_SUCCESS.getEntityJson<List<PropertyListItemDto>>()
            assertEquals(mockData!!.size, this.size)
        }

    }

    @Test
    fun `check getDetailProperty() service -- Success`() = runTest {
        val response = apiService.getDetailProperty()
        response.assertResponseOK()

        val responseData = response.body()!!
        responseData.apply {
            assertThat(this, `is`(notNullValue()))
            assertThat(this.adid, `is`(notNullValue()))

            val mockData = TypeFileResponse.DETAIL_SUCCESS.getEntityJson<PropertyDetailDto>()
            assertEquals(mockData!!.adid, this.adid)
            assertEquals(mockData.price, this.price)
        }

    }
}