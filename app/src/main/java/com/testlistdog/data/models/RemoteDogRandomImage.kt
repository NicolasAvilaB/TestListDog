package com.testlistdog.data.models

import com.google.gson.annotations.SerializedName
import com.testlistdog.data.models.Constants.MESSAGE
import com.testlistdog.data.models.Constants.STATUS

data class RemoteDogRandomImage(
    @SerializedName(MESSAGE) val message: String,
    @SerializedName(STATUS) val status: String
)
