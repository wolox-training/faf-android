package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.example.model.LoginRequest
import ar.com.wolox.android.example.model.LoginResponse
import ar.com.wolox.android.example.network.repository.PostRepository
import ar.com.wolox.wolmo.core.tests.CoroutineTestRule
import ar.com.wolox.wolmo.core.tests.WolmoPresenterTest
import ar.com.wolox.wolmo.networking.retrofit.handler.NetworkResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class LoginPresenterTest : WolmoPresenterTest<LoginView, LoginPresenter>() {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(runOnAllTests = true)

    @Mock
    lateinit var postRepository: PostRepository

    override fun getPresenterInstance() = LoginPresenter(postRepository)

    @Test
    fun `given a empty email then show error, enter email`() = runBlocking {
        // GIVEN
        val email = ""
        val password = "123456"
        // WHEN
        presenter.onClickLogin(email, password).join()
        // THEN
        verify(view, times(1)).showErrorEmail("Ingrese El Correo")
    }

    @Test
    fun `given a empty password then show error, enter password`() = runBlocking {
        // GIVEN
        val email = "example@gmail.com"
        val password = ""
        // WHEN
        presenter.onClickLogin(email, password).join()
        // THEN
        verify(view, times(1)).showErrorPassword("Ingrese La Contraseña")
    }

    @Test
    fun `given a wrong emial then show error, email format invalid`() = runBlocking {
        // GIVEN
        val email = "examplgmail"
        val password = "123456"
        // WHEN
        presenter.onClickLogin(email, password).join()
        // THEN
        verify(view, times(1)).showErrorFormat("El email debe tener el formato example@example.com")
    }

    @Test
    fun `given a correct login request`() = runBlocking {
        // GIVEN
        val email = "clinton.harris59@example.com"
        val password = "123456"
        val loginRequest = LoginRequest(email, password)
        val loginResponse = LoginResponse("1", "example@example", "name", "nick", "dd-mm-aaaa")
        var response = mock(Response::class.java) as Response<LoginResponse>
        whenever(response.body()).doReturn(loginResponse)
        whenever(postRepository.getLogin(loginRequest)).doReturn(NetworkResponse.Success(response))
        // WHEN
        presenter.onClickLogin(email, password).join()
        // THEN
        verify(view, times(1)).goToHomePage()
    }
}
