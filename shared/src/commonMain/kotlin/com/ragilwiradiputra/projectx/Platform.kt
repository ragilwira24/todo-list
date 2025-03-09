package com.ragilwiradiputra.projectx

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform