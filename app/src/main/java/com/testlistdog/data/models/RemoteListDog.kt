package com.testlistdog.data.models

import com.google.gson.annotations.SerializedName
import com.testlistdog.data.models.Constants.MESSAGE
import com.testlistdog.data.models.Constants.STATUS

data class RemoteListDog(
    @SerializedName(MESSAGE) val message: List<String>,
    @SerializedName(STATUS) val status: String
)
