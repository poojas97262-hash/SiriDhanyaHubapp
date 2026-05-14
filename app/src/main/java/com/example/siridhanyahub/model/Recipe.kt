package com.example.siridhanyahub.model

import java.io.Serializable

data class Recipe(
    val id: Int,
    val title: String,
    val milletType: String,
    val prepTime: String,
    val difficulty: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val imageRes: Int,
    val calories: String,
    val kannadaInstructions: List<String> = emptyList()
) : Serializable