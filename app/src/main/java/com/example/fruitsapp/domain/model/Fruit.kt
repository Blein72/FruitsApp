package com.example.fruitsapp.domain.model

data class Fruit(
        val id: Long,
        val name: String,
        val color: String,
        val weight: Long,
        val delicious: Boolean,
        val created_at: String,
        val updated_at: String
)