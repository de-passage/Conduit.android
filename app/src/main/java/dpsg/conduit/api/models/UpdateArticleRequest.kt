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

import dpsg.conduit.api.models.UpdateArticle

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param article 
 */


data class UpdateArticleRequest (

    @Json(name = "article")
    val article: UpdateArticle

)

