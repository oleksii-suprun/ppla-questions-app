package com.osuprun.pplaquestionsapp.domain

data class Question(
    val id: Long,
    val qid: String,
    val question: String,
    val comment: String?,
    val options: List<String>,
    val answers: List<Int> = listOf(0)
)
