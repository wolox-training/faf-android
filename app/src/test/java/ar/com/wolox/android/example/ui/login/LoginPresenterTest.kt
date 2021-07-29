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

    val input_empty_email = ""
    val input_password = "123456"
    val input_email = "clinton.harris59@example.com"
    val input_empty_password = ""
    val input_wrong_email = "exempleexmple.com"

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(runOnAllTests = true)

    @Mock
    lateinit var postRepository: PostRepository

    override fun getPresenterInstance() = LoginPresenter(postRepository)

    // given an empty email, when the user presses the login button, then an error is shown

    @Test
    fun `given a empty email,when the user press the login button,then error enter email is shown`() = runBlocking {
        // GIVEN

        // WHEN
        presenter.onClickLogin(input_empty_email, input_password).join()
        // THEN
        verify(view, times(1)).showErrorEmail("Ingrese El Correo")
    }

    @Test
    fun `given a empty password, when the user press login button, then error enter password is shown`() = runBlocking {
        // GIVEN

        // WHEN
        presenter.onClickLogin(input_email, input_empty_password).join()
        // THEN
        verify(view, times(1)).showErrorPassword("Ingrese La Contraseña")
    }

    @Test
    fun `given a wrong email,when the user press login button, then show email format invalid is shown`() = runBlocking {
        // GIVEN

        // WHEN
        presenter.onClickLogin(input_wrong_email, input_password).join()
        // THEN
        verify(view, times(1)).showErrorFormat("El email debe tener el formato example@example.com")
    }

    @Test
    fun `given a correct login request, when user press login button, then go to homepage `() = runBlocking {
        // GIVEN
        val loginRequest = LoginRequest(input_email, input_password)
        val loginResponse = LoginResponse("1", "example@example", "name", "nick", "dd-mm-aaaa")
        var response = mock(Response::class.java) as Response<LoginResponse>
        whenever(response.body()).doReturn(loginResponse)
        whenever(postRepository.getLogin(loginRequest)).doReturn(NetworkResponse.Success(response))
        // WHEN
        presenter.onClickLogin(input_email, input_password).join()
        // THEN
        verify(view, times(1)).goToHomePage()
    }

    @Test
    fun `given a incorrect login request, when user press login, then error is shown`() = runBlocking {
        // GIVEN
        val loginRequest = LoginRequest(input_email, input_password)
        val loginResponse = LoginResponse("1", "example@example", "name", "nick", "dd-mm-aaaa")
        var response = mock(Response::class.java) as Response<LoginResponse>
        whenever(response.body()).doReturn(loginResponse)
        whenever(postRepository.getLogin(loginRequest)).doReturn(NetworkResponse.Error(response))
        // WHEN
        presenter.onClickLogin(input_email, input_password).join()
        // THEN
        verify(view, times(1)).showError("Correo o Contraseña Invalidos")
    }
}
