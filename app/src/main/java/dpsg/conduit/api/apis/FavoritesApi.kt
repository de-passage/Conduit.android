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

import dpsg.conduit.api.models.CreateArticle201Response

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

class FavoritesApi(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient) : ApiClient(basePath, client) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "https://api.realworld.io/api")
        }
    }

    /**
     * Favorite an article
     * Favorite an article. Auth is required
     * @param slug Slug of the article that you want to favorite
     * @return CreateArticle201Response
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun createArticleFavorite(slug: kotlin.String) : CreateArticle201Response {
        val localVarResponse = createArticleFavoriteWithHttpInfo(slug = slug)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CreateArticle201Response
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
     * Favorite an article
     * Favorite an article. Auth is required
     * @param slug Slug of the article that you want to favorite
     * @return ApiResponse<CreateArticle201Response?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun createArticleFavoriteWithHttpInfo(slug: kotlin.String) : ApiResponse<CreateArticle201Response?> {
        val localVariableConfig = createArticleFavoriteRequestConfig(slug = slug)

        return request<Unit, CreateArticle201Response>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation createArticleFavorite
     *
     * @param slug Slug of the article that you want to favorite
     * @return RequestConfig
     */
    fun createArticleFavoriteRequestConfig(slug: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/articles/{slug}/favorite".replace("{"+"slug"+"}", encodeURIComponent(slug.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
            body = localVariableBody
        )
    }

    /**
     * Unfavorite an article
     * Unfavorite an article. Auth is required
     * @param slug Slug of the article that you want to unfavorite
     * @return CreateArticle201Response
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun deleteArticleFavorite(slug: kotlin.String) : CreateArticle201Response {
        val localVarResponse = deleteArticleFavoriteWithHttpInfo(slug = slug)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CreateArticle201Response
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
     * Unfavorite an article
     * Unfavorite an article. Auth is required
     * @param slug Slug of the article that you want to unfavorite
     * @return ApiResponse<CreateArticle201Response?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun deleteArticleFavoriteWithHttpInfo(slug: kotlin.String) : ApiResponse<CreateArticle201Response?> {
        val localVariableConfig = deleteArticleFavoriteRequestConfig(slug = slug)

        return request<Unit, CreateArticle201Response>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation deleteArticleFavorite
     *
     * @param slug Slug of the article that you want to unfavorite
     * @return RequestConfig
     */
    fun deleteArticleFavoriteRequestConfig(slug: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/articles/{slug}/favorite".replace("{"+"slug"+"}", encodeURIComponent(slug.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}
