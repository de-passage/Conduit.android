/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package dpsg.conduit.api.apis

import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.HttpUrl

import dpsg.conduit.api.models.GetProfileByUsername200Response

import dpsg.conduit.api.infrastructure.ApiClient
import dpsg.conduit.api.infrastructure.ApiResponse
import dpsg.conduit.api.infrastructure.ClientException
import dpsg.conduit.api.infrastructure.ClientError
import dpsg.conduit.api.infrastructure.ServerException
import dpsg.conduit.api.infrastructure.ServerError
import dpsg.conduit.api.infrastructure.MultiValueMap
import dpsg.conduit.api.infrastructure.RequestConfig
import dpsg.conduit.api.infrastructure.RequestMethod
import dpsg.conduit.api.infrastructure.ResponseType
import dpsg.conduit.api.infrastructure.Success

class ProfileApi(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient) : ApiClient(basePath, client) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "https://api.realworld.io/api")
        }
    }

    /**
     * Follow a user
     * Follow a user by username
     * @param username Username of the profile you want to follow
     * @return GetProfileByUsername200Response
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun followUserByUsername(username: kotlin.String) : GetProfileByUsername200Response {
        val localVarResponse = followUserByUsernameWithHttpInfo(username = username)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as GetProfileByUsername200Response
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * Follow a user
     * Follow a user by username
     * @param username Username of the profile you want to follow
     * @return ApiResponse<GetProfileByUsername200Response?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun followUserByUsernameWithHttpInfo(username: kotlin.String) : ApiResponse<GetProfileByUsername200Response?> {
        val localVariableConfig = followUserByUsernameRequestConfig(username = username)

        return request<Unit, GetProfileByUsername200Response>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation followUserByUsername
     *
     * @param username Username of the profile you want to follow
     * @return RequestConfig
     */
    fun followUserByUsernameRequestConfig(username: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/profiles/{username}/follow".replace("{"+"username"+"}", encodeURIComponent(username.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
            body = localVariableBody
        )
    }

    /**
     * Get a profile
     * Get a profile of a user of the system. Auth is optional
     * @param username Username of the profile to get
     * @return GetProfileByUsername200Response
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getProfileByUsername(username: kotlin.String) : GetProfileByUsername200Response {
        val localVarResponse = getProfileByUsernameWithHttpInfo(username = username)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as GetProfileByUsername200Response
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * Get a profile
     * Get a profile of a user of the system. Auth is optional
     * @param username Username of the profile to get
     * @return ApiResponse<GetProfileByUsername200Response?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun getProfileByUsernameWithHttpInfo(username: kotlin.String) : ApiResponse<GetProfileByUsername200Response?> {
        val localVariableConfig = getProfileByUsernameRequestConfig(username = username)

        return request<Unit, GetProfileByUsername200Response>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation getProfileByUsername
     *
     * @param username Username of the profile to get
     * @return RequestConfig
     */
    fun getProfileByUsernameRequestConfig(username: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/profiles/{username}".replace("{"+"username"+"}", encodeURIComponent(username.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = false,
            body = localVariableBody
        )
    }

    /**
     * Unfollow a user
     * Unfollow a user by username
     * @param username Username of the profile you want to unfollow
     * @return GetProfileByUsername200Response
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun unfollowUserByUsername(username: kotlin.String) : GetProfileByUsername200Response {
        val localVarResponse = unfollowUserByUsernameWithHttpInfo(username = username)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as GetProfileByUsername200Response
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * Unfollow a user
     * Unfollow a user by username
     * @param username Username of the profile you want to unfollow
     * @return ApiResponse<GetProfileByUsername200Response?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun unfollowUserByUsernameWithHttpInfo(username: kotlin.String) : ApiResponse<GetProfileByUsername200Response?> {
        val localVariableConfig = unfollowUserByUsernameRequestConfig(username = username)

        return request<Unit, GetProfileByUsername200Response>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation unfollowUserByUsername
     *
     * @param username Username of the profile you want to unfollow
     * @return RequestConfig
     */
    fun unfollowUserByUsernameRequestConfig(username: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/profiles/{username}/follow".replace("{"+"username"+"}", encodeURIComponent(username.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}