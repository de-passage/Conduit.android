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

import dpsg.conduit.api.models.GenericErrorModelErrors

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param errors 
 */


data class GenericErrorModel (

    @Json(name = "errors")
    val errors: GenericErrorModelErrors

)

