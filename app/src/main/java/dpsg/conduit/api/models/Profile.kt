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

package dpsg.conduit.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param username 
 * @param bio 
 * @param image 
 * @param following 
 */


data class Profile (

    @Json(name = "username")
    val username: kotlin.String,

    @Json(name = "bio")
    val bio: kotlin.String?,

    @Json(name = "image")
    val image: kotlin.String,

    @Json(name = "following")
    val following: kotlin.Boolean

)
